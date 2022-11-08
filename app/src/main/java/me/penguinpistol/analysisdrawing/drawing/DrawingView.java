package me.penguinpistol.analysisdrawing.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.List;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {
    private final SurfaceHolder mHolder;
    private final Rect mViewRect;

    private RenderThread mRenderThread;
    private boolean mIsRender;

    private long mDrawingStartTime;
    private boolean mIsOrderFinished;

    private Bitmap mBitmap;
    private List<DrawingOrder> mDrawingOrders;
    private long mTotalPlayTime;
    private long mCurrentPlayTime;

    private final Paint mGridPaint;
    private boolean mIsShowGrid = true;
    private int mGridColumnCount = 15;
    private float mGridCellSize;

    /////////////////////////////////////////////////////
    private final Paint debugPaint;

    public DrawingView(Context context) {
        this(context, null, 0);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHolder = getHolder();
        mHolder.addCallback(this);

        mViewRect = new Rect();

        mGridPaint = new Paint();
        mGridPaint.setStyle(Paint.Style.STROKE);
        mGridPaint.setColor(Color.parseColor("#4DFFFFFF"));
        mGridPaint.setStrokeWidth(2 * getResources().getDisplayMetrics().density);

        debugPaint = new Paint();
        debugPaint.setStyle(Paint.Style.FILL);
        debugPaint.setColor(Color.WHITE);
        debugPaint.setTextSize(12 * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getDrawingRect(mViewRect);

        if(mGridColumnCount > 0) {
            mGridCellSize = (float)getMeasuredWidth() / mGridColumnCount;
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mIsRender = true;
        mIsOrderFinished = false;
        mRenderThread = new RenderThread();
        mRenderThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mIsRender = false;
        try {
            mRenderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setImage(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void startOrders(List<DrawingOrder> orders) {
        if(orders == null || orders.size() == 0) {
            return;
        }
        mDrawingOrders = orders;

        mTotalPlayTime = orders.stream().mapToLong(DrawingOrder::getTotalPlayTime).max().orElse(0);
        mDrawingStartTime = System.currentTimeMillis();
        mCurrentPlayTime = 0;

        mIsOrderFinished = false;
    }

    private class RenderThread extends Thread {

        @Override
        public void run() {
            Canvas canvas;
            while(mIsRender) {
                if(!mIsOrderFinished) {
                    canvas = mHolder.lockCanvas(mViewRect);
                    try {
                        synchronized (mHolder) {
                            // drawing background
                            if(mBitmap != null) {
                                canvas.drawBitmap(mBitmap, null, mViewRect, null);
                            } else {
                                canvas.drawColor(Color.BLACK);
                            }

                            // drawing grid
                            if(mIsShowGrid) {
                                for (int i = 0; i < mGridColumnCount; i++) {
                                    canvas.drawLine(mGridCellSize * i, 0, mGridCellSize * i, mViewRect.bottom, mGridPaint);
                                    canvas.drawLine(0, mGridCellSize * i, mViewRect.right, mGridCellSize * i, mGridPaint);
                                }
                            }

                            mCurrentPlayTime = System.currentTimeMillis() - mDrawingStartTime;
                            canvas.drawText(String.valueOf(mCurrentPlayTime), 50, 50, debugPaint);

                            if(mDrawingOrders != null) {
                                for (int i = 0; i < mDrawingOrders.size(); i++) {
                                    DrawingOrder order = mDrawingOrders.get(i);
                                    float fraction = 0;

                                    if(mCurrentPlayTime >= order.getDelay()) {
                                        fraction = Math.min(1.0F, (float)(mCurrentPlayTime - order.getDelay()) / order.getPlayTime());
                                    }

                                    canvas.drawText(order.getDelay() + " -> " + order.getPlayTime() + " / " + fraction, 50, 100 + 50 * i, debugPaint);
                                    mDrawingOrders.get(i).draw(canvas, fraction);
                                }
                            }

                            if(mCurrentPlayTime > mTotalPlayTime) {
                                mIsOrderFinished = true;
                            }
                        }
                    } finally {
                        if (canvas == null) {
                            return;
                        }
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}

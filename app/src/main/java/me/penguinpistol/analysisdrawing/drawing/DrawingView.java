package me.penguinpistol.analysisdrawing.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.NonNull;

import java.util.List;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {
    private final SurfaceHolder mHolder;
    private final Rect mViewRect;

    private RenderThread mRenderThread;

    Paint paint;

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

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(12 * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getDrawingRect(mViewRect);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mRenderThread = new RenderThread();
        mRenderThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(mRenderThread != null && mRenderThread.isInterrupted()) {
            mRenderThread.start();
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        try {
            mRenderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long mDrawingStartTime;
    private boolean mIsOrderFinished;

    private List<DrawingOrder> mDrawingOrders;
    private long totalPlayTime;
    private long currentPlayTime;

    public void startOrders(List<DrawingOrder> orders) {
        if(orders == null || orders.size() == 0) {
            return;
        }
        mDrawingOrders = orders;

        totalPlayTime = orders.stream().mapToLong(DrawingOrder::getTotalPlayTime).max().orElse(0);
        mDrawingStartTime = System.currentTimeMillis();
        currentPlayTime = 0;

        mIsOrderFinished = false;
    }

    private class RenderThread extends Thread {

        @Override
        public void run() {
            Canvas canvas;
            while(true) {
                if(!mIsOrderFinished) {
                    canvas = mHolder.lockCanvas(mViewRect);
                    try {
                        synchronized (mHolder) {
                            canvas.drawColor(Color.BLACK);

                            currentPlayTime = System.currentTimeMillis() - mDrawingStartTime;
                            canvas.drawText(String.valueOf(currentPlayTime), 50, 50, paint);

                            if(mDrawingOrders != null) {
                                for (int i = 0; i < mDrawingOrders.size(); i++) {
                                    DrawingOrder order = mDrawingOrders.get(i);
                                    float fraction = 0;

                                    if(currentPlayTime >= order.getDelay()) {
                                        fraction = Math.min(1.0F, (float)(currentPlayTime - order.getDelay()) / order.getPlayTime());
                                    }

                                    canvas.drawText(order.getDelay() + " -> " + order.getPlayTime() + " / " + fraction, 50, 100 + 50 * i, paint);
                                    mDrawingOrders.get(i).draw(canvas, fraction);
                                }
                            }

                            if(currentPlayTime > totalPlayTime) {
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

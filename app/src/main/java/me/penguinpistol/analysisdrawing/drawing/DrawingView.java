package me.penguinpistol.analysisdrawing.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {
    private final SurfaceHolder mHolder;
    private final Rect mViewRect;

    private ScheduledExecutorService mOrderExecutor;
    private ScheduledExecutorService mRenderExecutor;
    private Canvas mBufferCanvas;
    private boolean mIsRepeat;
    private boolean mIsRender;
    private int mDrawingIndex;
    private long mStartTime;
    private long mTotalPlayTime;
    private long mCurrentPlayTime;

    private Bitmap mBitmap;
    private Rect mCropRect;

    private Class<? extends BaseDrawingModel>[] mModels;
    private List<PointF> mLandmark118;
    private List<PointF> mLandmark171;
    private float mScale;

    private final Paint mGridPaint;
    private boolean mIsShowGrid = true;
    private int mGridColumnCount = 16;
    private float mGridCellSize;

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
        mGridPaint.setStrokeWidth(1 * getResources().getDisplayMetrics().density);
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
        startDrawing(mModels);
//        mRenderThread = new RenderThread();
//        mRenderThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mIsRender = false;

        if(mRenderExecutor != null && !mRenderExecutor.isShutdown()) {
            mRenderExecutor.shutdown();
            mRenderExecutor = null;
        }

        if(mOrderExecutor != null && !mOrderExecutor.isShutdown()) {
            mOrderExecutor.shutdown();
            mOrderExecutor = null;
        }
    }

    @SafeVarargs
    public final void startDrawing(Class<? extends BaseDrawingModel>... models) {
        if(models == null || models.length == 0) {
            return;
        }

        if(mOrderExecutor != null && !mOrderExecutor.isShutdown()) {
            mOrderExecutor.shutdown();
            mOrderExecutor = null;
        }

        mModels = models;
        mDrawingIndex = 0;
        mOrderExecutor = Executors.newSingleThreadScheduledExecutor();
        mOrderExecutor.scheduleWithFixedDelay(() -> {
            try {
                BaseDrawingModel model = models[mDrawingIndex]
                        .getDeclaredConstructor(Context.class, List.class, List.class)
                        .newInstance(getContext(), mLandmark118, mLandmark171);

                List<Order> orders = model.getOrders();
                if(orders == null) {
                    return;
                }

                if(mRenderExecutor != null && !mRenderExecutor.isShutdown()) {
                    mRenderExecutor.shutdown();
                    mRenderExecutor = null;
                }

                mStartTime = System.currentTimeMillis();
                mTotalPlayTime = orders.stream().mapToLong(Order::getTotalPlayTime).max().orElse(0);
                mCurrentPlayTime = 0;
                mRenderExecutor = Executors.newSingleThreadScheduledExecutor();
                mRenderExecutor.scheduleAtFixedRate(() -> {
                    mBufferCanvas = mHolder.lockCanvas(mViewRect);
                    synchronized (mHolder) {
                        mCurrentPlayTime = System.currentTimeMillis() - mStartTime;

                        drawBackground(mBufferCanvas);
                        for (int i = 0; i < orders.size(); i++) {
                            Order order = orders.get(i);
                            float fraction = 0;

                            if(mCurrentPlayTime >= order.getDelay()) {
                                fraction = Math.min(1.0F, (float)(mCurrentPlayTime - order.getDelay()) / order.getPlayTime());
                            }

                            order.draw(mBufferCanvas, fraction);
                        }
                    }
                    mHolder.unlockCanvasAndPost(mBufferCanvas);
                    if(mCurrentPlayTime >= mTotalPlayTime) {
                        mRenderExecutor.shutdown();
                    }
                }, 0, 1000/60, TimeUnit.MILLISECONDS);

                while(!mRenderExecutor.isTerminated() && mIsRender);

                if(mDrawingIndex == models.length && !mIsRepeat) {
                    mOrderExecutor.shutdown();
                } else {
                    mDrawingIndex = (mDrawingIndex + 1) % models.length;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }, 0, 2000, TimeUnit.MILLISECONDS);
    }

    public void setData(Bitmap bitmap, List<PointF> landmark118, List<PointF> landmark171, JsonObject dataJson) {
        Point offset = new Point();

        if(bitmap != null) {
            int viewSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
            offset.set(
                (bitmap.getWidth() - viewSize) >> 1,
                (bitmap.getHeight() - viewSize) >> 1
            );
            mCropRect = new Rect(offset.x, offset.y, offset.x + viewSize, offset.y + viewSize);
            mBitmap = bitmap;

            Canvas canvas = mHolder.lockCanvas(mViewRect);
            drawBackground(canvas);
            mHolder.unlockCanvasAndPost(canvas);

            if(bitmap.getWidth() > bitmap.getHeight()) {
                mScale = (float)mViewRect.height() / bitmap.getHeight();
            } else {
                mScale = (float)mViewRect.width() / bitmap.getWidth();
            }
        }

        mLandmark118 = landmark118.stream().map(p -> new PointF((p.x - offset.x) * mScale, (p.y - offset.y) * mScale)).collect(Collectors.toList());
        mLandmark171 = landmark171.stream().map(p -> new PointF((p.x - offset.x) * mScale, (p.y - offset.y) * mScale)).collect(Collectors.toList());
    }

    public void setRepeat(boolean isRepeat) {
        mIsRepeat = isRepeat;
    }

    private void drawBackground(Canvas canvas) {
        if(mBitmap != null) {
            canvas.drawBitmap(mBitmap, mCropRect, mViewRect, null);
        } else {
            canvas.drawColor(Color.BLACK);
        }

        if(mIsShowGrid) {
            for(int i = 0; i < mGridColumnCount; i++) {
                canvas.drawLine(mGridCellSize * i, 0, mGridCellSize * i, mViewRect.bottom, mGridPaint);
                canvas.drawLine(0, mGridCellSize * i, mViewRect.right, mGridCellSize * i, mGridPaint);
            }
        }
    }
}

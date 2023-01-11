package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;

import androidx.annotation.NonNull;

public class ClippingRect extends BaseObject {

    private final RectF rect;
    private final RectF clippingRect;
    private final RectF animatedRect;
    private final Paint outlinePaint;

    public ClippingRect(int color, @NonNull RectF rect) {
        super(color);
        this.rect = new RectF();
        this.clippingRect = rect;
        this.animatedRect = new RectF();

        outlinePaint = new Paint();
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(2);
        outlinePaint.setColor(Color.WHITE);
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        rect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        if(clippingRect != null) {
            animatedRect.set(
                    clippingRect.left * fraction
                    , clippingRect.top * fraction
                    , rect.width() - ((rect.width() - clippingRect.right) * fraction)
                    , rect.height() - ((rect.height() - clippingRect.bottom) * fraction));
            canvas.clipRect(animatedRect, Region.Op.DIFFERENCE);
            canvas.drawRect(rect, paint);
            canvas.drawRect(animatedRect, outlinePaint);
        }
    }
}

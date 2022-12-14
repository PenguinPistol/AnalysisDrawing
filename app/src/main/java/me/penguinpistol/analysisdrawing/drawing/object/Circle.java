package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.animation.OvershootInterpolator;

public class Circle extends BaseObject {
    private static final float INNER_RADIUS_RATIO = 0.6F;

    private final Paint innerPaint;
    private final PointF cp;
    private final float outerRadius;

    public Circle(int outerColor, int innerColor, float cx, float cy, float outerRadius) {
        this(outerColor, innerColor, new PointF(cx, cy), outerRadius);
    }

    public Circle(int outerColor, int innerColor, PointF cp, float outerRadius) {
        super(outerColor);
        paint.setStyle(Paint.Style.FILL);

        innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint.setStyle(Paint.Style.FILL);
        innerPaint.setColor(innerColor);

        this.cp = cp;
        this.outerRadius = outerRadius;

        interpolator = new OvershootInterpolator(3);
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        canvas.drawCircle(cp.x, cp.y, outerRadius * fraction, paint);
        canvas.drawCircle(cp.x, cp.y, outerRadius * fraction * INNER_RADIUS_RATIO, innerPaint);
    }
}

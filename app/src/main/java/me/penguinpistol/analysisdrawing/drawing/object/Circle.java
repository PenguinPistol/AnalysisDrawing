package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.animation.OvershootInterpolator;

public class Circle extends DrawingObject {
    private static final float INNER_RATIO = 0.5F;

    private final Paint paint2;
    private final PointF point;
    private final float radius;

    public Circle(int color1, int color2, float cx, float cy, float radius) {
        super(color1);
        paint.setStyle(Paint.Style.FILL);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(color2);

        this.point = new PointF(cx, cy);
        this.radius = radius;

        interpolator = new OvershootInterpolator(3.0F);
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        canvas.drawCircle(point.x, point.y, radius * fraction, paint);
        canvas.drawCircle(point.x, point.y, radius * fraction * INNER_RATIO, paint2);
    }
}

package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Arc extends BaseObject {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CW, CCW})
    @interface Direction {}

    public static final int CW = 1;
    public static final int CCW = -1;

    private final RectF oval;
    private final float degree;

    public Arc(int color, float thickness, PointF cp, float radius, float degree, @Direction int direction) {
        super(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(thickness);

        this.degree = degree * direction;
        this.oval = new RectF(cp.x-radius, cp.y-radius, cp.x+radius, cp.y+radius);
    }

    public Arc(int color, float thickness, float cx, float cy, float radius, float degree, @Direction int direction) {
        this(color, thickness, new PointF(cx, cy), radius, degree, direction);
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }

        canvas.drawArc(oval, 0, -degree*fraction, false, paint);
    }
}

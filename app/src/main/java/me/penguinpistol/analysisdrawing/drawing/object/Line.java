package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Interpolator;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Line extends DrawingObject {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SOLID, SHARP, DASH})
    public @interface ShapeType {}

    public static final int SOLID = 0;
    public static final int SHARP = 1;
    public static final int DASH = 2;

    private final PointF p1, p2, cp;


    public Line(float x1, float y1, float x2, float y2, int color, float thickness) {
        this(x1, y1, x2, y2, color, thickness, SOLID);
    }

    public Line(float x1, float y1, float x2, float y2, int color, float thickness, @ShapeType int type) {
        super(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(thickness);

        cp = new PointF(
                (x1 + x2) * 0.5F,
                (y1 + y2) * 0.5F
        );

        p1 = new PointF(x1 - cp.x, y1 - cp.y);
        p2 = new PointF(x2 - cp.x, y2 - cp.y);

        switch(type) {
            case SOLID:
                break;
            case SHARP:
                final int[] gradientColors = new int[] {
                        Color.TRANSPARENT, paint.getColor(), paint.getColor(), Color.TRANSPARENT
                };
                final float[] gradientPosition = {
                        0.00F, 0.1F, 0.9F, 1.00F
                };
                Shader shader = new LinearGradient(x1, y1, x2, y2, gradientColors, gradientPosition, Shader.TileMode.MIRROR);
                paint.setShader(shader);
                break;
            case DASH:
                paint.setPathEffect(new DashPathEffect(new float[]{20, 20}, 10));
                break;
        }
    }

    @Override
    public void draw(Canvas canvas, float fraction) {
        if(interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        canvas.drawLine(
                (p1.x * fraction) + cp.x,
                (p1.y * fraction) + cp.y,
                (p2.x * fraction) + cp.x,
                (p2.y * fraction) + cp.y,
                paint
        );
    }
}

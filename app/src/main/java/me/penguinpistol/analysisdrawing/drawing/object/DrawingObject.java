package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.BaseInterpolator;

import androidx.annotation.ColorInt;

public abstract class DrawingObject {
    protected Paint paint;
    protected BaseInterpolator interpolator;
    protected int alpha;

    public DrawingObject(@ColorInt int color) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);

        alpha = paint.getAlpha();
    }

    public abstract void draw(Canvas canvas, long playTime, float fraction);
}

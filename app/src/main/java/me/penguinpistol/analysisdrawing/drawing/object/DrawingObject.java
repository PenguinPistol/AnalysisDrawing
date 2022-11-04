package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.BaseInterpolator;

import androidx.annotation.ColorInt;

public abstract class DrawingObject {
    protected Paint paint;
    protected BaseInterpolator interpolator;

    public DrawingObject(@ColorInt int color) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
    }

    public abstract void draw(Canvas canvas, float fraction);

    public void setInterpolator(BaseInterpolator interpolator) {
        this.interpolator = interpolator;
    }
}

package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.BaseInterpolator;

import androidx.annotation.ColorInt;

public abstract class BaseObject {
    protected Paint paint;
    protected BaseInterpolator interpolator;
    protected int originAlpha;

    public BaseObject(@ColorInt int color) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);

        originAlpha = paint.getAlpha();
    }

    public abstract void draw(Canvas canvas, long playTime, float fraction);

    public BaseObject setInterpolator(BaseInterpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }
}

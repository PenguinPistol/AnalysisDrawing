package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;

public class Overlay extends BaseObject {
    private final boolean useAnimation;

    public Overlay(int color, boolean useAnimation) {
        super(color);
        this.useAnimation = useAnimation;
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(useAnimation) {
            paint.setAlpha((int)(originAlpha * fraction));
        }
        canvas.drawColor(paint.getColor());
    }
}

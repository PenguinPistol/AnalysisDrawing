package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import me.penguinpistol.analysisdrawing.drawing.Vector2;

public class Arrow extends DrawingObject {

    private final Vector2 v1, v2;
    private final PointF cp;

    public Arrow(float x1, float y1, float x2, float y2, int color) {
        super(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        cp = new PointF((x1 + x2) * 0.5F, (y1 + y2) * 0.5F);

        v1 = Vector2.sub(new Vector2(x1, y1), new Vector2(cp.x, cp.y));
        v2 = Vector2.sub(new Vector2(x2, y2), new Vector2(cp.x, cp.y));
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        canvas.drawLine(
                (v1.x * fraction) + cp.x,
                (v1.y * fraction) + cp.y,
                (v2.x * fraction) + cp.x,
                (v2.y * fraction) + cp.y,
                paint
        );
    }

    private float getAngle(){
        double deltaY = v2.y - v1.y;
        double deltaX = v2.x - v1.x;
        double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (float) (result < 0 ? 360 + result : result);
    }
}

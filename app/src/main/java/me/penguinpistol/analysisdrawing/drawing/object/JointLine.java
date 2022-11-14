package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.List;

public class JointLine extends BaseObject {

    private final List<PointF> points;
    private final float sectionFraction;
    private final int lineCount;

    public JointLine(int color, List<PointF> points, float thickness) {
        this(color, points, thickness, false);
    }

    public JointLine(int color, List<PointF> points, float thickness, boolean isClosed) {
        super(color);
        this.points = points;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(thickness);

        lineCount = points.size() - (isClosed ? 0 : 1);
        sectionFraction = points.size() == 0 ? 0 : 1F / lineCount;
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {

        if(points.size() > 1) {
            for (int i = 0; i < lineCount; i++) {
                PointF p1 = points.get(i);
                PointF p2 = points.get((i+1) % points.size());
                PointF subVector = new PointF(p2.x - p1.x, p2.y - p1.y);
                float itemFraction = Math.max(0, Math.min(1F, (fraction - (sectionFraction * i)) / sectionFraction));
                if(interpolator != null) {
                    itemFraction = interpolator.getInterpolation(itemFraction);
                }
                canvas.drawLine(p1.x, p1.y, (subVector.x * itemFraction) + p1.x, (subVector.y * itemFraction) + p1.y, paint);
            }
        }
    }
}

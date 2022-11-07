package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.animation.AccelerateInterpolator;

import java.util.List;

public class Shape extends DrawingObject {

    private final List<PointF> points;
    private final Path path;
    private final RectF bounds;
    private final Matrix matrix;

    public Shape(int color, List<PointF> points) {
        super(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setPathEffect(new CornerPathEffect(36f));

        this.points = points;

        path = new Path();
        bounds = new RectF();
        matrix = new Matrix();

        interpolator = new AccelerateInterpolator();
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(points == null || points.size() < 3) {
            return;
        }

        path.reset();
        for(int i = 0; i < points.size(); i++) {
            PointF p = points.get(i);
            if(i == 0) {
                path.moveTo(p.x, p.y);
            } else {
                path.lineTo(p.x, p.y);
            }
        }
        path.close();
        path.computeBounds(bounds, true);
        matrix.setScale(fraction, fraction, bounds.centerX(), bounds.centerY());
        path.transform(matrix);

        canvas.drawPath(path, paint);
    }
}

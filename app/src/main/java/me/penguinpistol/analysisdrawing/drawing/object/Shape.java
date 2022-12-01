package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class Shape extends BaseObject {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef(flag = true, value = {NONE, SCALE, ALPHA})
    public @interface AnimationType {}

    public static final int NONE = 0;
    public static final int SCALE = 1;
    public static final int ALPHA = 2;

    private final List<PointF> points;
    private final Path path;
    @AnimationType
    private final int animationType;

    private final RectF bounds;
    private final Matrix matrix;

    public Shape(int color, List<PointF> points) {
        this(color, points, SCALE | ALPHA);
    }

    public Shape(int color, List<PointF> points, @AnimationType int animationType) {
        super(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setPathEffect(new CornerPathEffect(36f));

        this.points = points;
        this.animationType = animationType;

        path = new Path();
        bounds = new RectF();
        matrix = new Matrix();

        for(int i = 0; i < points.size(); i++) {
            PointF p = points.get(i);
            if(i == 0) {
                path.moveTo(p.x, p.y);
            } else {
                path.lineTo(p.x, p.y);
            }
        }
        path.close();
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(points == null || points.size() < 3) {
            return;
        }

        if(interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }

        Path path = new Path(this.path);

        if((animationType & SCALE) > 0) {
            path.computeBounds(bounds, true);
            matrix.setScale(fraction, fraction, bounds.centerX(), bounds.centerY());
            path.transform(matrix);
        }

        if((animationType & ALPHA) > 0) {
            paint.setAlpha((int)(originAlpha * fraction));
        }

        canvas.drawPath(path, paint);
    }
}

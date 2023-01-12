package me.penguinpistol.analysisdrawing.drawing.model.skin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Circle;
import me.penguinpistol.analysisdrawing.drawing.object.Line;

/**
 * 피부나이 - 피부나이
 */
public class SkinAge extends BaseDrawingModel {
    private static final int HEXAGON_COLUMN_COUNT = 4;
    private static final int HEXAGON_ROW_COUNT = 4;

    public SkinAge(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        overlayColor = Color.parseColor("#40C55A11");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        final List<BaseObject> order1 = new ArrayList<>();

        final RectF hexagonContainer = new RectF(
                landmark171.get(83).x,
                average(extractCoordinates(LANDMARK_171, AXIS_Y, 81, 82, 85, 86, 108, 109, 110, 111, 112)),
                landmark171.get(87).x,
                average(extractCoordinates(LANDMARK_171, AXIS_Y, 50, 58))
        );
        final List<Integer> drawIndexes = Arrays.asList(0, 1, 2, 5, 8, 9, 10, 11, 12, 14);
        final List<Integer> offsetRows = Arrays.asList(0, 1, 3);
        final float hexagonWidth = hexagonContainer.width() * (1F / HEXAGON_COLUMN_COUNT);
        final float hexagonHeight = hexagonContainer.height() * (1F / HEXAGON_ROW_COUNT);

        for (int i = 0; i < HEXAGON_COLUMN_COUNT * HEXAGON_ROW_COUNT; i++) {
            final int column = i % HEXAGON_COLUMN_COUNT;
            final int row = i / HEXAGON_COLUMN_COUNT;
            final float x = hexagonContainer.left + (hexagonWidth * column);
            final float y = hexagonContainer.top + (hexagonHeight * row);

            if(drawIndexes.contains(i)) {
                final float offset = offsetRows.contains(row) ? hexagonWidth * 0.5F : 0;
                order1.addAll(makeHexagon(x + offset, y, x + offset + hexagonWidth, y + hexagonHeight));
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        final List<PointF> points = Arrays.asList(
                landmark171.get(0),
                landmark171.get(15),
                landmark171.get(40),
                averagePoint(extractPoints(LANDMARK_171, 120, 121).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 140, 141).toArray(new PointF[0]))
        );
        Collections.shuffle(points);
        final long circlePlayTime = 100;
        for(int i = 0; i < points.size(); i++) {
            final PointF p = points.get(i);
            final List<BaseObject> circle = Collections.singletonList(new Circle(DrawingConfig.CIRCLE_OUTER_COLOR, DrawingConfig.CIRCLE_INNER_COLOR, p, defaultCircleRadius));
            orders.add(new Order(circle, DrawingConfig.DEFAULT_PLAY_TIME + circlePlayTime * i, circlePlayTime));
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }

    private List<Line> makeHexagon(float l, float t, float r, float b) {
        float quarterWidth = (r - l) * 0.25F;
        float halfHeight = (b - t) * 0.5F;
        final PointF[] points = new PointF[] {
                new PointF(l, t + halfHeight)
                , new PointF(l + quarterWidth, t)
                , new PointF(r - quarterWidth, t)
                , new PointF(r, t + halfHeight)
                , new PointF(r - quarterWidth, b)
                , new PointF(l + quarterWidth, b)
        };
        final List<Line> result = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            final int j = (i+1) % points.length;
            result.add(createSharpLine(points[i], points[j], Color.parseColor("#B3FFFFFF")));
        }

        return result;
    }
}

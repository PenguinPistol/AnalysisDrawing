package me.penguinpistol.analysisdrawing.drawing.model.skin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Shape;

public class SkinType extends BaseDrawingModel {

    public SkinType(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        overlayColor = Color.parseColor("#40C55A11");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();
        final int shapeColor = Color.parseColor("#26EFEF5D");

        // 왼쪽 볼
        List<PointF> leftCheek = Stream.of(
                averagePoint(extractPoints(LANDMARK_171, 84, 113, 128).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 131, 132).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 116, 119).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 121, 122, 125, 126).toArray(new PointF[0]))
        ).collect(Collectors.toList());
        order1.add(new Shape(shapeColor, leftCheek));

        // 오른쪽 볼
        List<PointF> rightCheek = Stream.of(
                averagePoint(extractPoints(LANDMARK_171, 148, 149).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 152, 133).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 141, 142, 145, 146).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 136, 139).toArray(new PointF[0]))
        ).collect(Collectors.toList());
        order1.add(new Shape(shapeColor, rightCheek));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        // U Zone
        List<PointF> uzone = Stream.of(
                averagePoint(extractPoints(LANDMARK_171, 95, 96, 158).toArray(new PointF[0])),
                landmark171.get(158),
                landmark171.get(159),
                landmark171.get(160),
                landmark171.get(161),
                landmark171.get(162),
                landmark171.get(163),
                landmark171.get(164),
                landmark171.get(165),
                landmark171.get(166),
                landmark171.get(167),
                landmark171.get(168),
                averagePoint(extractPoints(LANDMARK_171, 100, 102, 168).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 99, 165).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 98, 163).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 97, 161).toArray(new PointF[0]))
        ).collect(Collectors.toList());
        order2.add(new Shape(shapeColor, uzone));

        // T Zone
        List<PointF> tzone = Stream.of(
                landmark171.get(82),
                averagePoint(extractPoints(LANDMARK_171, 81, 82, 108).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 85, 86, 112).toArray(new PointF[0])),
                landmark171.get(86),
                averagePoint(extractPoints(LANDMARK_171, 33, 112).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 31, 111).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 30, 110).toArray(new PointF[0])),
                landmark171.get(46),
                landmark171.get(48),
                landmark171.get(43),
                averagePoint(extractPoints(LANDMARK_171, 25, 110).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 24, 109).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 22, 108).toArray(new PointF[0]))
        ).collect(Collectors.toList());
        order2.add(new Shape(shapeColor, tzone));

        order2.add(getInfoTextObject("%s피부"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

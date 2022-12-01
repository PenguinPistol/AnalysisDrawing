package me.penguinpistol.analysisdrawing.drawing.model.face;

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

public class FrontCheek extends BaseDrawingModel {
    public FrontCheek(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        List<PointF> leftPoints = Stream.of(
                averagePoint(extractPoints(LANDMARK_171, 113, 114, 128, 129).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 116, 131, 132).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 118, 121, 122).toArray(new PointF[0]))
        ).collect(Collectors.toList());

        List<PointF> rightPoints = Stream.of(
                averagePoint(extractPoints(LANDMARK_171, 136, 148, 149).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 133, 134, 151).toArray(new PointF[0])),
                averagePoint(extractPoints(LANDMARK_171, 138, 141, 142).toArray(new PointF[0]))
        ).collect(Collectors.toList());

        order1.add(new Shape(Color.parseColor("#80FFFFFF"), leftPoints));
        order1.add(new Shape(Color.parseColor("#80FFFFFF"), rightPoints));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        order2.add(getInfoTextObject("볼의 볼륨감이\n%s"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        data.details.frontCheek
        // 볼의 볼륨감 유무
        frontCheekValue
         */
    }
}

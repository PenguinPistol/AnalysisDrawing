package me.penguinpistol.analysisdrawing.drawing.model.skin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Shape;

/**
 * 모공 - 모공
 */
public class Pore extends BaseDrawingModel {

    public Pore(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        overlayColor = Color.parseColor("#40C55A11");
        int fillColor = Color.parseColor("#26FFFFCC");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        // 이마
        PointF foreheadCircleCenter = landmark171.get(110);
        float foreheadCircleRadius = average(extractCoordinates(LANDMARK_171, AXIS_X, 111, 112)) - foreheadCircleCenter.x;
        order1.add(createCircle(foreheadCircleCenter, foreheadCircleRadius));

        // 미간
        float betweenEyebrowCircleX = landmark171.get(40).x;
        float betweenEyebrowCircleY = average(extractCoordinates(LANDMARK_171, AXIS_Y, 40, 41)) - 15;
        float betweenEyebrowCircleRadius = (landmark171.get(30).x - landmark171.get(25).x) * 0.5F;
        order1.add(createCircle(betweenEyebrowCircleX, betweenEyebrowCircleY, betweenEyebrowCircleRadius));

        // 나비존
        int lineColor = Color.parseColor("#66FFFFFF");
        // TODO 나비존 외각선 그리기
        order1.add(new Shape(fillColor,
                extractPoints(LANDMARK_171, 128, 129, 130, 131, 132, 42, 45, 148, 149, 150, 151, 152, 133, 137, 142, 146, 47, 48, 44, 126, 122, 117, 113),
                Shape.SCALE));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

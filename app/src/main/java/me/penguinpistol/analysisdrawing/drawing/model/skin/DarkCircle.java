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
 * 다크서클 - 다크서클
 */
public class DarkCircle extends BaseDrawingModel {

    public DarkCircle(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        overlayColor = Color.parseColor("#40C55A11");
        final int darkCircleColor = Color.parseColor("#33522846");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        // 왼쪽 다크서클
        float leftOffsetY = (landmark171.get(131).y - landmark171.get(8).y) * -0.5F;
        int[] leftIndexes = new int[] { 113, 115, 132, 131, 130, 129 };
        Shape leftShape = new Shape(darkCircleColor, extractPoints(LANDMARK_171, 0, leftOffsetY, leftIndexes));
        order1.add(leftShape);

        // 오른쪽 다크서클
        float rightOffsetY = (landmark171.get(149).y - landmark171.get(17).y) * -0.5F;
        int[] rightIndexes = new int[] { 133, 135, 148, 149, 150, 151 };
        Shape rightShape = new Shape(darkCircleColor, extractPoints(LANDMARK_171, 0, rightOffsetY, rightIndexes));
        order1.add(rightShape);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

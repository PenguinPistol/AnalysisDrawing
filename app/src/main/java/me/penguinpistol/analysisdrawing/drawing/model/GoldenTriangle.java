package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.JointLine;
import me.penguinpistol.analysisdrawing.drawing.object.Line;

public class GoldenTriangle extends BaseDrawingModel {

    public GoldenTriangle(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        PointF[] triangle = new PointF[] {
                landmark171.get(0),
                landmark171.get(15),
                landmark171.get(54)
        };

        List<BaseObject> order1 = new ArrayList<>();
        order1.add(new Line(triangle[0], triangle[1], DrawingConfig.LINE_COLOR, defaultThickness));
        order1.add(new Line(triangle[1], triangle[2], DrawingConfig.LINE_COLOR, defaultThickness));
        order1.add(new Line(triangle[2], triangle[0], DrawingConfig.LINE_COLOR, defaultThickness));

        int[] lipLineIndexes = new int[] {
                50, 51, 52, 53, 54, 55, 56, 57, 58,
                79, 78, 77, 76, 75, 74, 73, 50, 66,
                67, 68, 69, 70, 72, 58
        };
        order1.add(new JointLine(Color.MAGENTA, extractPoints(LANDMARK_171, lipLineIndexes), defaultThickness));

        orders.add(new Order(order1, 0, 1000));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

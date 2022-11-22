package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

public class NoseLength extends BaseDrawingModel {

    public NoseLength(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float horizontalStartX = landmark118.get(56).x;
        float horizontalEndX = landmark118.get(68).x;

        float eyebrowY = landmark171.get(40).y;
        float noseLowerY = landmark171.get(49).y;
        float lipUpperY = min(extractCoordinates(LANDMARK_118, AXIS_Y, 88, 89, 90));
        float lipLowerY = landmark118.get(95).y;
        float jawLowerY = landmark118.get(16).y;

        order1.add(createShapeLine(horizontalStartX, eyebrowY, horizontalEndX, eyebrowY));
        order1.add(createShapeLine(horizontalStartX, noseLowerY, horizontalEndX, noseLowerY));
        order1.add(createShapeLine(horizontalStartX, lipUpperY, horizontalEndX, lipUpperY));
        order1.add(createShapeLine(horizontalStartX, lipLowerY, horizontalEndX, lipLowerY));
        order1.add(createShapeLine(horizontalStartX, jawLowerY, horizontalEndX, jawLowerY));

        float verticalMargin = 35;
        float noseLeftX = landmark118.get(77).x;
        float noseRightX = landmark118.get(83).x;

        order1.add(createShapeLine(noseLeftX, eyebrowY - verticalMargin, noseLeftX, jawLowerY + verticalMargin));
        order1.add(createShapeLine(noseRightX, eyebrowY - verticalMargin, noseRightX, jawLowerY + verticalMargin));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        order2.add(createArrow(horizontalEndX, eyebrowY, horizontalEndX, noseLowerY));
        order2.add(createText(horizontalEndX + 8, average(eyebrowY, noseLowerY), "%.1fcm", Text.Anchor.LEFT_CENTER));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order3 = new ArrayList<>();

        final int[] nosePath = new int[] {
                75, 76, 77, 78, 80, 82, 83, 84, 85
        };
        order3.add(createJointLine(extractPoints(LANDMARK_118, nosePath), false));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order3, DrawingConfig.DEFAULT_PLAY_TIME, 1000));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

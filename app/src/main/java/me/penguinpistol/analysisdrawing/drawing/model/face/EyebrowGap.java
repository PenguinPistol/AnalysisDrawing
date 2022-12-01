package me.penguinpistol.analysisdrawing.drawing.model.face;

import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 눈썹 - 눈썹 간격
 */
public class EyebrowGap extends BaseDrawingModel {

    public EyebrowGap(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float eyebrowUpperY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 34, 35, 44, 45));
        float eyebrowLowerY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 55, 61, 72));
        float leftInnerX = landmark118.get(37).x;
        float rightInnerX = landmark118.get(42).x;
        order1.add(createDashLine(leftInnerX, eyebrowUpperY, leftInnerX, eyebrowLowerY));
        order1.add(createDashLine(rightInnerX, eyebrowUpperY, rightInnerX, eyebrowLowerY));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        float eyebrowGapY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 37, 42));
        order2.add(createArrow(leftInnerX, eyebrowGapY, rightInnerX, eyebrowGapY));

        order2.add(createText(average(leftInnerX, rightInnerX), eyebrowGapY, "%.1fcm", Text.Anchor.CENTER_BOTTOM));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order3 = new ArrayList<>();

        // 왼쪽 눈썹 윤곽선
        final int[] leftEyebrowPath = new int[] {
                37, 36, 35, 34, 33, 41, 40, 39, 38
        };
        order3.add(createJointLine(extractPoints(LANDMARK_118, leftEyebrowPath)));

        // 오른쪽 눈썹 윤곽선
        final int[] rightEyebrowPath = new int[] {
                42, 43, 44, 45, 46, 47, 48, 49, 50
        };
        order3.add(createJointLine(extractPoints(LANDMARK_118, rightEyebrowPath)));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order3, DrawingConfig.DEFAULT_PLAY_TIME, 1000));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

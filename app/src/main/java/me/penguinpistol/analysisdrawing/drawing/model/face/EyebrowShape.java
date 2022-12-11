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
 * 눈썹 - 눈썹 모양
 */
public class EyebrowShape extends BaseDrawingModel {

    public EyebrowShape(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        final float dashOffset = 15F;
        final float textOffset = 20f;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        // 왼쪽 눈썹 rect 점선
        float leftOuterX = landmark118.get(33).x;
        float leftInnerX = landmark118.get(37).x;
        float leftUpperY = min(extractCoordinates(LANDMARK_118, AXIS_Y, 34, 35));
        float leftLowerY = landmark118.get(38).y;
        order1.add(createDashLine(leftOuterX - dashOffset, leftUpperY, leftInnerX + dashOffset, leftUpperY));
        order1.add(createDashLine(leftOuterX - dashOffset, leftLowerY, leftInnerX + dashOffset, leftLowerY));
        order1.add(createDashLine(leftOuterX, leftUpperY - dashOffset, leftOuterX, leftLowerY + dashOffset));
        order1.add(createDashLine(leftInnerX, leftUpperY - dashOffset, leftInnerX, leftLowerY + dashOffset));

        // 오른쪽 눈썹 rect 점선
        float rightOuterX = landmark118.get(46).x;
        float rightInnerX = landmark118.get(42).x;
        float rightUpperY = min(extractCoordinates(LANDMARK_118, AXIS_Y, 44, 45));
        float rightLowerY = landmark118.get(50).y;
        order1.add(createDashLine(rightOuterX + dashOffset, rightUpperY, rightInnerX - dashOffset, rightUpperY));
        order1.add(createDashLine(rightOuterX + dashOffset, rightLowerY, rightInnerX - dashOffset, rightLowerY));
        order1.add(createDashLine(rightOuterX, rightUpperY - dashOffset, rightOuterX, rightLowerY + dashOffset));
        order1.add(createDashLine(rightInnerX, rightUpperY - dashOffset, rightInnerX, rightLowerY + dashOffset));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        // 왼쪽 눈썹길이
        order2.add(createText(average(leftOuterX, leftInnerX), leftUpperY - textOffset, "%.1fcm", Text.Anchor.CENTER_BOTTOM));
        // 오른쪽 눈썹길이
        order2.add(createText(average(rightOuterX, rightInnerX), rightUpperY - textOffset, "%.1fcm", Text.Anchor.CENTER_BOTTOM));
        // 왼쪽 눈썹높이
        order2.add(createText(leftOuterX - textOffset, average(leftUpperY, leftLowerY), "%.1fcm", Text.Anchor.RIGHT_CENTER));
        // 오른쪽 눈썹 높이
        order2.add(createText(rightOuterX + textOffset, average(rightUpperY, rightLowerY), "%.1fcm", Text.Anchor.LEFT_CENTER));

        order2.add(getInfoTextObject("눈썹형태텍스트"));

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
        /*
        // 눈썹길이
        data.partAllScore.eyebrowLength.left.cm
        data.partAllScore.eyebrowLength.right.cm
        // 눈썹높이
        data.partAllScore.eyebrowHeight.left.cm
        data.partAllScore.eyebrowHeight.right.cm
        // 눈썹형태
        data.partAllScore.eyebrowShape
         */
    }
}

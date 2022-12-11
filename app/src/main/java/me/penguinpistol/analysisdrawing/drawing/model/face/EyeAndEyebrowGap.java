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
import me.penguinpistol.analysisdrawing.drawing.object.Arrow;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 쌍꺼풀 - 눈과 눈썹거리
 */
public class EyeAndEyebrowGap extends BaseDrawingModel {

    public EyeAndEyebrowGap(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float leftEyebrowBottomY = landmark118.get(40).y;
        float leftEyeTopY = landmark118.get(53).y;
        float overLeftEyeInnerX = average(extractCoordinates(LANDMARK_118, AXIS_X, 55, 75));
        float leftEyebrowOuterX = landmark118.get(33).x;
        order1.add(createShapeLine(leftEyebrowOuterX, leftEyebrowBottomY, overLeftEyeInnerX, leftEyebrowBottomY));
        order1.add(createShapeLine(leftEyebrowOuterX, leftEyeTopY, overLeftEyeInnerX, leftEyeTopY));

        float rightEyebrowBottomY = landmark118.get(48).y;
        float rightEyeTopY = landmark118.get(63).y;
        float overRightEyeInnerX = average(extractCoordinates(LANDMARK_118, AXIS_X, 65, 85));
        float rightEyebrowOuterX = landmark118.get(46).x;
        order1.add(createShapeLine(rightEyebrowOuterX, rightEyebrowBottomY, overRightEyeInnerX, rightEyebrowBottomY));
        order1.add(createShapeLine(rightEyebrowOuterX, rightEyeTopY, overRightEyeInnerX, rightEyeTopY));

        order1.add(createDashLine(landmark118.get(51).x, landmark118.get(53).y, landmark118.get(51).x, landmark118.get(57).y));
        order1.add(createDashLine(landmark118.get(65).x, landmark118.get(63).y, landmark118.get(65).x, landmark118.get(67).y));

        order1.add(createDashLine(landmark118.get(51).x, landmark118.get(57).y, landmark118.get(55).x, landmark118.get(57).y));
        order1.add(createDashLine(landmark118.get(61).x, landmark118.get(67).y, landmark118.get(65).x, landmark118.get(67).y));

        order1.add(new Arrow(landmark118.get(33).x, landmark118.get(40).y, landmark118.get(33).x, landmark118.get(53).y, DrawingConfig.LINE_COLOR));
        order1.add(new Arrow(landmark118.get(46).x, landmark118.get(48).y, landmark118.get(46).x, landmark118.get(63).y, DrawingConfig.LINE_COLOR));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        order2.add(createText(leftEyebrowOuterX, leftEyeTopY, "%.1fcm", Text.Anchor.CENTER_TOP));
        order2.add(createText(rightEyebrowOuterX, rightEyeTopY, "%.1fcm", Text.Anchor.CENTER_TOP));

        order2.add(getInfoTextObject("눈과 눈썹거리\nL: %.1fcm\nR: %.2fcm"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 눈높이
        data.details.heightOfAnEye.heightOfAnEyeLeft.cm
        data.details.heightOfAnEye.heightOfAnEyeRight.cm
        // 눈과 눈썹거리(좌상단)
        data.partAllScore.eyetoEyeBrowDistance.left.length.cm
        data.partAllScore.eyetoEyeBrowDistance.right.length.cm
         */
    }
}

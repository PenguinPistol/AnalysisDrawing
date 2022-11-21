package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.object.Arrow;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Line;

public class EyeSize extends BaseDrawingModel {
    private static final float MARGIN = 25F;

    public EyeSize(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        // 눈 기본 좌표
        float leftEyeStartX = landmark118.get(51).x;
        float leftEyeOuterX = landmark118.get(33).x;
        float leftEyeEndX = landmark118.get(55).x;
        float rightEyeStartX = landmark118.get(61).x;
        float rightEyeOuterX = landmark118.get(46).x;
        float rightEyeEndX = landmark118.get(65).x;

        List<BaseObject> order1 = new ArrayList<>();
        float leftEyeTopY = landmark118.get(53).y;
        float leftEyeBottomY = landmark118.get(57).y;
        float leftEyeStartOverX = average(leftEyeStartX, leftEyeOuterX);
        float leftEyeEndOverX = average(leftEyeEndX, landmark118.get(75).x) + 5;
        order1.add(new Line(leftEyeStartOverX, leftEyeTopY, leftEyeEndOverX, leftEyeTopY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(leftEyeStartOverX, leftEyeBottomY, leftEyeEndOverX, leftEyeBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        float rightEyeTopY = landmark118.get(63).y;
        float rightEyeBottomY = landmark118.get(67).y;
        float rightEyeStartOverX = average(rightEyeStartX, landmark118.get(85).x) - 5;
        float rightEyeEndOverX = average(rightEyeEndX, rightEyeOuterX);
        order1.add(new Line(rightEyeStartOverX, rightEyeTopY, rightEyeEndOverX, rightEyeTopY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(rightEyeStartOverX, rightEyeBottomY, rightEyeEndOverX, rightEyeBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 눈 세로 점선
        float eyeHeightTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 0, 32, 33, 46));
        float eyeHeightBottomY = landmark118.get(72).y;
        order1.add(new Line(leftEyeStartX, eyeHeightTopY, leftEyeStartX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(leftEyeEndX, eyeHeightTopY, leftEyeEndX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(rightEyeStartX, eyeHeightTopY, rightEyeStartX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(rightEyeEndX, eyeHeightTopY, rightEyeEndX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        List<BaseObject> order2 = new ArrayList<>();

        // 좌우 눈 아래 가로 화살표
        order2.add(new Arrow(leftEyeStartX - MARGIN, eyeHeightBottomY, leftEyeEndX + MARGIN, eyeHeightBottomY, DrawingConfig.LINE_COLOR));
        order2.add(new Arrow(rightEyeStartX - MARGIN, eyeHeightBottomY, rightEyeEndX + MARGIN, eyeHeightBottomY, DrawingConfig.LINE_COLOR));

        // 좌우 눈 끝 세로 화살표
        order2.add(new Arrow(leftEyeOuterX, eyeHeightTopY, leftEyeOuterX, eyeHeightBottomY, DrawingConfig.LINE_COLOR));
        order2.add(new Arrow(rightEyeOuterX, eyeHeightTopY, rightEyeOuterX, eyeHeightBottomY, DrawingConfig.LINE_COLOR));

        order2.add(getInfoTextObject("눈높이\nL: %.2fcm\nR: %.2fcm\n눈너비\nL: %.2fcm\nR: %.2fcm"));

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

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
import me.penguinpistol.analysisdrawing.drawing.object.Line;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 눈 - 눈 간격
 */
public class EyeSpacing extends BaseDrawingModel {

    public EyeSpacing(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        // 눈 기본 좌표
        float leftEyeStartX = landmark118.get(51).x;
        float leftEyeEndX = landmark118.get(55).x;
        float rightEyeStartX = landmark118.get(61).x;
        float rightEyeEndX = landmark118.get(65).x;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float eyeHeightTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 38, 50));
        float eyeHeightBottomY = landmark118.get(73).y;
        // 양쪽 눈 끝점 세로 선
        order1.add(new Line(leftEyeStartX, eyeHeightTopY, leftEyeStartX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(leftEyeEndX, eyeHeightTopY, leftEyeEndX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(rightEyeStartX, eyeHeightTopY, rightEyeStartX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(rightEyeEndX, eyeHeightTopY, rightEyeEndX, eyeHeightBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        float eyeSpacingY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 55, 61));
        float eyeWidthY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 72, 73));
        // 눈 간격 가로 화살표 / 텍스트
        order2.add(new Arrow(leftEyeEndX, eyeSpacingY, rightEyeStartX, eyeSpacingY, DrawingConfig.LINE_COLOR));
        order2.add(new Text(average(leftEyeEndX, rightEyeStartX), eyeSpacingY, "%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.CENTER_TOP));
        // 왼쪽 눈 너비 가로 화살표 / 텍스트
        order2.add(new Arrow(leftEyeStartX, eyeWidthY, leftEyeEndX, eyeWidthY, DrawingConfig.LINE_COLOR));
        order2.add(new Text(average(leftEyeStartX, leftEyeEndX), eyeWidthY, "%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.CENTER_TOP));
        // 오른쪽 눈 너비 가로 화살표 / 텍스트
        order2.add(new Arrow(rightEyeStartX, eyeWidthY, rightEyeEndX, eyeWidthY, DrawingConfig.LINE_COLOR));
        order2.add(new Text(average(rightEyeStartX, rightEyeEndX), eyeWidthY, "%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.CENTER_TOP));

        order2.add(getInfoTextObject("눈 간격\n%.2fcm"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 눈너비
        data.partAllScore.widthOfAnEye.left.cm
        data.partAllScore.widthOfAnEye.right.cm
        // 눈간격
        data.partAllScore.eyeDistance
         */
    }
}

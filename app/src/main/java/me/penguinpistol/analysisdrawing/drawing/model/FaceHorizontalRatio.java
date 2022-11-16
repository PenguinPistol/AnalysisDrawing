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
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 얼굴비율 - 가로비율
 */
public class FaceHorizontalRatio extends BaseDrawingModel {

    public FaceHorizontalRatio(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        // 가로비율 세로실선 시작 X좌표
        int[] verticalSolidLineXIndex = new int[] { 84, 0, 5, 10, 15, 88 };
        // 좌우 눈꺼풀중 위에 있는 Y좌표
        float upperEyelidY = min(extractCoordinates(LANDMARK_171, AXIS_Y, 2, 13));
        // 좌우 눈썹, 미간의 Y좌표 평균
        float averageEyebrowBottomY = average(extractCoordinates(LANDMARK_171, AXIS_Y, 20, 35, 40));
        // 좌우 턱라인 Y좌표 평균
        float averageJawlineY = average(extractCoordinates(LANDMARK_171, AXIS_Y, 95, 96, 100, 101));

        // 좌우 비율 세로선
        List<BaseObject> order1 = new ArrayList<>();
        for(float x : extractCoordinates(LANDMARK_171, AXIS_X, verticalSolidLineXIndex)) {
            order1.add(new Line(x, averageEyebrowBottomY, x, averageJawlineY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        }

        // 눈 윗선, 아랫선
        float eyeLineStartX = landmark171.get(84).x;
        float eyeLineEndX = landmark171.get(88).x;
        order1.add(new Line(eyeLineStartX, upperEyelidY, eyeLineEndX, upperEyelidY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(eyeLineStartX, landmark171.get(152).y, eyeLineEndX, landmark171.get(152).y, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        List<BaseObject> order2 = new ArrayList<>();
        // 좌상단 텍스트
        order2.add(getInfoTextObject("이상적인 가로비율\n0.65:1:1:1:0.65\n\n나의 가로비율\n%.2f:%.2f:%.2f:%.2f:%.2f"));

        // 눈 너비, 높이, 간격
        float eyeWidthHeightArrowY = average(extractCoordinates(LANDMARK_171, AXIS_Y, 130, 150));
        order2.add(new Arrow(landmark171.get(0).x, eyeWidthHeightArrowY, landmark171.get(5).x, eyeWidthHeightArrowY, DrawingConfig.LINE_COLOR));
        order2.add(new Arrow(landmark171.get(5).x, landmark171.get(48).y, landmark171.get(10).x, landmark171.get(48).y, DrawingConfig.LINE_COLOR));
        order2.add(new Arrow(landmark171.get(10).x, eyeWidthHeightArrowY, landmark171.get(15).x, eyeWidthHeightArrowY, DrawingConfig.LINE_COLOR));

        // TODO Json 파싱 데이터 텍스트 출력
        // 눈높이 X 좌표
        float textX1 = average(extractCoordinates(LANDMARK_171, AXIS_X, 0, 5));
        // 눈간격 X 좌표
        float textX2 = average(extractCoordinates(LANDMARK_171, AXIS_X, 5, 10));
        // 눈너비 X 좌표
        float textX3 = average(extractCoordinates(LANDMARK_171, AXIS_X, 10, 15));
        order2.add(new Text(textX1, eyeWidthHeightArrowY, "눈높이\n%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_TOP));
        order2.add(new Text(textX2, landmark171.get(48).y, "눈간격\n%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_TOP));
        order2.add(new Text(textX3, eyeWidthHeightArrowY, "눈너비\n%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_TOP));

        orders.add(new Order(order1, 0, 500));
        orders.add(new Order(order2, 600, 500));
    }

    @Override
    protected void parseJson(JsonElement json) {
    }
}

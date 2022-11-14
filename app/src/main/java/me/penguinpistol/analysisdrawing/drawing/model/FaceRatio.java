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
import me.penguinpistol.analysisdrawing.drawing.object.Arrow;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Circle;
import me.penguinpistol.analysisdrawing.drawing.object.Line;

public class FaceRatio extends BaseDrawingModel {

    public FaceRatio(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders() {
        // 가로비율 세로실선 시작 X좌표
        int[] verticalSolidLineXIndex = new int[] { 84, 0, 5, 10, 15, 88 };
        // 좌우 눈꺼풀중 위에 있는 Y좌표
        float upperEyelidY = max(extractPoints(LANDMARK_171, AXIS_Y, 2, 13));
        // 좌우 눈썹, 미간의 Y좌표 평균
        float averageEyebrowBottomY = average(extractPoints(LANDMARK_171, AXIS_Y, 20, 35, 40));
        // 좌우 턱라인 Y좌표 평균
        float averageJawlineY = average(extractPoints(LANDMARK_171, AXIS_Y, 95, 96, 100, 101));

        // 좌우 비율 세로선
        List<BaseObject> order1 = new ArrayList<>();
        for(float x : extractPoints(LANDMARK_171, AXIS_X, verticalSolidLineXIndex)) {
            order1.add(new Line(x, averageEyebrowBottomY, x, averageJawlineY, DrawingConfig.LINE_COLOR, lineThickness, Line.SHARP));
        }

        // 눈 윗선, 아랫선
        float eyeLineStartX = landmark171.get(84).x;
        float eyeLineEndX = landmark171.get(88).x;
        order1.add(new Line(eyeLineStartX, upperEyelidY, eyeLineEndX, upperEyelidY, DrawingConfig.LINE_COLOR, lineThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(eyeLineStartX, landmark171.get(152).y, eyeLineEndX, landmark171.get(152).y, DrawingConfig.LINE_COLOR, lineThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        List<BaseObject> order2 = new ArrayList<>();
        // 좌상단 텍스트
        order2.add(getInfoTextObject("이상적인 가로비율\n0.65:1:1:1:0.65\n\n나의 가로비율\n%.2f:%.2f:%.2f:%.2f:%.2f"));

        // 눈 너비, 높이, 간격
        float eyeWidthHeightArrowY = average(extractPoints(LANDMARK_171, AXIS_Y, 130, 150));
        order2.add(new Arrow(landmark171.get(0).x, eyeWidthHeightArrowY, landmark171.get(5).x, eyeWidthHeightArrowY, DrawingConfig.LINE_COLOR));
        order2.add(new Arrow(landmark171.get(5).x, landmark171.get(48).y, landmark171.get(10).x, landmark171.get(48).y, DrawingConfig.LINE_COLOR));
        order2.add(new Arrow(landmark171.get(10).x, eyeWidthHeightArrowY, landmark171.get(15).x, eyeWidthHeightArrowY, DrawingConfig.LINE_COLOR));

        // TODO Json 파싱 데이터 텍스트 출력

        List<BaseObject> order3 = new ArrayList<>();
        for(PointF p : landmark171) {
            order3.add(new Circle(Color.MAGENTA, Color.WHITE, p.x, p.y, 10));
        }

        orders.add(new Order(order1, 0, 500));
        orders.add(new Order(order2, 600, 500));
//        orders.add(new Order(order3, 500, 500));
    }

    @Override
    protected void parseJson(JsonElement json) {
    }
}

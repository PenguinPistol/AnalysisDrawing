package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.Vector2;
import me.penguinpistol.analysisdrawing.drawing.object.Arc;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Circle;
import me.penguinpistol.analysisdrawing.drawing.object.JointLine;
import me.penguinpistol.analysisdrawing.drawing.object.Line;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 얼굴비율 - 얼굴비대칭
 */
public class FaceAsymmetry extends BaseDrawingModel {
    private final int lineColor = Color.MAGENTA;

    public FaceAsymmetry(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<BaseObject> order1 = new ArrayList<>();

        // 양쪽 눈 바깥쪽 위치벡터
        Vector2 leftEyeOuter = new Vector2(landmark118.get(51));
        Vector2 rightEyeOuter = new Vector2(landmark118.get(65));
        // 양쪽 눈 바깥쪽 평균 Y 좌표
        float eyeLineY = average(leftEyeOuter.y, rightEyeOuter.y);
        order1.add(new Line(leftEyeOuter.x, eyeLineY, rightEyeOuter.x, eyeLineY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 입 양끝 위치벡터
        Vector2 leftMouthOuter = new Vector2(landmark118.get(86));
        Vector2 rightMouthOuter = new Vector2(landmark118.get(92));
        // 입 양끝 평균 Y좌표
        float mouthLineY = average(leftMouthOuter.y, rightMouthOuter.y);
        order1.add(new Line(leftMouthOuter.x, mouthLineY, rightMouthOuter.x, mouthLineY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 얼굴 가운데 가로지르는 세로 선
        PointF faceCenterStart = new PointF(landmark118.get(71).x, average(extractCoordinates(LANDMARK_118, AXIS_Y, 37, 42)));
        PointF faceCenterEnd = new PointF(landmark118.get(71).x, landmark118.get(16).y);
        order1.add(new Line(faceCenterStart, faceCenterEnd, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 이상적인 선
        order1.add(new Line(leftEyeOuter.toPoint(), rightEyeOuter.toPoint(), lineColor, defaultThickness, Line.SHARP));
        order1.add(new Line(leftMouthOuter.toPoint(), rightMouthOuter.toPoint(), lineColor, defaultThickness, Line.SHARP));
        order1.add(new Line(faceCenterStart, faceCenterEnd, lineColor, defaultThickness, Line.SHARP));

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<BaseObject> order2 = new ArrayList<>();

        // 얼굴윤곽 path
        int[] pathPoints = IntStream.range(6, 27).toArray();
        order2.add(new JointLine(lineColor, extractPoints(LANDMARK_118, pathPoints), defaultThickness));

        // 눈 각도
        PointF eyeIntersection = intersection(leftEyeOuter.toPoint(), rightEyeOuter.toPoint(), faceCenterStart, faceCenterEnd);
        float eyeAngle = new Vector2(rightEyeOuter).sub(leftEyeOuter).angle() - 180;
        order2.add(new Arc(lineColor, defaultThickness, eyeIntersection, DrawingConfig.ARC_RADIUS, eyeAngle, Arc.CCW));
        order2.add(new Text(eyeIntersection, "%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.RIGHT_TOP).offset(-20, 0));     // 이상적인 각도
        order2.add(new Text(eyeIntersection, "%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.LEFT_TOP).offset(20, 0));       // 계산된 각도

        // 입 각도
        PointF mouthIntersection = intersection(leftMouthOuter.toPoint(), rightMouthOuter.toPoint(), faceCenterStart, faceCenterEnd);
        float mouthAngle = new Vector2(rightMouthOuter).sub(leftMouthOuter).angle() - 180;
        order2.add(new Arc(lineColor, defaultThickness, mouthIntersection, DrawingConfig.ARC_RADIUS, mouthAngle, Arc.CW));
        order2.add(new Text(mouthIntersection, "%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.RIGHT_TOP).offset(-20, 0));     // 이상적인 각도
        order2.add(new Text(mouthIntersection, "%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.LEFT_TOP).offset(20, 0));       // 계산된 각도

        // 좌상단 텍스트
        order2.add(getInfoTextObject("눈과 입의 평행각도차\n\n턱의 면적\nL : %.2fcm\nR : %.2fcm"));

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<BaseObject> order3 = new ArrayList<>();
        // 얼굴윤곽 닫는 path(직선 길이가 길어 너무 빠르게 애니메이팅되어 분리)
        order3.add(new JointLine(lineColor, extractPoints(LANDMARK_118, 26, 6), defaultThickness));

        orders.add(new Order(order1, 0, 500));
        orders.add(new Order(order2, 500, 500));
        orders.add(new Order(order3, 1000, 500));
        // 포인트
        int[] circlePoints = new int[] { 6, 16, 26, 80 };
        long startDelay = 200;
        for(int i = 0; i < circlePoints.length; i++) {
            List<BaseObject> circle = Collections.singletonList(new Circle(DrawingConfig.CIRCLE_OUTER_COLOR, DrawingConfig.CIRCLE_INNER_COLOR, landmark118.get(circlePoints[i]), defaultCircleRadius));
            orders.add(new Order(circle, 500 + startDelay * i, 200));
        }
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

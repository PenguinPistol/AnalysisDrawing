package me.penguinpistol.analysisdrawing.drawing.model.face;

import android.content.Context;
import android.graphics.Color;
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
import me.penguinpistol.analysisdrawing.drawing.object.JointLine;
import me.penguinpistol.analysisdrawing.drawing.object.Line;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 얼굴비율 - 황금삼각비
 */
public class GoldenTriangle extends BaseDrawingModel {

    public GoldenTriangle(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        final PointF[] triangle = new PointF[] {
                landmark171.get(0),
                landmark171.get(15),
                landmark171.get(54)
        };

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        final int[] lipLineIndexes = new int[] {
                50, 51, 52, 53, 54, 55, 56, 57,
                58, 79, 78, 77, 76, 75, 74, 73,
                50, 66, 67, 68, 69, 70, 72, 58
        };

        // 입술라인
        order1.add(new JointLine(Color.MAGENTA, extractPoints(LANDMARK_171, lipLineIndexes), defaultThickness));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();
        // 삼각형
        order2.add(new Line(triangle[0], triangle[1], DrawingConfig.LINE_COLOR, defaultThickness));
        order2.add(new Line(triangle[1], triangle[2], DrawingConfig.LINE_COLOR, defaultThickness));
        order2.add(new Line(triangle[2], triangle[0], DrawingConfig.LINE_COLOR, defaultThickness));

        // 코끝 선
        float noseLineY = landmark171.get(49).y;
        float noseLineEndX = average(extractCoordinates(LANDMARK_171, AXIS_X, 103, 104));
        order2.add(new Line(landmark171.get(92).x, noseLineY, noseLineEndX, noseLineY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        // 턱끝 선
        float jawLineY = landmark171.get(98).y;
        float jawLineStartX = average(extractCoordinates(LANDMARK_171, AXIS_X, 95, 96));
        float jawLineEndX = average(extractCoordinates(LANDMARK_171, AXIS_X, 100, 101));
        order2.add(new Line(jawLineStartX, jawLineY, jawLineEndX, jawLineY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order3 = new ArrayList<>();
        // 삼각형 꼭짓점 텍스트
        order3.add(new Text(triangle[0], "A\n%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.RIGHT_CENTER));
        order3.add(new Text(triangle[1], "B\n%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.LEFT_CENTER));
        order3.add(new Text(triangle[2], "P\n%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_TOP));

        // 코 <-> 턱끝 세로 화살표, 텍스트
        float noseToJawArrowX = landmark171.get(143).x;
        order3.add(new Arrow(noseToJawArrowX, noseLineY, noseToJawArrowX, jawLineY, DrawingConfig.LINE_COLOR));
        order3.add(new Text(noseToJawArrowX, average(noseLineY, jawLineY), "하정길이\n%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_CENTER));
        order3.add(getInfoTextObject("A포인트\n%.2fº\n\nB포인트\n%.2fº\n\nP포인트\n%.2fº"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, 1000));
        orders.add(new Order(order2, 0, 500));
        orders.add(new Order(order3, 500, 500));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 포인트
        lab_face_ratio.data.partAllScore.goldenTriangleRatioDegree.a
        lab_face_ratio.data.partAllScore.goldenTriangleRatioDegree.b
        lab_face_ratio.data.partAllScore.goldenTriangleRatioDegree.c
        // 하정길이
        lab_face_ratio.data.details.common.upperAndLowerFaceLength.upperAndLowerFaceRatioLowLength
         */
    }
}

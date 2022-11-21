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
import me.penguinpistol.analysisdrawing.drawing.object.Line;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 쌍꺼풀 - 쌍꺼풀
 */
public class DoubleEyelid extends BaseDrawingModel {

    public DoubleEyelid(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        final PointF leftEyebrowOuter = landmark118.get(33);
        final PointF leftEyebrowInner = landmark118.get(38);
        final PointF leftEyeTop = landmark118.get(53);
        final PointF leftEyeBottom = landmark118.get(57);
        final PointF leftEyeOuter = landmark118.get(51);
        final PointF leftPupilCenter = landmark118.get(59);

        final PointF rightEyebrowOuter = landmark118.get(46);
        final PointF rightEyebrowInner = landmark118.get(50);
        final PointF rightEyeTop = landmark118.get(63);
        final PointF rightEyeBottom = landmark118.get(67);
        final PointF rightEyeOuter = landmark118.get(65);
        final PointF rightPupilCenter = landmark118.get(69);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        // 왼쪽 눈 가로 실선(눈썹 아래, 눈 아래, 눈 바깥쪽과 눈동자 사이)
        float leftPupilY = average(leftEyeOuter.y, leftPupilCenter.y);
        float leftEyeOverInnerX = average(extractCoordinates(LANDMARK_118, AXIS_X, 71, 75));

        order1.add(new Line(leftEyebrowOuter.x, leftEyebrowInner.y, leftEyeOverInnerX, leftEyebrowInner.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(leftEyebrowOuter.x, leftEyeBottom.y, leftEyeOverInnerX, leftEyeBottom.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(leftEyeOuter.x, leftPupilY, leftPupilCenter.x, leftPupilY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 오른쪽 눈 가로 실선(눈썹 아래, 눈 아래, 눈 바깥쪽과 눈동자 사이)
        float rightPupilY = average(rightEyeOuter.y, rightPupilCenter.y);
        float rightEyeOverInnerX = average(extractCoordinates(LANDMARK_118, AXIS_X, 71, 85));

        order1.add(new Line(rightEyebrowOuter.x, rightEyebrowInner.y, rightEyeOverInnerX, rightEyebrowInner.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(rightEyebrowOuter.x, rightEyeBottom.y, rightEyeOverInnerX, rightEyeBottom.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(rightEyeOuter.x, rightPupilY, rightPupilCenter.x, rightPupilY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 왼쪽 눈 바깥쪽, 눈동자 중심 세로선
        float leftPupilCenterX = average(leftEyeTop.x, leftEyeBottom.x);
        order1.add(new Line(leftPupilCenterX, leftEyeTop.y, leftPupilCenterX, leftEyeBottom.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(leftEyeOuter.x, leftEyeTop.y, leftEyeOuter.x, leftEyeBottom.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 오른쪽 눈 바깥쪽, 눈동자 중심 세로선
        float rightPupilCenterX = average(rightEyeTop.x, rightEyeBottom.x);
        order1.add(new Line(rightPupilCenterX, rightEyeTop.y, rightPupilCenterX, rightEyeBottom.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(rightEyeOuter.x, rightEyeTop.y, rightEyeOuter.x, rightEyeBottom.y, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 양쪽 눈 세로 점선
        float leftOuterVerticalX = average(extractCoordinates(LANDMARK_118, AXIS_X, 33, 41));
        float leftEyebrowTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 33, 37, 38));
        float rightOuterVerticalX = average(extractCoordinates(LANDMARK_118, AXIS_X, 46, 47));
        float rightEyebrowTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 42, 46, 50));
        float verticalBottomY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 72, 73));
        order1.add(new Line(leftOuterVerticalX, leftEyebrowTopY, leftOuterVerticalX, verticalBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(landmark118.get(75).x, leftEyebrowTopY, landmark118.get(75).x, verticalBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(landmark118.get(85).x, rightEyebrowTopY, landmark118.get(85).x, verticalBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(rightOuterVerticalX, rightEyebrowTopY, rightOuterVerticalX, verticalBottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        float textMarginTop = 16;
        order2.add(new Text(average(leftPupilCenterX, leftEyeOuter.x), leftEyeBottom.y + textMarginTop, "%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.CENTER_CENTER));
        order2.add(new Text(average(rightPupilCenterX, rightEyeOuter.x), rightEyeBottom.y + textMarginTop, "%.2fcm", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.CENTER_CENTER));

        order2.add(getInfoTextObject("쌍꺼풀 유무\nL: %s\nR: %s"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

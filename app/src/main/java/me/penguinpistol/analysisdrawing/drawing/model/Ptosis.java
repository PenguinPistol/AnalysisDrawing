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
 * 쌍꺼풀 - 안검하수
 */
public class Ptosis extends BaseDrawingModel {

    public Ptosis(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        final float expand = 20F;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float leftPupilY = landmark118.get(59).y;
        float rightPupilY = landmark118.get(69).y;
        float leftEyeTopY = landmark118.get(53).y;
        float rightEyeTopY = landmark118.get(63).y;

        float expandedLeftEyeOuterX = landmark118.get(51).x - expand;
        float expandedLeftEyeInnerX = landmark118.get(55).x + expand;
        float expandedRightEyeInnerX = landmark118.get(61).x - expand;
        float expandedRightEyeOuterX = landmark118.get(65).x + expand;
        order1.add(new Line(expandedLeftEyeOuterX, leftPupilY, expandedLeftEyeInnerX, leftPupilY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(expandedRightEyeOuterX, rightPupilY, expandedRightEyeInnerX, rightPupilY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        order1.add(new Line(expandedLeftEyeOuterX, leftEyeTopY, expandedLeftEyeInnerX, leftEyeTopY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(expandedRightEyeOuterX, rightEyeTopY, expandedRightEyeInnerX, rightEyeTopY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        float arrowTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 0, 32, 33, 46));
        float arrowBottomY = landmark118.get(72).y;
        float arrowLeftX = landmark118.get(33).x;
        float arrowRightX = landmark118.get(46).x;
        order1.add(new Arrow(arrowLeftX, arrowTopY, arrowLeftX, arrowBottomY, DrawingConfig.LINE_COLOR));
        order1.add(new Arrow(arrowRightX, arrowTopY, arrowRightX, arrowBottomY, DrawingConfig.LINE_COLOR));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        order2.add(new Text(landmark118.get(57).x, arrowBottomY, "L\n%.1f%.1f", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_TOP));
        order2.add(new Text(landmark118.get(67).x, arrowBottomY, "R\n%.1f%.1f", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_TOP));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
    }
}

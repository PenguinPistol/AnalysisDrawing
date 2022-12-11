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
import me.penguinpistol.analysisdrawing.drawing.object.Line;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 얼굴비율 - 세로비율
 */
public class FaceVerticalRatio extends BaseDrawingModel {

    public FaceVerticalRatio(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        // 상단 가로실선 Y 좌표
        float topY = landmark171.get(40).y;
        // 중단 가로실선 좌표
        float middleY = landmark171.get(49).y;
        float middleStartX = average(extractCoordinates(LANDMARK_171, AXIS_X, 92, 93));
        float middleEndX = average(extractCoordinates(LANDMARK_171, AXIS_X, 103, 104));
        // 하단 가로실선 Y 좌표
        float bottomY = landmark171.get(98).y;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();
        order1.add(new Line(landmark171.get(84).x, topY, landmark171.get(88).x, topY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(middleStartX, middleY, middleEndX, middleY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(landmark171.get(97).x, bottomY, landmark171.get(99).x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        // 중정길이 화살표 X 좌표
        float arrowX1 = landmark171.get(113).x;
        // 하정길이 화살표 X 좌표
        float arrowX2 = landmark171.get(143).x;
        order2.add(new Arrow(arrowX1, topY, arrowX1, middleY, DrawingConfig.LINE_COLOR));
        order2.add(new Arrow(arrowX2, middleY, arrowX2, bottomY, DrawingConfig.LINE_COLOR));

        // 중정길이 텍스트 Y 좌표
        float textY1 = average(topY, middleY);
        // 하정길이 텍스트 Y 좌표
        float textY2 = average(middleY, bottomY);
        // 텍스트 크기
        order2.add(new Text(arrowX1, textY1, "중정길이\n%.2fcm", Color.WHITE, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_CENTER));
        order2.add(new Text(arrowX2, textY2, "하정길이\n%.2fcm", Color.WHITE, defaultTextSize, Text.Align.CENTER, Text.Anchor.CENTER_CENTER));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, 500));
        orders.add(new Order(order2, 600, 500));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 중정길이
        lab_face_ratio.data.details.common.upperAndLowerFaceLength.upperAndLowerFaceRatioMiddleLength
        // 하정길이
        lab_face_ratio.data.details.common.upperAndLowerFaceLength.upperAndLowerFaceRatioLowLength
         */
    }
}

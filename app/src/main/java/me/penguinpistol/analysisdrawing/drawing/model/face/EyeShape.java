package me.penguinpistol.analysisdrawing.drawing.model.face;

import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.Vector2;
import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Line;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 눈 - 눈매
 */
public class EyeShape extends BaseDrawingModel {

    public EyeShape(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        float topY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 38, 50));
        float bottomY = landmark118.get(72).y;

        Vector2 leftEyeOuter = new Vector2(landmark118.get(51));
        Vector2 leftEyeInner = new Vector2(landmark118.get(55));
        Vector2 rightEyeOuter = new Vector2(landmark118.get(65));
        Vector2 rightEyeInner = new Vector2(landmark118.get(61));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        // 왼쪽 눈 세로 점선
        order1.add(new Line(leftEyeOuter.x, topY, leftEyeOuter.x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(landmark118.get(114).x, topY, landmark118.get(114).x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(landmark118.get(115).x, topY, landmark118.get(115).x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(leftEyeInner.x, topY, leftEyeInner.x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        // 오른쪽 눈 세로 점선
        order1.add(new Line(rightEyeInner.x, topY, rightEyeInner.x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(landmark118.get(116).x, topY, landmark118.get(116).x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(landmark118.get(117).x, topY, landmark118.get(117).x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        order1.add(new Line(rightEyeOuter.x, topY, rightEyeOuter.x, bottomY, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));

        // 좌측 각도 실선
        float leftAngleBaseX = average(extractCoordinates(LANDMARK_118, AXIS_X, 0, 33));
        Vector2 leftHypotenuse = Vector2.sub(leftEyeOuter, leftEyeInner);
        float leftScalar = distance(new PointF(leftAngleBaseX, leftEyeInner.y), leftEyeInner.toPoint()) / leftHypotenuse.length();
        leftHypotenuse.mul(leftScalar).add(leftEyeInner);

        order1.add(new Line(leftHypotenuse.toPoint(), leftEyeInner.toPoint(), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(new PointF(leftAngleBaseX, leftEyeInner.y), leftEyeInner.toPoint(), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 우측 각도 실선
        float rightAngleBaseX = average(extractCoordinates(LANDMARK_118, AXIS_X,  32, 46));
        Vector2 rightHypotenuse = Vector2.sub(rightEyeOuter, rightEyeInner);
        float rightScalar = distance(new PointF(rightAngleBaseX, rightEyeInner.y), rightEyeInner.toPoint()) / rightHypotenuse.length();
        rightHypotenuse.mul(rightScalar).add(rightEyeInner);

        order1.add(new Line(rightHypotenuse.toPoint(), rightEyeInner.toPoint(), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(new PointF(rightAngleBaseX, rightEyeInner.y), rightEyeInner.toPoint(), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        order2.add(new Text(average(leftEyeOuter.x, leftEyeInner.x), topY, "%.2f:%.2f:%.2f", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.CENTER_BOTTOM));
        order2.add(new Text(average(rightEyeOuter.x, rightEyeInner.x), topY, "%.2f:%.2f:%.2f", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.CENTER_BOTTOM));

        order2.add(new Text(leftAngleBaseX, average(leftHypotenuse.y, leftEyeInner.y), "%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.RIGHT_CENTER));
        order2.add(new Text(rightAngleBaseX, average(rightHypotenuse.y, rightEyeInner.y), "%.2fº", DrawingConfig.TEXT_COLOR, defaultTextSize, Text.Anchor.LEFT_CENTER));

        order2.add(getInfoTextObject("이상적인 눈동자비율\n1.0:1.0:1.0\n\n나의 눈동자비율\nL: %.2f:%.2f:%.2f\nR: %.2f:%.2f:%.2f"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 눈각도
        data.details.angleOfEye.angleOfEyes.left
        data.details.angleOfEye.angleOfEyes.right
        // 눈비율
        data.partAllScore.pupilRatio.left
        data.partAllScore.pupilRatio.right
         */
    }
}

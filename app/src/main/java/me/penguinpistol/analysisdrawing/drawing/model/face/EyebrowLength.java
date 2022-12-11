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
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 눈썹 - 눈썹 길이
 */
public class EyebrowLength extends BaseDrawingModel {

    public EyebrowLength(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        PointF leftEyebrowLeftTop = landmark118.get(34);
        PointF leftDashStartPoint = landmark118.get(77);
        PointF leftReferencePoint = new PointF(landmark118.get(33).x, landmark118.get(37).y);
        order1.add(createDashLine(leftDashStartPoint, new PointF(leftDashStartPoint.x, leftEyebrowLeftTop.y)));
        order1.add(createDashLine(leftDashStartPoint, intersection(leftReferencePoint, leftEyebrowLeftTop, leftDashStartPoint, landmark118.get(114))));
        order1.add(createDashLine(leftDashStartPoint, intersection(leftReferencePoint, leftEyebrowLeftTop, leftDashStartPoint, landmark118.get(51))));

        PointF rightEyebrowRightTop = landmark118.get(45);
        PointF rightDashStartPoint = landmark118.get(83);
        PointF rightReferencePoint = new PointF(landmark118.get(46).x, landmark118.get(42).y);
        order1.add(createDashLine(rightDashStartPoint, new PointF(rightDashStartPoint.x, rightEyebrowRightTop.y)));
        order1.add(createDashLine(rightDashStartPoint, intersection(rightReferencePoint, rightEyebrowRightTop, rightDashStartPoint, landmark118.get(117))));
        order1.add(createDashLine(rightDashStartPoint, intersection(rightReferencePoint, rightEyebrowRightTop, rightDashStartPoint, landmark118.get(65))));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        order2.add(createArrow(landmark118.get(33).x, landmark118.get(38).y, landmark118.get(37).x, landmark118.get(38).y));
        order2.add(createArrow(landmark118.get(42).x, landmark118.get(50).y, landmark118.get(46).x, landmark118.get(50).y));

        order2.add(createText(landmark118.get(35), "%.1fcm", Text.Anchor.CENTER_BOTTOM));
        order2.add(createText(landmark118.get(44), "%.1fcm", Text.Anchor.CENTER_BOTTOM));

        order2.add(getInfoTextObject("눈 높이와 눈썹높이 비율\nL: %s\nR: %s", Text.Align.LEFT));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order3 = new ArrayList<>();

        // 왼쪽 눈썹 윤곽선
        final int[] leftEyebrowPath = new int[] {
                37, 36, 35, 34, 33, 41, 40, 39, 38
        };
        order3.add(createJointLine(extractPoints(LANDMARK_118, leftEyebrowPath)));

        // 오른쪽 눈썹 윤곽선
        final int[] rightEyebrowPath = new int[] {
                42, 43, 44, 45, 46, 47, 48, 49, 50
        };
        order3.add(createJointLine(extractPoints(LANDMARK_118, rightEyebrowPath)));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order3, DrawingConfig.DEFAULT_PLAY_TIME, 1000));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 눈썹길이
        data.partAllScore.eyebrowLength.left.cm
        data.partAllScore.eyebrowLength.right.cm
        // 눈높이와 눈썹높이비율
        // TODO 데이터 확인필요
         */
    }
}

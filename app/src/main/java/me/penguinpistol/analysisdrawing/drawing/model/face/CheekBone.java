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
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 윤곽 - 광대뼈
 */
public class CheekBone extends BaseDrawingModel {

    public CheekBone(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        // 중정
        float middleLineOffset = 50;
        float middleStartX = landmark118.get(2).x;
        float middleEndX = landmark118.get(30).x;
        float middleFaceTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 2, 30));
        float middleFaceBottomY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 77, 83));
        // 가로 실선
        order1.add(createShapeLine(middleStartX - middleLineOffset, middleFaceTopY, middleEndX + middleLineOffset, middleFaceTopY));
        order1.add(createShapeLine(middleStartX - middleLineOffset, middleFaceBottomY, middleEndX + middleLineOffset, middleFaceBottomY));
        // 세로 점선
        order1.add(createDashLine(middleStartX, middleFaceTopY, middleStartX, landmark118.get(80).y));
        order1.add(createDashLine(middleEndX, middleFaceTopY, middleEndX, landmark118.get(80).y));

        // 하정
        float lowerLineOffset = 40;
        float lowerDashOffset = 25;
        float lowerStartX = landmark118.get(8).x - lowerLineOffset;
        float lowerEndX = landmark118.get(24).x + lowerLineOffset;
        float lowerFaceTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 88, 90));
        float lowerFaceBottomY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 11, 21));
        float lowerArrowY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 12, 20));
        // 가로 실선
        order1.add(createShapeLine(lowerStartX, lowerFaceTopY, lowerEndX, lowerFaceTopY));
        order1.add(createShapeLine(lowerStartX, lowerFaceBottomY, lowerEndX, lowerFaceBottomY));
        // 세로 점선
        order1.add(createDashLine(lowerStartX + lowerDashOffset, lowerFaceTopY, lowerStartX + lowerDashOffset, lowerArrowY));
        order1.add(createDashLine(lowerEndX - lowerDashOffset, lowerFaceTopY, lowerEndX - lowerDashOffset, lowerArrowY));

        // 왼쪽 윤곽선
        PointF leftContourUpper = intersection(LANDMARK_118, 33, 46, 8, 2);
        order1.add(createShapeLine(leftContourUpper, landmark118.get(8), Color.MAGENTA));
        order1.add(createShapeLine(landmark118.get(8), landmark118.get(13), Color.MAGENTA));

        // 오른쪽 윤곽선
        PointF rightContourUpper = intersection(LANDMARK_118, 33, 46, 24, 30);
        order1.add(createShapeLine(rightContourUpper, landmark118.get(24), Color.MAGENTA));
        order1.add(createShapeLine(landmark118.get(24), landmark118.get(19), Color.MAGENTA));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        // 화살표
        order2.add(createArrow(middleStartX, landmark118.get(80).y, middleEndX, landmark118.get(80).y));
        order2.add(createArrow(lowerStartX + lowerDashOffset, lowerArrowY, lowerEndX - lowerDashOffset, lowerArrowY));

        // 텟트스
        order2.add(createText(landmark118.get(72).x, landmark118.get(72).y + defaultTextOffset, "%.1fcm", Text.Anchor.CENTER_TOP));
        order2.add(createText(landmark118.get(95).x, lowerArrowY, "%.1fcm", Text.Anchor.CENTER_TOP));

        // 점
        order2.add(createCircle(middleStartX, middleFaceTopY));
        order2.add(createCircle(middleEndX, middleFaceTopY));

        // 좌상단 텍스트
        order2.add(getInfoTextObject("광대와 사각턱의 비율\n%s\n광대 너비\n%.1fcm"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 광대너비
        data.partAllScore.angularRatioOfZygomaAndLowerJawZygoma.palace.cm
        // 사각턱너비
        data.partAllScore.angularRatioOfZygomaAndLowerJawZygoma.mandible.cm
        // 광대와 사각턱 비율
        data.partAllScore.angularRatioOfZygomaAndLowerJawZygoma.ratio
        */
    }
}

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
import me.penguinpistol.analysisdrawing.drawing.object.Text;

/**
 * 입술 - 입꼬리
 */
public class LipTail extends BaseDrawingModel {
    public LipTail(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();
        final int[] lipOutline = new int[] {
                86, 110,  87, 111,  88,  89,  90, 112,
                91, 113,  92,  93,  94,  95,  96,  97,
                86,  98, 105, 106, 100, 107, 103, 102, 92
        };

        order1.add(createJointLine(extractPoints(LANDMARK_118, lipOutline), false));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        // 입술중앙, 입술밑 가로선
        float horizontalMargin = 25;
        float lipLeftX = landmark118.get(86).x;
        float lipRightX = landmark118.get(92).x;
        float lipMiddleY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 86, 92));
        float lipBottomY = landmark118.get(95).y;
        order2.add(createSharpLine(lipLeftX - horizontalMargin, lipMiddleY, lipRightX + horizontalMargin, lipMiddleY));
        order2.add(createSharpLine(lipLeftX - horizontalMargin, lipBottomY, lipRightX + horizontalMargin, lipBottomY));

        // 양쪽 세로선
        float lipWidthGuideTopY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 93, 95, 96, 97));
        order2.add(createDashLine(lipLeftX, lipWidthGuideTopY, lipLeftX, lipBottomY + 40));
        order2.add(createDashLine(lipRightX, lipWidthGuideTopY, lipRightX, lipBottomY + 40));

        float angularLineMargin = 15;

        // 왼쪽 각진선
        float leftCenterX = average(extractCoordinates(LANDMARK_118, AXIS_X, 78, 80));
        float leftCenterY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 78, 80));
        Vector2 leftInterSection = new Vector2(lipLeftX - angularLineMargin, landmark118.get(86).y);
        Vector2 leftUpper = Vector2.sub(new Vector2(leftCenterX, leftCenterY), leftInterSection).mul(0.3F);
        Vector2 leftLower = Vector2.sub(new Vector2(landmark118.get(16)), leftInterSection).mul(0.3F);
        order2.add(createDashLine(leftInterSection.toPoint(), leftUpper.toPoint(leftInterSection)));
        order2.add(createDashLine(leftInterSection.toPoint(), leftLower.toPoint(leftInterSection)));

        // 오른쪽 각진선
        float rightCenterX = average(extractCoordinates(LANDMARK_118, AXIS_X, 82, 88));
        float rightCenterY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 82, 88));
        Vector2 rightInterSection = new Vector2(lipRightX + angularLineMargin, landmark118.get(86).y);
        Vector2 rightUpper = Vector2.sub(new Vector2(rightCenterX, rightCenterY), rightInterSection).mul(0.3F);
        Vector2 rightLower = Vector2.sub(new Vector2(landmark118.get(16)), rightInterSection).mul(0.3F);
        order2.add(createDashLine(rightInterSection.toPoint(), rightUpper.toPoint(rightInterSection)));
        order2.add(createDashLine(rightInterSection.toPoint(), rightLower.toPoint(rightInterSection)));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order3 = new ArrayList<>();

        // 입 너비
        order3.add(createArrow(lipLeftX, lipBottomY + 20, lipRightX, lipBottomY + 20));
        order3.add(createText(landmark118.get(95).x, lipBottomY + 20, "%.1fcm", Text.Anchor.CENTER_TOP));

        order3.add(getInfoTextObject("입꼬리각도\nL: %.1fº\nR: %.1fº"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, 1000));
        orders.add(new Order(order2, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order3, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 입술너비
        data.details.lipWidthAndConner.lipWidth.cm
        // 입꼬리각도
        data.partAllScore.lipTipShape.left
        data.partAllScore.lipTipShape.right
         */
    }
}

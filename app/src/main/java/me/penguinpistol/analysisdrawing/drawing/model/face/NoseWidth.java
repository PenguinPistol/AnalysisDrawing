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
 * 코 - 코너비
 */
public class NoseWidth extends BaseDrawingModel {

    public NoseWidth(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float horizontalStartX = landmark118.get(56).x;
        float horizontalEndX = landmark118.get(68).x;

        float verticalStartY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 38, 50, 71));
        float verticalEndY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 111, 112));

        float noseLeftX = landmark118.get(77).x;
        float noseRightX = landmark118.get(83).x;
        float noseStartY = landmark118.get(71).y;
        float nostrilsUpperY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 76, 84));
        float nostrilsMiddleY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 77, 83));

        // 가로선
        order1.add(createShapeLine(horizontalStartX, noseStartY, horizontalEndX, noseStartY));
        order1.add(createShapeLine(horizontalStartX, nostrilsUpperY, horizontalEndX, nostrilsUpperY));
        order1.add(createShapeLine(horizontalStartX, nostrilsMiddleY, horizontalEndX, nostrilsMiddleY));

        // 세로선
        order1.add(createShapeLine(noseLeftX, verticalStartY, noseLeftX, verticalEndY));
        order1.add(createShapeLine(noseRightX, verticalStartY, noseRightX, verticalEndY));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        final int[] nosePath = new int[] {
                75, 76, 77, 78, 80, 82, 83, 84, 85
        };
        order2.add(createJointLine(extractPoints(LANDMARK_118, nosePath), false));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order3 = new ArrayList<>();

        float arrowY = landmark118.get(80).y;
        order3.add(createArrow(noseLeftX, arrowY, noseRightX, arrowY));
        order3.add(createText(average(noseLeftX, noseRightX), arrowY, "%.1fcm", Text.Anchor.CENTER_TOP));

        order3.add(getInfoTextObject("코너비\n%.2fcm"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, 1000));
        orders.add(new Order(order3, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 코너비
        lab_nose.data.partAllScore.widthOfNose.cm
         */
    }
}

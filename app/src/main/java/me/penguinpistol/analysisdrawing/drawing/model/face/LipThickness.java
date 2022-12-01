package me.penguinpistol.analysisdrawing.drawing.model.face;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

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
 * 입술 - 입술두께
 */
public class LipThickness extends BaseDrawingModel {

    public LipThickness(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float horizontalOffset = 25;
        float horizontalStartX = landmark118.get(86).x;
        float horizontalEndX = landmark118.get(92).x;
        float lipUpperY = min(landmark118.get(88).y, landmark118.get(90).y);
        float lipMiddleY = landmark118.get(100).y;
        float lipLowerY = landmark118.get(95).y;

        order1.add(createShapeLine(horizontalStartX - horizontalOffset, lipUpperY, horizontalEndX + horizontalOffset, lipUpperY));
        order1.add(createShapeLine(horizontalStartX - horizontalOffset, lipMiddleY, horizontalEndX + horizontalOffset, lipMiddleY));
        order1.add(createShapeLine(horizontalStartX - horizontalOffset, lipLowerY, horizontalEndX + horizontalOffset, lipLowerY));

        float verticalUpperY = landmark118.get(80).y * 0.5F;
        float verticalLowerY = average(extractCoordinates(LANDMARK_118, AXIS_Y, 11, 12, 20, 21));

        order1.add(createDashLine(horizontalStartX, verticalUpperY, horizontalStartX, verticalLowerY));
        order1.add(createDashLine(horizontalEndX, verticalUpperY, horizontalEndX, verticalLowerY));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        float arrowLeftX = horizontalStartX - horizontalOffset;
        float arrowRightX = horizontalEndX + horizontalOffset;

        order2.add(createArrow(arrowLeftX, lipUpperY, arrowLeftX, lipMiddleY));
        order2.add(createArrow(arrowRightX, lipMiddleY, arrowRightX, lipLowerY));

        order2.add(createText(arrowLeftX - defaultTextOffset, average(lipUpperY, lipMiddleY), "%.1fcm", Text.Anchor.RIGHT_CENTER));
        order2.add(createText(arrowRightX + defaultTextOffset, average(lipMiddleY, lipLowerY), "%.1fcm", Text.Anchor.LEFT_CENTER));

        order2.add(getInfoTextObject("이상적인 입술비율\n1 : 1.6\n나의 입술비율\n%.1f : %.1f"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        final int[] lipOutline = new int[] {
                86, 110,  87, 111,  88,  89,  90, 112,
                91, 113,  92,  93,  94,  95,  96,  97,
                86,  98, 105, 106, 100, 107, 103, 102, 92
        };
        List<BaseObject> order3 = new ArrayList<>();
        order3.add(createJointLine(extractPoints(LANDMARK_118, lipOutline), false));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order3, DrawingConfig.DEFAULT_PLAY_TIME, 1000));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

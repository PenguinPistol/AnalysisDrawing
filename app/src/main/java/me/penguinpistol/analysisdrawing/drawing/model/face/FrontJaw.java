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
 * 윤곽 - 앞턱
 */
public class FrontJaw extends BaseDrawingModel {

    public FrontJaw(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float horizontalStartX = landmark118.get(56).x;
        float horizontalEndX = landmark118.get(68).x;

        float eyebrowY = landmark171.get(40).y;
        float noseLowerY = landmark171.get(49).y;
        float lipUpperY = min(extractCoordinates(LANDMARK_118, AXIS_Y, 88, 89, 90));
        float lipLowerY = landmark118.get(95).y;
        float jawLowerY = landmark118.get(16).y;

        order1.add(createSharpLine(horizontalStartX, eyebrowY, horizontalEndX, eyebrowY));
        order1.add(createSharpLine(horizontalStartX, noseLowerY, horizontalEndX, noseLowerY));
        order1.add(createSharpLine(horizontalStartX, lipUpperY, horizontalEndX, lipUpperY));
        order1.add(createSharpLine(horizontalStartX, lipLowerY, horizontalEndX, lipLowerY));
        order1.add(createSharpLine(horizontalStartX, jawLowerY, horizontalEndX, jawLowerY));

        float verticalMargin = 35;
        float noseLeftX = landmark118.get(77).x;
        float noseRightX = landmark118.get(83).x;

        order1.add(createSharpLine(noseLeftX, eyebrowY - verticalMargin, noseLeftX, jawLowerY + verticalMargin));
        order1.add(createSharpLine(noseRightX, eyebrowY - verticalMargin, noseRightX, jawLowerY + verticalMargin));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();
        float philtrumLengthX = landmark118.get(86).x;
        float jawLengthX = landmark118.get(92).x;

        // 코 길이
        order2.add(createArrow(horizontalEndX, eyebrowY, horizontalEndX, noseLowerY));
        order2.add(createText(horizontalEndX + defaultTextOffset, average(eyebrowY, noseLowerY), "%.1fcm", Text.Anchor.LEFT_CENTER));
        // 인중 길이
        order2.add(createText(philtrumLengthX - defaultTextOffset, average(noseLowerY, lipUpperY), "%.1fcm", Text.Anchor.RIGHT_CENTER));
        order2.add(createArrow(philtrumLengthX, noseLowerY, philtrumLengthX, lipUpperY));
        // 턱 길이
        order2.add(createArrow(jawLengthX, lipLowerY, jawLengthX, jawLowerY));
        order2.add(createText(jawLengthX + defaultTextOffset, average(lipLowerY, jawLowerY), "%.1fcm", Text.Anchor.LEFT_CENTER));

        order2.add(getInfoTextObject("턱코길이비율\n%s\n앞턱길이\n%.2fcm"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 코 길이
        data.details.noseLength.lengthOfNoseCm
        // 인중길이(데이터없음)
        ??
        // 턱코길이비율(데이터없음)
        ??
        // 앞턱길이
        data.details.frontChin.jawLength
         */
    }
}

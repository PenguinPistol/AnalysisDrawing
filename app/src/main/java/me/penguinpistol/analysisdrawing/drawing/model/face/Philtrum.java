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
 * 입술 - 인중
 */
public class Philtrum extends BaseDrawingModel {

    public Philtrum(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        float noseUnderY = landmark118.get(80).y;
        float lipUpperY = min(extractCoordinates(LANDMARK_118, AXIS_Y, 88, 90));
        float lipUnderY = landmark118.get(95).y;
        float jawUnderY = landmark118.get(16).y;

        order1.add(createSharpLine(landmark118.get(51).x, noseUnderY, landmark118.get(65).x, noseUnderY));
        order1.add(createSharpLine(landmark118.get(51).x, lipUpperY, landmark118.get(65).x, lipUpperY));
        order1.add(createSharpLine(landmark118.get(114).x, lipUnderY, landmark118.get(117).x, lipUnderY));
        order1.add(createSharpLine(landmark118.get(14).x, jawUnderY, landmark118.get(18).x, jawUnderY));

        float verticalX = average(extractCoordinates(LANDMARK_118, AXIS_X, 16, 74, 80));
        order1.add(createSharpLine(verticalX, landmark118.get(74).y, verticalX, landmark118.get(16).y));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order2 = new ArrayList<>();

        order2.add(createSharpLine(landmark118.get(78).x, noseUnderY, landmark118.get(78).x, lipUpperY));
        order2.add(createSharpLine(landmark118.get(94).x, lipUnderY, landmark118.get(94).x, jawUnderY));

        order2.add(createText(landmark118.get(86).x, average(noseUnderY, lipUpperY), "%.1fcm", Text.Anchor.RIGHT_CENTER));
        order2.add(createText(landmark118.get(93).x, average(lipUnderY, jawUnderY), "%.1fcm", Text.Anchor.LEFT_CENTER));

        order2.add(getInfoTextObject("이상적인 비율\n인중 1 : 2 턱\n\n나의 비율\n%.1f : %.1f"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        /*
        // 인중길이
        data.details.philtrum.philtrumJawRatio.philtrums.cm
        // 턱길이
        data.details.philtrum.philtrumJawRatio.jaw.cm
        // 인중턱비율
        data.details.philtrum.philtrumJawRatio.philtrumJawRatios.ratio
         */
    }
}

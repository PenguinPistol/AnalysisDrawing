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

public class LipMountain extends BaseDrawingModel {

    public LipMountain(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
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

        PointF p = landmark118.get(89);
        PointF leftTop = intersection(landmark118.get(10), landmark118.get(77), landmark118.get(88), landmark118.get(89));
        PointF rightTop = intersection(landmark118.get(83), landmark118.get(22), landmark118.get(90), landmark118.get(89));
        order2.add(createDashLine(p, leftTop));
        order2.add(createDashLine(p, rightTop));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order3 = new ArrayList<>();

        order3.add(createText(p.x, p.y - defaultTextOffset * 1.5F, "%.1fº", Text.Anchor.CENTER_BOTTOM));
        order3.add(getInfoTextObject("입술산 각도\n%.1fº"));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, 1000));
        orders.add(new Order(order2, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order3, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

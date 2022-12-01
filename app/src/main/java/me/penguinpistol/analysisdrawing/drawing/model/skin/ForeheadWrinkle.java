package me.penguinpistol.analysisdrawing.drawing.model.skin;

import android.content.Context;
import android.graphics.Color;
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

public class ForeheadWrinkle extends BaseDrawingModel {
    // TODO
    private List<List<PointF>> wrinkles;

    public ForeheadWrinkle(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {

        overlayColor = Color.parseColor("#403047AA");
        jointLineColor = Color.parseColor("#CCFFFF00");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();


        wrinkles = new ArrayList<>();
        List<PointF> temp = new ArrayList<>();
        temp.add(new PointF(100, 100));
        temp.add(new PointF(50, 50));
        temp.add(new PointF(200, 50));
        wrinkles.add(temp);

        for(List<PointF> points : wrinkles) {
            order1.add(createJointLine(points, false));
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

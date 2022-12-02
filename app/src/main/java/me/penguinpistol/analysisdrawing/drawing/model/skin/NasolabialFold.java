package me.penguinpistol.analysisdrawing.drawing.model.skin;

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

/**
 * 주름 - 팔자주름
 */
public class NasolabialFold extends BaseDrawingModel {

    private List<PointF> leftPoints = new ArrayList<>();
    private List<PointF> rightPoints = new ArrayList<>();

    public NasolabialFold(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);

        overlayColor = Color.parseColor("#403047AA");
        jointLineColor = Color.parseColor("#CCFFFF00");
    }

    @Override
    protected void initOrders(Context context) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        order1.add(createJointLine(leftPoints, false));
        order1.add(createJointLine(rightPoints, false));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {
        // [x1,y1,x2,y2...] 형태의 리스트
        // lab_origin.data.details.common.skinNasolabialFoldLeftPathPoints
        // lab_origin.data.details.common.skinNasolabialFoldRightPathPoints
    }
}

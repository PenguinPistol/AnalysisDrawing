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
import me.penguinpistol.analysisdrawing.drawing.Vector2;
import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;

/**
 * 모공 - 블랙헤드
 */
public class Blackhead extends BaseDrawingModel {

    public Blackhead(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        overlayColor = Color.parseColor("#403047AA");
        final int blackheadColor = Color.parseColor("#FF595959");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<BaseObject> order1 = new ArrayList<>();

        Vector2 noseBottom = new Vector2(landmark118.get(80).x, landmark118.get(80).y + (distance(landmark118.get(80), landmark118.get(89)) * 0.3F));

        // TODO 베지에 곡선으로 변경 필요
        // 코 이음선 좌측
        List<PointF> noseLeft = new ArrayList<>();
        PointF p78 = new PointF(landmark118.get(78).x, landmark118.get(78).y);
        p78.offset(-5, 5);

        noseLeft.add(landmark118.get(38));
        noseLeft.add(landmark118.get(75));
        noseLeft.add(averagePoint(extractPoints(LANDMARK_118, 75, 76).toArray(new PointF[0])));
        noseLeft.add(landmark118.get(77));
        noseLeft.add(p78);
        noseLeft.add(noseBottom.toPoint());

        order1.add(createJointLine(noseLeft,  false));

        // 코 이음선 우측
        List<PointF> noseRight = new ArrayList<>();
        PointF p82 = new PointF(landmark118.get(82).x, landmark118.get(82).y);
        p82.offset(5, 5);

        noseRight.add(landmark118.get(50));
        noseRight.add(landmark118.get(85));
        noseRight.add(averagePoint(extractPoints(LANDMARK_118, 85, 84).toArray(new PointF[0])));
        noseRight.add(landmark118.get(83));
        noseRight.add(p82);
        noseRight.add(noseBottom.toPoint());

        order1.add(createJointLine(noseRight, false));

        // 블랙헤드
        // TODO 블랙헤드 그리기
//        for(RectF blackhead : blackheads) {
//            order1.add(createJointLine(p1, p2, p3, p4).setColor(blackheadColor));
//        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;
import me.penguinpistol.analysisdrawing.drawing.object.Circle;
import me.penguinpistol.analysisdrawing.drawing.object.Line;

public class FaceShape extends BaseDrawingModel {

    public FaceShape(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        super(context, landmark118, landmark171);
    }

    @Override
    protected void initOrders(Context context) {
        List<BaseObject> order1 = new ArrayList<>();

        // 가운데 세로 실선
        order1.add(new Line(landmark171.get(80), landmark171.get(98), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 가로 실선
        order1.add(new Line(landmark171.get(82), landmark171.get(86), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(landmark118.get(1), landmark118.get(31), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        order1.add(new Line(landmark118.get(8), landmark118.get(24), DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));

        // 얼굴 외곽 실선
        List<Pair<Integer, Integer>> inner = Arrays.asList(
                new Pair<>(LANDMARK_171, 80),
                new Pair<>(LANDMARK_171, 86),
                new Pair<>(LANDMARK_118, 31),
                new Pair<>(LANDMARK_118, 24),
                new Pair<>(LANDMARK_171, 98),
                new Pair<>(LANDMARK_118, 8),
                new Pair<>(LANDMARK_118, 1),
                new Pair<>(LANDMARK_171, 82)
        );

        for (int i = 0; i < inner.size(); i++) {
            int next = (i+1) % inner.size();
            PointF start = point(inner.get(i).first, inner.get(i).second);
            PointF end = point(inner.get(next).first, inner.get(next).second);
            order1.add(new Line(start, end, DrawingConfig.LINE_COLOR, defaultThickness, Line.SHARP));
        }

        // 얼굴 외곽 점선

        int[] outer1 = IntStream.range(85, 89).toArray();
        int[] outer2 = IntStream.range(89, 108).map(i -> 89 + (107 - i)).toArray(); // 뒤집기
        int[] outer3 = IntStream.range(80, 85).map(i -> 80 + (84 - i)).toArray();   // 뒤집기
        int[] outer = Stream.of(outer1, outer2, outer3).flatMapToInt(Arrays::stream).toArray();
        for(int i = 0 ; i < outer.length; i++) {
            int next = (i+1) % outer.length;
            order1.add(new Line(landmark171.get(outer[i]), landmark171.get(outer[next]), DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH)
                    .setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE));
        }

        List<BaseObject> order2 = new ArrayList<>();
        order2.add(getInfoTextObject("얼굴형"));

        orders.add(new Order(order1, 0, DrawingConfig.DEFAULT_PLAY_TIME));
        orders.add(new Order(order2, DrawingConfig.DEFAULT_PLAY_TIME, DrawingConfig.DEFAULT_PLAY_TIME));

        // 윤곽 안쪽 실선 포인트별 점 찍기
        List<Pair<Integer, Integer>> innerCopy = new ArrayList<>(inner);
        Collections.shuffle(innerCopy);
        long circlePlayTime = 100;
        for (int i = 0; i < innerCopy.size(); i++) {
            Pair<Integer, Integer> p = innerCopy.get(i);
            List<BaseObject> circle = Collections.singletonList(new Circle(DrawingConfig.CIRCLE_OUTER_COLOR, DrawingConfig.CIRCLE_INNER_COLOR, point(p.first, p.second), defaultCircleRadius));
            orders.add(new Order(circle, DrawingConfig.DEFAULT_PLAY_TIME + circlePlayTime * i, circlePlayTime));
        }

    }

    @Override
    protected void parseJson(JsonElement json) {

    }
}

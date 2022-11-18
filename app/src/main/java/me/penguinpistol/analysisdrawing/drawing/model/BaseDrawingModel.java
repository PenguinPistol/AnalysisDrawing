package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.object.Overlay;
import me.penguinpistol.analysisdrawing.drawing.object.Text;

public abstract class BaseDrawingModel {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LANDMARK_118, LANDMARK_171})
    protected @interface Landmark {}
    protected static final int LANDMARK_118 = 0;
    protected static final int LANDMARK_171 = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({AXIS_X, AXIS_Y})
    protected @interface Axis {}
    protected static final int AXIS_X = 0;
    protected static final int AXIS_Y = 1;

    protected final List<PointF> landmark118;
    protected final List<PointF> landmark171;
    protected final List<Order> orders;

    protected float infoTextSize;
    protected float defaultThickness;
    protected float defaultTextSize;
    protected float defaultCircleRadius;
    protected int overlayColor = 0x33000000;

    public BaseDrawingModel(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        this.landmark118 = landmark118;
        this.landmark171 = landmark171;
        this.orders = new ArrayList<>();
        this.orders.add(new Order(Collections.singletonList(new Overlay(overlayColor, false)), 0, 0));

        float density = context.getResources().getDisplayMetrics().density;
        infoTextSize = DrawingConfig.INFO_TEXT_SIZE * density;
        defaultThickness = DrawingConfig.LINE_THICKNESS * density;
        defaultTextSize = DrawingConfig.TEXT_SIZE * density;
        defaultCircleRadius = DrawingConfig.CIRCLE_OUTER_RADIUS * density;

        // TODO parseJson 내부에서 호출하게 변경
        initOrders(context);
    }

    /**
     * 드로잉 요소 정의
     */
    protected abstract void initOrders(Context context);

    /**
     * 데이터 파싱
     */
    protected abstract void parseJson(JsonElement json);

    public List<Order> getOrders() {
        return orders;
    }

    /**
     * 좌상단 정보텍스트 드로잉요소
     */
    protected Text getInfoTextObject(String text) {
        return getInfoTextObject(text, Text.Align.CENTER);
    }

    protected Text getInfoTextObject(String text, Text.Align align) {
        return new Text(DrawingConfig.INFO_TEXT_POSITION_X, DrawingConfig.INFO_TEXT_POSITION_Y, text, DrawingConfig.LINE_COLOR, infoTextSize, align, Text.Anchor.LEFT_TOP);
    }

    /**
     * 랜드마크 포인트 획득
     */
    protected PointF point(@Landmark int landmark, int index) {
        List<PointF> points = landmark == LANDMARK_118 ? landmark118 : landmark171;
        return index < points.size() ? points.get(index) : null;
    }

    /**
     * 랜드마크 포인트의 특정 인덱스들의 x/y축 기준 좌표를 추출
     */
    protected float[] extractCoordinates(@Landmark int landmark, @Axis int axis, int... indexes) {
        if(indexes == null) {
            return new float[0];
        }

        float[] result = new float[indexes.length];
        List<PointF> points = landmark == LANDMARK_118 ? landmark118 : landmark171;

        for (int i = 0; i < indexes.length; i++) {
            if(indexes[i] < points.size()) {
                result[i] = axis == AXIS_X ? points.get(indexes[i]).x : points.get(indexes[i]).y;
            }
        }

        return result;
    }

    /**
     * 랜드마크 포인트 특정 인덱스 리스트 추출
     */
    protected List<PointF> extractPoints(@Landmark int landmark, int... indexes) {
        if(indexes == null) {
            return new ArrayList<>();
        }

        List<PointF> points = landmark == LANDMARK_118 ? landmark118 : landmark171;
        List<PointF> result = new ArrayList<>();
        for (int index : indexes) {
            if (index < points.size()) {
                result.add(points.get(index));
            }
        }
        return result;
    }

    /**
     * float 배열 평균구하기
     */
    protected float average(float... values) {
        if(values == null || values.length == 0) {
            return 0;
        }
        return (float) IntStream.range(0, values.length).mapToDouble(i -> values[i]).average().orElse(0);
    }

    /**
     * float 배열 최댓값 구하기
     */
    protected float max(float... values) {
        if(values == null || values.length == 0) {
            return 0;
        }
        return (float) IntStream.range(0, values.length).mapToDouble(i -> values[i]).max().orElse(0);
    }

    /**
     * float 배열 최솟값 구하기
     */
    protected float min(float... values) {
        if(values == null || values.length == 0) {
            return 0;
        }
        return (float) IntStream.range(0, values.length).mapToDouble(i -> values[i]).min().orElse(0);
    }

    /**
     * 두 직선의 교차점 구하기
     */
    protected PointF intersection(PointF p1, PointF p2, PointF p3, PointF p4) {
        float numeratorX    = (p1.x * p2.y - p1.y * p2.x) * (p3.x - p4.x) - (p1.x - p2.x) * (p3.x * p4.y - p3.y * p4.x);
        float numeratorY    = (p1.x * p2.y - p1.y * p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x * p4.y - p3.y * p4.x);
        float denominator  = (p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x - p4.x);

        if(denominator == 0) {
            return null;
        }

        return new PointF(numeratorX / denominator, numeratorY / denominator);
    }
}

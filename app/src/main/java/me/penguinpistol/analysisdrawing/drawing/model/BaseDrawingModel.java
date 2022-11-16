package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
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

    public BaseDrawingModel(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        this.landmark118 = landmark118;
        this.landmark171 = landmark171;
        this.orders = new ArrayList<>();

        float density = context.getResources().getDisplayMetrics().density;
        infoTextSize = DrawingConfig.INFO_TEXT_SIZE * density;
        defaultThickness = DrawingConfig.LINE_THICKNESS * density;
        defaultTextSize = DrawingConfig.TEXT_SIZE * density;

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
}

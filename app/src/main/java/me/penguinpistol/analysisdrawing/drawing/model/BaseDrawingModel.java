package me.penguinpistol.analysisdrawing.drawing.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import me.penguinpistol.analysisdrawing.drawing.DrawingConfig;
import me.penguinpistol.analysisdrawing.drawing.Order;
import me.penguinpistol.analysisdrawing.drawing.object.Arrow;
import me.penguinpistol.analysisdrawing.drawing.object.Circle;
import me.penguinpistol.analysisdrawing.drawing.object.JointLine;
import me.penguinpistol.analysisdrawing.drawing.object.Line;
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
    protected float defaultTextOffset;
    protected float defaultCircleRadius;

    @ColorInt protected int overlayColor = 0x33000000;
    @ColorInt protected int jointLineColor = Color.MAGENTA;

    public BaseDrawingModel(@NonNull Context context, @NonNull List<PointF> landmark118, @NonNull List<PointF> landmark171) {
        this.landmark118 = landmark118;
        this.landmark171 = landmark171;
        this.orders = new ArrayList<>();

        float density = context.getResources().getDisplayMetrics().density;
        infoTextSize = DrawingConfig.INFO_TEXT_SIZE * density;
        defaultThickness = DrawingConfig.LINE_THICKNESS * density;
        defaultTextSize = DrawingConfig.TEXT_SIZE * density;
        defaultTextOffset = DrawingConfig.TEXT_OFFSET * density;
        defaultCircleRadius = DrawingConfig.CIRCLE_OUTER_RADIUS * density;

        // TODO parseJson ???????????? ???????????? ??????
        initOrders(context);

        this.orders.add(0, new Order(Collections.singletonList(new Overlay(overlayColor, false)), 0, 0));
    }

    /**
     * ????????? ?????? ??????
     */
    protected abstract void initOrders(Context context);

    /**
     * ????????? ??????
     */
    protected abstract void parseJson(JsonElement json);

    public List<Order> getOrders() {
        return orders;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * ????????? ??????????????? ???????????????
     */
    protected Text getInfoTextObject(String text) {
        return getInfoTextObject(text, Text.Align.CENTER);
    }

    protected Text getInfoTextObject(String text, Text.Align align) {
        return new Text(DrawingConfig.INFO_TEXT_POSITION_X, DrawingConfig.INFO_TEXT_POSITION_Y, text, DrawingConfig.LINE_COLOR, infoTextSize, align, Text.Anchor.LEFT_TOP);
    }

    protected Line createSharpLine(float x1, float y1, float x2, float y2, int color) {
        return new Line(x1, y1, x2, y2, color, defaultThickness, Line.SHARP);
    }

    protected Line createSharpLine(float x1, float y1, float x2, float y2) {
        return createSharpLine(x1, y1, x2, y2, DrawingConfig.LINE_COLOR);
    }

    protected Line createSharpLine(PointF p1, PointF p2, int color) {
        return createSharpLine(p1.x, p1.y, p2.x, p2.y, color);
    }

    protected Line createSharpLine(PointF p1, PointF p2) {
        return createSharpLine(p1.x, p1.y, p2.x, p2.y);
    }


    protected Line createDashLine(float x1, float y1, float x2, float y2) {
        return new Line(x1, y1, x2, y2, DrawingConfig.LINE_COLOR, defaultThickness, Line.DASH).setDashConfig(DrawingConfig.LINE_DASH_INTERVAL, DrawingConfig.LINE_DASH_PHASE);
    }

    protected Line createDashLine(PointF p1, PointF p2) {
        return createDashLine(p1.x, p1.y, p2.x, p2.y);
    }

    protected Text createText(float x, float y, String text, Text.Align align, Text.Anchor anchor) {
        return new Text(x, y, text, DrawingConfig.LINE_COLOR, defaultTextSize, align, anchor);
    }

    protected Text createText(float x, float y, String text) {
        return createText(x, y, text, Text.Align.LEFT, Text.Anchor.LEFT_TOP);
    }

    protected Text createText(float x, float y, String text, Text.Align align) {
        return createText(x, y, text, align, Text.Anchor.LEFT_TOP);
    }

    protected Text createText(float x, float y, String text, Text.Anchor anchor) {
        return createText(x, y, text, Text.Align.LEFT, anchor);
    }

    protected Text createText(PointF p, String text) {
        return createText(p.x, p.y, text, Text.Align.LEFT, Text.Anchor.LEFT_TOP);
    }

    protected Text createText(PointF p, String text, Text.Align align) {
        return createText(p.x, p.y, text, align, Text.Anchor.LEFT_TOP);
    }

    protected Text createText(PointF p, String text, Text.Anchor anchor) {
        return createText(p.x, p.y, text, Text.Align.LEFT, anchor);
    }

    protected JointLine createJointLine(List<PointF> points) {
        return createJointLine(points, true);
    }

    protected JointLine createJointLine(List<PointF> points, boolean isClosed) {
        return new JointLine(jointLineColor, points, defaultThickness, isClosed);
    }

    protected Arrow createArrow(float x1, float y1, float x2, float y2) {
        return new Arrow(x1, y1, x2, y2, DrawingConfig.LINE_COLOR);
    }

    protected Arrow createArrow(PointF p1, PointF p2) {
        return createArrow(p1.x, p1.y, p2.x, p2.y);
    }

    protected Circle createCircle(float cx, float cy, float r) {
        return new Circle(DrawingConfig.CIRCLE_OUTER_COLOR, DrawingConfig.CIRCLE_INNER_COLOR, cx, cy, r);
    }

    protected Circle createCircle(PointF cp, float r) {
        return createCircle(cp.x, cp.y, r);
    }

    protected Circle createCircle(float cx, float cy) {
        return createCircle(cx, cy, defaultCircleRadius);
    }

    protected Circle createCircle(PointF cp) {
        return createCircle(cp.x, cp.y, defaultCircleRadius);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * ???????????? ????????? ??????
     */
    protected PointF point(@Landmark int landmark, int index) {
        List<PointF> points = landmark == LANDMARK_118 ? landmark118 : landmark171;
        return index < points.size() ? points.get(index) : null;
    }

    /**
     * ???????????? ???????????? ?????? ??????????????? x/y??? ?????? ????????? ??????
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
     * ?????? ????????? ???????????? ????????? ????????? ??????
     */
    protected List<PointF> extractPoints(@Landmark int landmark, int... indexes) {
        return extractPoints(landmark, 0, 0, indexes);
    }

    /**
     * offset ????????? ?????? ????????? ???????????? ????????? ????????? ??????
     */
    protected List<PointF> extractPoints(@Landmark int landmark, float offsetX, float offsetY, int... indexes) {
        if(indexes == null) {
            return new ArrayList<>();
        }

        List<PointF> points = landmark == LANDMARK_118 ? landmark118 : landmark171;
        List<PointF> result = new ArrayList<>();
        for (int index : indexes) {
            if (index < points.size()) {
                PointF p = new PointF(points.get(index).x, points.get(index).y);
                p.offset(offsetX, offsetY);
                result.add(p);
            }
        }
        return result;
    }



    /**
     * float ?????? ???????????????
     */
    protected float average(float... values) {
        if(values == null || values.length == 0) {
            return 0;
        }
        return (float) IntStream.range(0, values.length).mapToDouble(i -> values[i]).average().orElse(0);
    }

    /**
     * ??? ????????? x, y ?????? ?????? ????????? ????????? ??? ??????
     */
    protected PointF averagePoint(PointF... points) {
        if(points == null || points.length == 0) {
            return new PointF();
        }
        float x = (float)Stream.of(points).mapToDouble(p -> p.x).average().orElse(0);
        float y = (float)Stream.of(points).mapToDouble(p -> p.y).average().orElse(0);
        return new PointF(x, y);
    }

    /**
     * float ?????? ????????? ?????????
     */
    protected float max(float... values) {
        if(values == null || values.length == 0) {
            return 0;
        }
        return (float) IntStream.range(0, values.length).mapToDouble(i -> values[i]).max().orElse(0);
    }

    /**
     * float ?????? ????????? ?????????
     */
    protected float min(float... values) {
        if(values == null || values.length == 0) {
            return 0;
        }
        return (float) IntStream.range(0, values.length).mapToDouble(i -> values[i]).min().orElse(0);
    }

    /**
     * ??? ????????? ????????? ?????????
     * <pre>
     *      p3
     *      |
     * p1---P---p2
     *      |
     *      P4
     * </pre>
     * @return P
     */
    protected PointF intersection(PointF p1, PointF p2, PointF p3, PointF p4) {
        float numeratorX    = (p1.x * p2.y - p1.y * p2.x) * (p3.x - p4.x) - (p1.x - p2.x) * (p3.x * p4.y - p3.y * p4.x);
        float numeratorY    = (p1.x * p2.y - p1.y * p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x * p4.y - p3.y * p4.x);
        float denominator   = (p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x - p4.x);

        if(denominator == 0) {
            return null;
        }

        return new PointF(numeratorX / denominator, numeratorY / denominator);
    }

    /**
     * ??? ????????? ????????? ?????????(?????? ??????????????? ???????????? ??????)
     * <pre>
     *      p3
     *      |
     * p1---P---p2
     *      |
     *      P4
     * </pre>
     * @return P
     */
    protected PointF intersection(@Landmark int landmark, int p1, int p2, int p3, int p4) {
        List<PointF> points = landmark == LANDMARK_118 ? landmark118 : landmark171;
        try {
            return intersection(points.get(p1), points.get(p2), points.get(p3), points.get(p4));
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * ??? ??? ????????? ??????
     */
    protected float distance(PointF p1, PointF p2) {
        float distX = p1.x - p2.x;
        float distY = p1.y - p2.y;
        return (float) Math.sqrt((distX * distX) + (distY * distY));
    }
}

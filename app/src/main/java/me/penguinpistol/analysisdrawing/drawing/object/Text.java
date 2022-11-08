package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.Arrays;

public class Text extends DrawingObject {
    private final String[] texts;
    private final Rect[] bounds;
    private final Align align;
    private final Anchor anchor;

    private PointF point;
    private float maxWidth;
    private float maxHeight;

    public Text(float x, float y, String text, int color, float textSize, Align align, Anchor anchor) {
        super(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.LEFT);

        this.align = align;
        this.anchor = anchor;
        this.point = new PointF(x, y);
        this.texts = text.split("\n");
        this.bounds = new Rect[texts.length];

        calculateTextBounds();
    }

    public Text(float x, float y, String text, int color, float textSize, Align align) {
        this(x, y, text, color, textSize, align, Anchor.LEFT_TOP);
    }

    public Text(float x, float y, String text, int color, float textSize, Anchor anchor) {
        this(x, y, text, color, textSize, Align.LEFT, anchor);
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        for (int i = 0; i < texts.length; i++) {
            float x = 0;
            float y = 0;
            switch (anchor) {
                case LEFT_TOP:
                    x = point.x;
                    y = point.y + maxHeight * (i+1);
                    break;
                case LEFT_BOTTOM:
                    x = point.x;
                    y = point.y - maxHeight * (texts.length - (i+1));
                    break;
                case RIGHT_TOP:
                    x = point.x - bounds[i].width();
                    y = point.y + maxHeight * (i+1);
                    break;
                case RIGHT_BOTTOM:
                    x = point.x - bounds[i].width();
                    y = point.y - maxHeight * (texts.length - (i+1));
                    break;
                case CENTER_TOP:
                    x = point.x - bounds[i].centerX();
                    y = point.y + maxHeight * (i+1);
                    break;
                case CENTER_BOTTOM:
                    x = point.x - bounds[i].centerX();
                    y = point.y - maxHeight * (texts.length - (i+1));
                    break;
            }

            canvas.drawText(texts[i], x, y, paint);
        }
    }

    public void setFont(Typeface typeface) {
        this.paint.setTypeface(typeface);
        calculateTextBounds();
    }

    private void calculateTextBounds() {
        for (int i = 0; i < texts.length; i++) {
            bounds[i] = new Rect();
            paint.getTextBounds(texts[i], 0, texts[i].length(), bounds[i]);
        }
        maxWidth = Arrays.stream(bounds).mapToInt(Rect::width).max().orElse(0);
        maxHeight = Arrays.stream(bounds).mapToInt(Rect::height).max().orElse(0);
    }

    public enum Align {
        LEFT(-1),
        CENTER(0),
        RIGHT(1);

        final int dir;

        Align(int dir) {
            this.dir = dir;
        }
    }

    public enum Anchor {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM,
        CENTER_TOP,
        CENTER_BOTTOM
    }
}

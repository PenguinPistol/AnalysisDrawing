package me.penguinpistol.analysisdrawing.drawing.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.animation.DecelerateInterpolator;

import java.util.Arrays;

public class Text extends BaseObject {
    private static final int TRANSLATE_Y = 50;

    private final String[] texts;
    private final Rect[] bounds;
    private final Align align;
    private final Anchor anchor;
    private final PointF point;

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

        interpolator = new DecelerateInterpolator();

        calculateTextBounds();
    }

    public Text(float x, float y, String text, int color, float textSize) {
        this(x, y, text, color, textSize, Align.LEFT, Anchor.LEFT_TOP);
    }

    public Text(float x, float y, String text, int color, float textSize, Align align) {
        this(x, y, text, color, textSize, align, Anchor.LEFT_TOP);
    }

    public Text(float x, float y, String text, int color, float textSize, Anchor anchor) {
        this(x, y, text, color, textSize, Align.LEFT, anchor);
    }

    public Text(PointF p, String text, int color, float textSize) {
        this(p.x, p.y, text, color, textSize, Align.LEFT, Anchor.LEFT_TOP);
    }

    public Text(PointF p, String text, int color, float textSize, Align align) {
        this(p.x, p.y, text, color, textSize, align, Anchor.LEFT_TOP);
    }

    public Text(PointF p, String text, int color, float textSize, Anchor anchor) {
        this(p.x, p.y, text, color, textSize, Align.LEFT, anchor);
    }

    public Text(PointF p, String text, int color, float textSize, Align align, Anchor anchor) {
        this(p.x, p.y, text, color, textSize, align, anchor);
    }

    @Override
    public void draw(Canvas canvas, long playTime, float fraction) {
        if(texts == null) {
            return;
        }

        if(interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }

        float translateY = TRANSLATE_Y * (1F - fraction);
        paint.setAlpha((int)(originAlpha * fraction));

        for (int i = 0; i < texts.length; i++) {
            float remainingSpace = (maxWidth - bounds[i].width());
            float x = 0;
            float y = 0;

            // check horizontal anchor
            if(anchor.check(ANCHOR_LEFT)) {
                x = point.x + (remainingSpace * align.value);
            } else if(anchor.check(ANCHOR_CENTER)) {
                x = point.x - bounds[i].centerX() + (remainingSpace * (align.value - 0.5F));
            } else if(anchor.check(ANCHOR_RIGHT)) {
                x = point.x - bounds[i].width() - (remainingSpace * (1F - align.value));
            }

            // check vertical anchor
            if(anchor.check(ANCHOR_TOP)) {
                y = point.y + maxHeight * (i+1);
            } else if(anchor.check(ANCHOR_MIDDLE)) {
                y = point.y + maxHeight * (i+1) - (maxHeight * texts.length * 0.5F);
            } else if(anchor.check(ANCHOR_BOTTOM)) {
                y = point.y - maxHeight * (texts.length - (i+1));
            }

            canvas.drawText(texts[i], x, y + translateY, paint);
        }
    }

    public void setFont(Typeface typeface) {
        this.paint.setTypeface(typeface);
        calculateTextBounds();
    }

    public Text offset(float x, float y) {
        this.point.offset(x, y);
        return this;
    }

    private void calculateTextBounds() {
        for (int i = 0; i < texts.length; i++) {
            bounds[i] = new Rect();
            paint.getTextBounds(texts[i], 0, texts[i].length(), bounds[i]);
        }
        maxWidth = Arrays.stream(bounds).mapToInt(Rect::width).max().orElse(0);
        maxHeight = paint.getFontMetrics(new Paint.FontMetrics());
    }

    public enum Align {
        LEFT(0.0F),
        CENTER(0.5F),
        RIGHT(1.0F);

        final float value;

        Align(float value) {
            this.value = value;
        }
    }

    private static final int ANCHOR_LEFT    = 32;
    private static final int ANCHOR_CENTER  = 16;
    private static final int ANCHOR_RIGHT   = 8;
    private static final int ANCHOR_TOP     = 4;
    private static final int ANCHOR_MIDDLE  = 2;
    private static final int ANCHOR_BOTTOM  = 1;

    public enum Anchor {
        LEFT_TOP(36),       // 100100
        LEFT_CENTER(34),    // 100010
        LEFT_BOTTOM(33),    // 100001
        CENTER_TOP(20),     // 010100
        CENTER_CENTER(18),  // 010010
        CENTER_BOTTOM(17),  // 010001
        RIGHT_TOP(12),      // 001100
        RIGHT_CENTER(10),   // 001010
        RIGHT_BOTTOM(9)     // 001001
        ;

        final int mask;

        Anchor(int mask) {
            this.mask = mask;
        }

        boolean check(int mask) {
            return (this.mask & mask) > 0;
        }
    }
}

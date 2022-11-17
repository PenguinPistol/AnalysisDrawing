package me.penguinpistol.analysisdrawing.drawing;

import android.graphics.Color;

/**
 * 드로잉 기본 속성 저장
 */
public final class DrawingConfig {

    /**
     * 좌상단 텍스트 속성
     */
    public static final int INFO_TEXT_POSITION_X = 24;
    public static final int INFO_TEXT_POSITION_Y = 24;
    public static final int INFO_TEXT_SIZE = 12;

    /**
     * Line 속성
     */
    public static final int LINE_THICKNESS = 1;
    public static final int LINE_COLOR = Color.WHITE;
    public static final float LINE_DASH_INTERVAL = 10F;
    public static final float LINE_DASH_PHASE = 5F;

    /**
     * Text 속성
     */
    public static final int TEXT_SIZE = 10;
    public static final int TEXT_COLOR = Color.WHITE;

    /**
     * Circle 속성
     */
    public static final int CIRCLE_OUTER_RADIUS = 5;
    public static final int CIRCLE_OUTER_COLOR = Color.MAGENTA;
    public static final int CIRCLE_INNER_COLOR = Color.WHITE;

    /**
     * ARC 속성
     */
    public static final int ARC_RADIUS = 15;
}

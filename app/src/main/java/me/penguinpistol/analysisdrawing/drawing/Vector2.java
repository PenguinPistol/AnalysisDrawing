package me.penguinpistol.analysisdrawing.drawing;

import android.graphics.PointF;

import androidx.annotation.NonNull;

import java.util.Locale;

public final class Vector2 {
    public static float TO_RADIANS = (1 / 180F) * (float) Math.PI;
    public static float TO_DEGREES = (1 / (float) Math.PI) * 180;

    public float x;
    public float y;

    public Vector2() {
        this(0, 0);
    }

    public Vector2(@NonNull Vector2 other) {
        this(other.x, other.y);
    }

    public Vector2(@NonNull PointF p) {
        this(p.x, p.y);
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 set(@NonNull Vector2 other) {
        return set(other.x, other.y);
    }

    public Vector2 add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 add(@NonNull Vector2 other) {
        return add(other.x, other.y);
    }

    public Vector2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 sub(@NonNull Vector2 other) {
        return sub(other.x, other.y);
    }

    public Vector2 mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        float length = length();
        if(length != 0) {
            this.x /= length;
            this.y /= length;
        }
        return this;
    }

    public float angle() {
        float angle = (float) Math.atan2(y, x) * TO_DEGREES;
        if(angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public Vector2 rotate(float degree) {
        float radians = degree * TO_RADIANS;
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);
        return set(this.x * cos - this.y * sin, this.x * sin + this.y * cos);
    }

    public float distance(float x, float y) {
        float distX = this.x - x;
        float distY = this.y - y;
        return (float) Math.sqrt((distX * distX) + (distY * distY));
    }

    public float distance(@NonNull Vector2 other) {
        return distance(other.x, other.y);
    }

    public float dot(@NonNull Vector2 other) {
        return (this.x * other.x) + (this.y * other.y);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Vector2:[%.2f, %.2f]", x, y);
    }

    public PointF toPoint() {
        return new PointF(x, y);
    }

    public PointF toPoint(Vector2 offset) {
        return new PointF(x + offset.x, y + offset.y);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1).add(v2);
    }

    public static Vector2 sub(Vector2 v1, Vector2 v2) {
        return new Vector2(v1).sub(v2);
    }

    public static float dot(Vector2 v1, Vector2 v2) {
        return (v1.x * v2.x) + (v1.y * v2.y);
    }
}

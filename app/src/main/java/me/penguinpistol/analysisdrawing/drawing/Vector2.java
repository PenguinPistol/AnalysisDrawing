package me.penguinpistol.analysisdrawing.drawing;

import androidx.annotation.NonNull;
import androidx.core.math.MathUtils;

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
        return (float) Math.sqrt((distX * distY) + (distY * distY));
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
}

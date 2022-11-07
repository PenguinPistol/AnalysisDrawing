package me.penguinpistol.analysisdrawing.drawing;

import androidx.annotation.NonNull;

import java.util.Locale;

public final class Vector2 {
    public static Vector2 TOP = new Vector2(0, 1);
    public static Vector2 LEFT = new Vector2(-1, 0);

    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 rotate(Vector2 vector, float degree) {
        double rad = Math.toRadians(degree);
        return new Vector2((float)(vector.x * Math.cos(rad) - vector.y * Math.sin(rad)), (float)(vector.x * Math.sin(rad) + vector.y * Math.cos(rad)));
    }

    public Vector2 mul(float scala) {
        x *= scala;
        y *= scala;
        return this;
    }

    public static Vector2 sum(Vector2 left, Vector2 right) {
        return new Vector2(left.x + right.x, left.y + right.y);
    }

    public static Vector2 sub(Vector2 left, Vector2 right) {
        return new Vector2(left.x - right.x, left.y - right.y);
    }

    public static Vector2 normalize(Vector2 target) {
        return new Vector2(target.x / target.magnitude(), target.y / target.magnitude());
    }

    public static float dot(Vector2 left, Vector2 right) {
        return (left.x * right.x) + (left.y * right.y);
    }

    public float magnitude() {
        return (float)Math.sqrt(x * x + y * y);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "(%.3f, %.3f)", x, y);
    }
}

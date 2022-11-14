package me.penguinpistol.analysisdrawing.drawing;

import android.graphics.Canvas;

import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.object.BaseObject;

public class Order {
    private final List<BaseObject> baseObjects;
    private final long delay;
    private final long playTime;

    public Order(List<BaseObject> baseObjects, long delay, long playTime) {
        this.baseObjects = baseObjects;
        this.delay = delay;
        this.playTime = playTime;
    }

    public void draw(Canvas canvas, float fraction) {
        for(BaseObject object : baseObjects) {
            object.draw(canvas, playTime, fraction);
        }
    }

    public long getDelay() {
        return delay;
    }

    public long getPlayTime() {
        return playTime;
    }

    public long getTotalPlayTime() {
        return delay + playTime;
    }
}

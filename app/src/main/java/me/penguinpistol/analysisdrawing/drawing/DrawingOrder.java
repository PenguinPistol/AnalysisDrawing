package me.penguinpistol.analysisdrawing.drawing;

import android.graphics.Canvas;

import java.util.List;

import me.penguinpistol.analysisdrawing.drawing.object.DrawingObject;

public class DrawingOrder {
    private final List<DrawingObject> drawingObjects;
    private final long delay;
    private final long playTime;

    public DrawingOrder(List<DrawingObject> drawingObjects, long delay, long playTime) {
        this.drawingObjects = drawingObjects;
        this.delay = delay;
        this.playTime = playTime;
    }

    public void draw(Canvas canvas, float fraction) {
        for(DrawingObject object : drawingObjects) {
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

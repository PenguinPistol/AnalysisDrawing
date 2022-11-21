package me.penguinpistol.analysisdrawing;

import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;

public class Parts {
    public final Class<? extends BaseDrawingModel> modelClass;
    public final String name;

    public Parts(Class<? extends BaseDrawingModel> modelClass, String name) {
        this.modelClass = modelClass;
        this.name = name;
    }
}

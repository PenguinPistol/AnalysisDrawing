package me.penguinpistol.analysisdrawing.data;

import android.graphics.PointF;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.MainViewModel;

public class AnalysisData {

    private final String image;
    private final List<PointF> landmark118 = new ArrayList<>();
    private final List<PointF> landmark171 = new ArrayList<>();

    public AnalysisData(JsonObject json) {
        JsonObject jsonLabData = MainViewModel.findChildJsonObject(json, "lab_origin.data");

        image = jsonLabData.get("userImage").getAsString();

        JsonArray arrLandmark118 = jsonLabData.getAsJsonArray("landmark118");
        for(JsonElement e : arrLandmark118) {
            JsonArray arrPoint = e.getAsJsonArray();
            if(arrPoint != null && arrPoint.size() > 1) {
                landmark118.add(new PointF(arrPoint.get(0).getAsFloat(), arrPoint.get(1).getAsFloat()));
            } else {
                landmark118.add(new PointF());
            }
        }

        JsonArray arrLandmark171 = jsonLabData.getAsJsonArray("landmark171");
        for(JsonElement e : arrLandmark171) {
            JsonArray arrPoint = e.getAsJsonArray();
            if(arrPoint != null && arrPoint.size() > 1) {
                landmark171.add(new PointF(arrPoint.get(0).getAsFloat(), arrPoint.get(1).getAsFloat()));
            } else {
                landmark171.add(new PointF());
            }
        }
    }

    public String getImage() {
        return image;
    }

    public List<PointF> getLandmark118() {
        return landmark118;
    }

    public List<PointF> getLandmark171() {
        return landmark171;
    }
}
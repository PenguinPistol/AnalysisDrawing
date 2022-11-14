package me.penguinpistol.analysisdrawing;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Consumer;
import java.util.regex.PatternSyntaxException;

import me.penguinpistol.analysisdrawing.data.AnalysisData;
import me.penguinpistol.analysisdrawing.data.ResponseData;

public class MainViewModel extends ViewModel {

    public void getMeituData(Context context, Consumer<AnalysisData> callback) {
        Gson gson = new Gson();
        try {
            InputStream is = context.getAssets().open("meitu.json");
            Reader reader = new InputStreamReader(is);
            ResponseData response = gson.fromJson(reader, ResponseData.class);
            AnalysisData data = new AnalysisData(response.getData());
            callback.accept(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject findChildJsonObject(@Nullable JsonObject root, @Nullable String path) {
        if(root == null || path == null) {
            return null;
        }

        try {
            String[] locations = path.split("\\.");
            JsonObject json = root.getAsJsonObject(locations[0]);
            for (int i = 1; i < locations.length; i++) {
                json = json.getAsJsonObject(locations[i]);
            }
            return json;
        }catch (PatternSyntaxException e) {
            Log.e("Drawing", "findChildJsonObject() >> Path Split Failed : " + path, e);
        } catch (JsonSyntaxException e) {
            Log.e("Drawing", "findChildJsonObject() >> Json Syntax Error : \n" + "json : " + root + "\n" + "path :" + path, e);
        } catch (Exception e) {
            Log.e("Drawing", "findChildJsonObject() >> Unknown Error", e);
            e.printStackTrace();
        }
        return null;
    }
}

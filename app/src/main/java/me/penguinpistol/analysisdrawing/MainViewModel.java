package me.penguinpistol.analysisdrawing;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.PatternSyntaxException;

import me.penguinpistol.analysisdrawing.data.AnalysisData;
import me.penguinpistol.analysisdrawing.data.ResponseData;
import me.penguinpistol.analysisdrawing.drawing.model.DoubleEyelid;
import me.penguinpistol.analysisdrawing.drawing.model.EyeAndEyebrowGap;
import me.penguinpistol.analysisdrawing.drawing.model.EyeShape;
import me.penguinpistol.analysisdrawing.drawing.model.EyeSize;
import me.penguinpistol.analysisdrawing.drawing.model.EyeSpacing;
import me.penguinpistol.analysisdrawing.drawing.model.EyebrowGap;
import me.penguinpistol.analysisdrawing.drawing.model.EyebrowLength;
import me.penguinpistol.analysisdrawing.drawing.model.EyebrowShape;
import me.penguinpistol.analysisdrawing.drawing.model.FaceAsymmetry;
import me.penguinpistol.analysisdrawing.drawing.model.FaceHorizontalRatio;
import me.penguinpistol.analysisdrawing.drawing.model.FaceShape;
import me.penguinpistol.analysisdrawing.drawing.model.FaceVerticalRatio;
import me.penguinpistol.analysisdrawing.drawing.model.GoldenTriangle;
import me.penguinpistol.analysisdrawing.drawing.model.LipMountain;
import me.penguinpistol.analysisdrawing.drawing.model.LipThickness;
import me.penguinpistol.analysisdrawing.drawing.model.NoseLength;
import me.penguinpistol.analysisdrawing.drawing.model.NoseWidth;
import me.penguinpistol.analysisdrawing.drawing.model.Ptosis;

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


    public List<Pair<String, Parts[]>> getParts() {
        List<Pair<String, Parts[]>> items = new ArrayList<>();
        //
        items.add(new Pair<>("얼굴비율", new Parts[] {
                new Parts(FaceHorizontalRatio.class, "가로비율"),
                new Parts(FaceVerticalRatio.class, "세로비율"),
                new Parts(GoldenTriangle.class, "황금삼각존"),
                new Parts(FaceAsymmetry.class, "얼굴비대칭"),
        }));
        //
        items.add(new Pair<>("얼굴형", new Parts[] { new Parts(FaceShape.class, "얼굴형")}));
        //
        items.add(new Pair<>("눈", new Parts[] {
                new Parts(EyeSize.class, "눈 크기"),
                new Parts(EyeSpacing.class, "눈 간격"),
                new Parts(EyeShape.class, "눈매")
        }));
        //
        items.add(new Pair<>("쌍꺼풀", new Parts[] {
                new Parts(DoubleEyelid.class, "쌍꺼풀"),
                new Parts(Ptosis.class, "안검하수"),
                new Parts(EyeAndEyebrowGap.class, "눈과 눈썹거리")
        }));
        //
        items.add(new Pair<>("눈썹", new Parts[] {
                new Parts(EyebrowShape.class, "눈썹 모양")
                , new Parts(EyebrowLength.class, "눈썹 길이")
                , new Parts(EyebrowGap.class, "눈썹 간격")
        }));
        //
        items.add(new Pair<>("코", new Parts[] {
                new Parts(NoseLength.class, "코길이")
                , new Parts(NoseWidth.class, "코너비")
        }));
        //
        items.add(new Pair<>("입", new Parts[] {
                new Parts(LipThickness.class, "입술두께")
                , new Parts(LipMountain.class, "입술산")
        }));

        return items;
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

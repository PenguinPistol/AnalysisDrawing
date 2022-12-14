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
import me.penguinpistol.analysisdrawing.drawing.model.face.CheekBone;
import me.penguinpistol.analysisdrawing.drawing.model.face.DoubleEyelid;
import me.penguinpistol.analysisdrawing.drawing.model.face.EyeAndEyebrowGap;
import me.penguinpistol.analysisdrawing.drawing.model.face.EyeShape;
import me.penguinpistol.analysisdrawing.drawing.model.face.EyeSize;
import me.penguinpistol.analysisdrawing.drawing.model.face.EyeSpacing;
import me.penguinpistol.analysisdrawing.drawing.model.face.EyebrowGap;
import me.penguinpistol.analysisdrawing.drawing.model.face.EyebrowLength;
import me.penguinpistol.analysisdrawing.drawing.model.face.EyebrowShape;
import me.penguinpistol.analysisdrawing.drawing.model.face.FaceAsymmetry;
import me.penguinpistol.analysisdrawing.drawing.model.face.FaceHorizontalRatio;
import me.penguinpistol.analysisdrawing.drawing.model.face.FaceShape;
import me.penguinpistol.analysisdrawing.drawing.model.face.FaceVerticalRatio;
import me.penguinpistol.analysisdrawing.drawing.model.face.FrontCheek;
import me.penguinpistol.analysisdrawing.drawing.model.face.FrontJaw;
import me.penguinpistol.analysisdrawing.drawing.model.face.GoldenTriangle;
import me.penguinpistol.analysisdrawing.drawing.model.face.LipMountain;
import me.penguinpistol.analysisdrawing.drawing.model.face.LipTail;
import me.penguinpistol.analysisdrawing.drawing.model.face.LipThickness;
import me.penguinpistol.analysisdrawing.drawing.model.face.NoseLength;
import me.penguinpistol.analysisdrawing.drawing.model.face.NoseWidth;
import me.penguinpistol.analysisdrawing.drawing.model.face.Philtrum;
import me.penguinpistol.analysisdrawing.drawing.model.face.Ptosis;
import me.penguinpistol.analysisdrawing.drawing.model.face.SquareJaw;
import me.penguinpistol.analysisdrawing.drawing.model.skin.AcneScar;
import me.penguinpistol.analysisdrawing.drawing.model.skin.Blackhead;
import me.penguinpistol.analysisdrawing.drawing.model.skin.Blemishes;
import me.penguinpistol.analysisdrawing.drawing.model.skin.CrowFeetWrinkle;
import me.penguinpistol.analysisdrawing.drawing.model.skin.DarkCircle;
import me.penguinpistol.analysisdrawing.drawing.model.skin.EyeWrinkle;
import me.penguinpistol.analysisdrawing.drawing.model.skin.ForeheadWrinkle;
import me.penguinpistol.analysisdrawing.drawing.model.skin.Mole;
import me.penguinpistol.analysisdrawing.drawing.model.skin.NasolabialFold;
import me.penguinpistol.analysisdrawing.drawing.model.skin.Pimple;
import me.penguinpistol.analysisdrawing.drawing.model.skin.Pore;
import me.penguinpistol.analysisdrawing.drawing.model.skin.SkinAge;
import me.penguinpistol.analysisdrawing.drawing.model.skin.SkinTone;
import me.penguinpistol.analysisdrawing.drawing.model.skin.SkinType;

// TODO ???????????? ViewModel ??? ????????????
public class MainViewModel extends ViewModel {
    private final MainRepository repository = new MainRepository();
    private static final int PHOTO_INDEX = 4402;

    public void test() {
        repository.getAnalysisResult(PHOTO_INDEX);
    }

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

    public List<Pair<String, Parts[]>> getFaceParts() {
        List<Pair<String, Parts[]>> items = new ArrayList<>();
        // ????????????
        items.add(new Pair<>("????????????", new Parts[] {
                new Parts(FaceHorizontalRatio.class, "????????????"),
                new Parts(FaceVerticalRatio.class, "????????????"),
                new Parts(GoldenTriangle.class, "???????????????"),
                new Parts(FaceAsymmetry.class, "???????????????"),
        }));
        // ?????????
        items.add(new Pair<>("?????????", new Parts[] { new Parts(FaceShape.class, "?????????")}));
        // ???
        items.add(new Pair<>("???", new Parts[] {
                new Parts(EyeSize.class, "??? ??????"),
                new Parts(EyeSpacing.class, "??? ??????"),
                new Parts(EyeShape.class, "??????")
        }));
        // ?????????
        items.add(new Pair<>("?????????", new Parts[] {
                new Parts(DoubleEyelid.class, "?????????"),
                new Parts(Ptosis.class, "????????????"),
                new Parts(EyeAndEyebrowGap.class, "?????? ????????????")
        }));
        // ??????
        items.add(new Pair<>("??????", new Parts[] {
                new Parts(EyebrowShape.class, "?????? ??????")
                , new Parts(EyebrowLength.class, "?????? ??????")
                , new Parts(EyebrowGap.class, "?????? ??????")
        }));
        // ???
        items.add(new Pair<>("???", new Parts[] {
                new Parts(NoseLength.class, "?????????")
                , new Parts(NoseWidth.class, "?????????")
        }));
        // ???
        items.add(new Pair<>("???", new Parts[] {
                new Parts(LipThickness.class, "????????????")
                , new Parts(LipMountain.class, "?????????")
                , new Parts(LipTail.class, "?????????")
                , new Parts(Philtrum.class, "??????")
        }));
        // ??????
        items.add(new Pair<>("??????", new Parts[] {
                new Parts(CheekBone.class, "?????????")
                , new Parts(FrontJaw.class, "??????")
                , new Parts(SquareJaw.class, "?????????")
                , new Parts(FrontCheek.class, "??????")
        }));

        return items;
    }

    public List<Pair<String, Parts[]>> getSkinParts() {
        List<Pair<String, Parts[]>> items = new ArrayList<>();
        // ????????????
        items.add(new Pair<>("????????????", new Parts[] {
                new Parts(SkinType.class, "????????????")
        }));
        // ??????
        items.add(new Pair<>("??????", new Parts[] {
                new Parts(ForeheadWrinkle.class, "????????????")
                , new Parts(NasolabialFold.class, "????????????")
                , new Parts(EyeWrinkle.class, "????????????")
                , new Parts(CrowFeetWrinkle.class, "????????????")
        }));
        //??????
        items.add(new Pair<>("??????", new Parts[] {
                new Parts(Pore.class, "??????")
                , new Parts(Blackhead.class, "????????????")
        }));
        // ?????????
        items.add(new Pair<>("?????????", new Parts[] {
                new Parts(Pimple.class, "?????????")
                , new Parts(AcneScar.class, "???????????????")
        }));
        // ???
        items.add(new Pair<>("???", new Parts[] {
                new Parts(Mole.class, "???")
                , new Parts(Blemishes.class, "??????")
        }));
        // ????????????
        items.add(new Pair<>("????????????", new Parts[] {
                new Parts(DarkCircle.class, "????????????")
        }));
        // ?????????
        items.add(new Pair<>("?????????", new Parts[] {
                new Parts(SkinTone.class, "?????????")
        }));
        // ????????????
        items.add(new Pair<>("????????????", new Parts[] {
                new Parts(SkinAge.class, "????????????")
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

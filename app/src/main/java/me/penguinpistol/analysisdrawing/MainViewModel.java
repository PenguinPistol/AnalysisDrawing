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
import me.penguinpistol.analysisdrawing.drawing.model.skin.SkinTone;
import me.penguinpistol.analysisdrawing.drawing.model.skin.SkinType;

// TODO 일반적인 ViewModel 로 변경하기
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
        // 얼굴비율
        items.add(new Pair<>("얼굴비율", new Parts[] {
                new Parts(FaceHorizontalRatio.class, "가로비율"),
                new Parts(FaceVerticalRatio.class, "세로비율"),
                new Parts(GoldenTriangle.class, "황금삼각존"),
                new Parts(FaceAsymmetry.class, "얼굴비대칭"),
        }));
        // 얼굴형
        items.add(new Pair<>("얼굴형", new Parts[] { new Parts(FaceShape.class, "얼굴형")}));
        // 눈
        items.add(new Pair<>("눈", new Parts[] {
                new Parts(EyeSize.class, "눈 크기"),
                new Parts(EyeSpacing.class, "눈 간격"),
                new Parts(EyeShape.class, "눈매")
        }));
        // 쌍꺼풀
        items.add(new Pair<>("쌍꺼풀", new Parts[] {
                new Parts(DoubleEyelid.class, "쌍꺼풀"),
                new Parts(Ptosis.class, "안검하수"),
                new Parts(EyeAndEyebrowGap.class, "눈과 눈썹거리")
        }));
        // 눈썹
        items.add(new Pair<>("눈썹", new Parts[] {
                new Parts(EyebrowShape.class, "눈썹 모양")
                , new Parts(EyebrowLength.class, "눈썹 길이")
                , new Parts(EyebrowGap.class, "눈썹 간격")
        }));
        // 코
        items.add(new Pair<>("코", new Parts[] {
                new Parts(NoseLength.class, "코길이")
                , new Parts(NoseWidth.class, "코너비")
        }));
        // 입
        items.add(new Pair<>("입", new Parts[] {
                new Parts(LipThickness.class, "입술두께")
                , new Parts(LipMountain.class, "입술산")
                , new Parts(LipTail.class, "입꼬리")
                , new Parts(Philtrum.class, "인중")
        }));
        // 윤곽
        items.add(new Pair<>("윤곽", new Parts[] {
                new Parts(CheekBone.class, "광대뼈")
                , new Parts(FrontJaw.class, "앞턱")
                , new Parts(SquareJaw.class, "사각턱")
                , new Parts(FrontCheek.class, "앞볼")
        }));

        return items;
    }

    public List<Pair<String, Parts[]>> getSkinParts() {
        List<Pair<String, Parts[]>> items = new ArrayList<>();
        // 피부유형
        items.add(new Pair<>("피부유형", new Parts[] {
                new Parts(SkinType.class, "피부유형")
        }));
        // 주름
        items.add(new Pair<>("주름", new Parts[] {
                new Parts(ForeheadWrinkle.class, "이마주름")
                , new Parts(NasolabialFold.class, "팔자주름")
                , new Parts(EyeWrinkle.class, "눈밑주름")
                , new Parts(CrowFeetWrinkle.class, "눈가주름")
        }));
        //모공
        items.add(new Pair<>("모공", new Parts[] {
                new Parts(Pore.class, "모공")
                , new Parts(Blackhead.class, "블랙헤드")
        }));
        // 여드름
        items.add(new Pair<>("여드름", new Parts[] {
                new Parts(Pimple.class, "여드름")
                , new Parts(AcneScar.class, "여드름자국")
        }));
        // 점
        items.add(new Pair<>("점", new Parts[] {
                new Parts(Mole.class, "점")
                , new Parts(Blemishes.class, "잡티")
        }));
        // 다크서클
        items.add(new Pair<>("다크서클", new Parts[] {
                new Parts(DarkCircle.class, "다크서클")
        }));
        // 피부톤
        items.add(new Pair<>("피부톤", new Parts[] {
                new Parts(SkinTone.class, "피부톤")
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

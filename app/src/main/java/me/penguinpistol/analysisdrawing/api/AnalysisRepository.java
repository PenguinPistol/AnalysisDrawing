package me.penguinpistol.analysisdrawing.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import me.penguinpistol.analysisdrawing.data.ResponseData;
import retrofit2.Call;
import retrofit2.Response;

public class AnalysisRepository {

    public void getMeituData(int photoIdx) {
//        Call<ResponseData<JsonObject>> call = ApiClient.getApi().connectionMeitu(photoIdx);
//        call.enqueue(new ApiCallback<JsonObject>() {
//            @Override
//            public void onPostResponse(@Nullable JsonObject data) {
//
//            }
//
//            @Override
//            public void onPostFailure(@NonNull Call<ResponseData<JsonObject>> call, @NonNull String code, @Nullable String message) {
//
//            }
//        });
    }

    private void getFaceAnalysis(int photoIdx) {
        final String[] faces = new String[] {
                ""
        };

        AnalysisData data = new AnalysisData();

        for(String face : faces) {
//            Call<ResponseData<JsonObject>> call = ApiClient.getApi().analysis(face, photoIdx);
//            try {
//                Response<ResponseData<JsonObject>> response = call.execute();
//                JsonObject dataJson = checkResponse(response);
//                if(dataJson != null) {
//                    // 데이터 파싱
//                    /*
//                        JsonElement findJson = JsonUtil.findChild("lab_face_ratio.data.details.common.upperAndLowerFaceLength.upperAndLowerFaceRatioMiddleLength");
//                        if(findJson != null) {
//
//                        }
//                    */
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private JsonObject checkResponse(Response<ResponseData> response) {
        final int httpStatusCode = response.code();

        if(httpStatusCode == 200 || httpStatusCode == 201) {
//            ResponseData<JsonObject> apiResult = response.body();
//            if(apiResult == null) {
//                return null;
//            } else {
//                String apiResultCode = apiResult.getCode();
//                if("00".equals(apiResultCode)) {
//                    return apiResult.getData();
//                } else {
//                    return null;
//                }
//            }
        }

        return null;
    }
}

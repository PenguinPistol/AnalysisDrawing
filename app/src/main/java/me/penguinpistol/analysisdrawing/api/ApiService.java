package me.penguinpistol.analysisdrawing.api;

import com.google.gson.JsonObject;

import me.penguinpistol.analysisdrawing.data.ResponseData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("app/lab/newConnectionMeitu2")
    Call<ResponseData> connectionMeitu(@Field("photoIdx") int photoIdx);

    /**
     * 분석
     */
    @FormUrlEncoded
    @POST("app/lab/{analysisPath}")
    Call<ResponseData> analysis(@Path("analysisPath") String analysisPath, @Field("photoIdx") int photoIdx);

    /**
     * 얼굴 총평
     */
    @FormUrlEncoded
    @POST("app/lab/allScoreForApp")
    Call<ResponseData> analysisFaceOverall(@Field("photoIdx") int photoIdx);

    /**
     * 피부 총평
     */
    @FormUrlEncoded
    @POST("app/lab/skinAllScoreForApp")
    Call<ResponseData> analysisSkinOverall(@Field("photoIdx") int photoIdx);
}

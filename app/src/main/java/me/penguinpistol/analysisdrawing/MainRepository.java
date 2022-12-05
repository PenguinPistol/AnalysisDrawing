package me.penguinpistol.analysisdrawing;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import me.penguinpistol.analysisdrawing.api.ApiCallback;
import me.penguinpistol.analysisdrawing.api.ApiClient;
import me.penguinpistol.analysisdrawing.data.ResponseData;
import retrofit2.Call;

public class MainRepository {

    public void getAnalysisResult(int photoIdx) {
        Call<ResponseData<JsonObject>> callMeitu = ApiClient.getApi().connectionMeitu(photoIdx);
        callMeitu.enqueue(new ApiCallback<JsonObject>() {
            @Override
            public void onPostResponse(@Nullable JsonObject data) {

            }

            @Override
            public void onPostFailure(@NonNull Call<ResponseData<JsonObject>> call, @NonNull String code, @Nullable String message) {
                Log.d("PPP", code + " >> " + message);
            }
        });
    }

}

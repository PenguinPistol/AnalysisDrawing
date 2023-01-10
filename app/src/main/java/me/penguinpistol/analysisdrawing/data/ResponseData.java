package me.penguinpistol.analysisdrawing.data;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ResponseData<T> {
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private JsonObject data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getData() {
        return data;
    }

    @NonNull
    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

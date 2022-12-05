package me.penguinpistol.analysisdrawing.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.penguinpistol.analysisdrawing.data.ResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<ResponseData<T>> {
    private static final int STATUS_OK = 200;
    private static final int STATUS_CREATED = 201;
    private static final int STATUS_MOVED_PERMANENTLY = 301;
    private static final int STATUS_BAD_REQUEST = 400;
    private static final int STATUS_UNAUTHORIZED = 401;
    private static final int STATUS_FORBIDDEN = 403;
    private static final int STATUS_NOT_FOUND = 404;
    private static final int STATUS_METHOD_NOT_ALLOWED = 405;
    private static final int STATUS_TOO_MANY_REQUEST = 429;
    private static final int STATUS_INTERNAL_SERVER_ERROR = 500;

    private static final String RESULT_SUCCESS = "00";
    private static final String RESULT_FAIL = "01";
    private static final String RESULT_NOT_AVAILABLE = "96";
    private static final String RESULT_UNAUTHORIZED = "97";
    private static final String RESULT_AUTH_EXPIRED = "98";
    private static final String RESULT_SERVER_EXCEPTION = "99";

    @Override
    public void onResponse(@NonNull Call<ResponseData<T>> call, @NonNull Response<ResponseData<T>> response) {
        final int httpStatusCode = response.code();

        if(httpStatusCode == STATUS_OK || httpStatusCode == STATUS_CREATED) {
            ResponseData<T> apiResult = response.body();
            if(apiResult == null) {
                onPostFailure(call, "-1", "api result is null");
            } else {
                String apiResultCode = apiResult.getCode();
                if(RESULT_SUCCESS.equals(apiResultCode)) {
                    onPostResponse(apiResult.getData());
                } else {
                    onPostFailure(call, apiResultCode, apiResult.getMessage());
                }
            }
        } else {
            onPostFailure(call, String.valueOf(httpStatusCode), "HTTP STATUS ERROR");
        }
    }

    @Override
    public void onFailure(@NonNull Call<ResponseData<T>> call, @NonNull Throwable t) {
        onPostFailure(call, "-99", t.getMessage());
    }

    public abstract void onPostResponse(@Nullable T data);
    public abstract void onPostFailure(@NonNull Call<ResponseData<T>> call, @NonNull String code, @Nullable String message);
}

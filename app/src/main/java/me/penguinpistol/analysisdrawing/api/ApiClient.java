package me.penguinpistol.analysisdrawing.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import me.penguinpistol.analysisdrawing.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiM2E3ODQ3OTJiNTBkNWY3MmQzZDRmZDBkNjgxZWFiZTQ0NjQ0MWJlYzVhNmYzNzRmZjI3ZjU1OWY5ZDdlNjZiZGE3NTQwYTA2NTJjZDBhYTUiLCJpYXQiOjE2NzAyMDI2MTEuNDQwMzYxLCJuYmYiOjE2NzAyMDI2MTEuNDQwMzYzLCJleHAiOjE3MDE3Mzg2MTEuNDMzNTU1LCJzdWIiOiIyODkiLCJzY29wZXMiOlsiKiJdfQ.W01D9Q6PXi5xDJgs-ffqnJq21bdGvqS_xG8-FVxLN4r8glKDTMKfqCDyxbE6xFoBm-GuUG1i9G2DDKfIpZw6gQxvc4UYISZcPiZFognc5kZbRFBlbzhTe887r3V5KP0NwB5liPKH9qaQuVJsB3GaXMYtk5w8Chgy6_O1TVLzPO9pyUQOKIvujmiGqTetb9gZQxrKa3-ji7drgzqHBGz7181JNFY3lkeY4oidykNObQlf6cG1XGn6mt9FWH0_6ySx90sj2PTQcp-BiaWGkgyO0o7E6NJ_JvJ5ozJNyzBXpmAHXKZe9lPW3CxLhUA4yEvuGEMexkCzqPFDwPy2MNcWLCagkDmD4-ajkSvWDJhxOdvmXJ8bbVkc-J_hIjMOeQKEVcXNfXBSlrd6NWiKZ5XvBxaAPcW_UEf6o17w3aX-X08gqcisehMry_hd_VjkVhWZmhMUnaKi7IqBzNeYKhPdqpmm_VXRQaV_4ntyuyldi_l7G6p4q-q8gGQE8yj4MmLzp22Pf9yn14OGjVOhUhjRwzMvIqoIypukepPULZNl3tyHVoE2TMXk-hfStx3veKxznU1veTHCit4gRbyHY56HNhyqWF5Olthd3Krgs6_R9RBYa0A_gWU8y0T8zkVjU5thU8j3IVqbBiw469zCOXzcZeE39yVXqGYcuyx15Yv-9_g";
    private static final String USER_AGENT = "co.natoo.mobile.debugNatoo-Debug";
    private static final Gson gson = new GsonBuilder().setLenient().create();

    public static ApiService getApi() {
        return retrofit().create(ApiService.class);
    }

    private static Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://t.api.natoo.co/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient())
                .build();
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .addNetworkInterceptor(networkInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private static Interceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(log -> Log.d("OkHttpClient", log));
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

    private static Interceptor networkInterceptor() {
        return chain -> {
            Request original = chain.request();
            String accessToken = "Bearer " + ACCESS_TOKEN;
            return chain.proceed(original.newBuilder()
                    .addHeader("Authorization", accessToken)
                    .addHeader("User-Agent", USER_AGENT)
                    .addHeader("Content-Language", "ko")
                    .build());
        };
    }
}

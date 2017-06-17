package com.test.weatherapp.comm;

import android.content.Context;
import android.support.annotation.NonNull;

import com.test.weatherapp.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A facade for communicating with the api endpoints.
 *
 * @author Usman
 */

public class RestAdapter {

    public static final int READ_TIMEOUT_SECS = 60;
    public static final int CONNECTION_TIMEOUT_SECS = 60;

    /**
     * Creates the retrofit instance which could then be used to call api endpoints. See ApiEndpoints.java.
     *
     * @param ctx
     * @return
     */
    public static Retrofit createRetrofit(@NonNull Context ctx) {

        GsonConverterFactory gsonFactory = GsonConverterFactory.create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT_SECS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECS, TimeUnit.SECONDS)
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {

                                Request.Builder builder = chain.request().newBuilder();
                                builder.addHeader("Content-Type", "application/json");
                                Request request = builder.build();
                                Response response = chain.proceed(request);
                                return response;
                            }
                        });


        OkHttpClient httpClient = httpClientBuilder.build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ctx.getString(R.string.open_weather_map_url))
                .addConverterFactory(gsonFactory).client(httpClient).build();

        return retrofit;
    }
}

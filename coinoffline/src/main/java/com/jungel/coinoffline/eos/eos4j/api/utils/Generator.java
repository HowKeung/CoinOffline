package com.jungel.coinoffline.eos.eos4j.api.utils;

import com.jungel.coinoffline.eos.eos4j.api.exception.ApiError;
import com.jungel.coinoffline.eos.eos4j.api.exception.ApiException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Generator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create());

    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        builder.baseUrl(baseUrl);
        builder.client(httpClient.build());
        builder.addConverterFactory(JacksonConverterFactory.create());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
            System.out.println("TEST-- url : " + call.request().url());
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    System.out.println(response.body().toString());
                } else {
                    if (response != null) {
                        System.out.println(response.body());
                    } else {
                        System.out.println(response);
                    }
                }
                return response.body();
            } else {
                System.out.println("TEST-- response.body : " + response.body());
                ApiError apiError = getApiError(response);
                throw new ApiException(apiError);
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    private static ApiError getApiError(Response<?> response) throws IOException, ApiException {
        return (ApiError) retrofit.responseBodyConverter(ApiError.class, new Annotation[0]).convert(response.errorBody());
    }
}

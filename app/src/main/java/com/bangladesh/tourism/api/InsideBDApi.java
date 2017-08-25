package com.bangladesh.tourism.api;

import com.bangladesh.tourism.api.services.ApiService;
import com.bangladesh.tourism.api.services.LoginService;
import com.bangladesh.tourism.models.ApiResponse;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit;

/**
 * Created by Zakir on 16/02/2016.
 */
@Singleton
public class InsideBDApi {

    private Retrofit sampleAdapter;

    @Inject
    public InsideBDApi() {

    }

    private Retrofit getSampleAdapter() {
        if(sampleAdapter == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();
            sampleAdapter = new Retrofit.Builder()
                    .baseUrl("http://dev-zakir.tripcam.com/autoapi/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return sampleAdapter;
    }



    public Object getService(String serviceName) {

        Object apiService = null;

        switch (serviceName) {
            case ApiService.SAMPLE_SERVICE:
                apiService = getSampleAdapter().create(LoginService.class);
                break;
            default:
                break;
        }

        return apiService;
    }

    private Interceptor getInterceptor()
    {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl url = chain.request().url()
                        .newBuilder()
                        .addQueryParameter("type", "android-Companion")
                        .build();
                Request request = chain.request().newBuilder().url(url).build();
                return chain.proceed(request);
                //return null;
            }
        };
    }
}

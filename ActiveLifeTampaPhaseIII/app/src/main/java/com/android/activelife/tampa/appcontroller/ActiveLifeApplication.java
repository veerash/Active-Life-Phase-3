package com.android.activelife.tampa.appcontroller;

import android.app.Application;

import com.android.activelife.tampa.services.request.ApiRequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vsatrasala on 2/8/2017.
 */

public class ActiveLifeApplication extends Application{

    public Retrofit jRetrofit;
    public ApiRequest jApiRequestService;
    public static String BASE_URL="https://api.github.com/";
    @Override
    public void onCreate() {
        super.onCreate();



    }

    public Retrofit getRetrofit(String url){
        if(jRetrofit==null){
            jRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return jRetrofit;
    }

    public ApiRequest getApiRequest(String url){
        if(jApiRequestService==null) {
            jApiRequestService = getRetrofit(url).create(ApiRequest.class);
        }
        return jApiRequestService;
    }
}

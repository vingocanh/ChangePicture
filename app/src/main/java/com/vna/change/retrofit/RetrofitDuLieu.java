package com.vna.change.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDuLieu {

    public static Retrofit retrofit = null;

    public static String url = "https://apipixivcustom.herokuapp.com/";
    public static String url1 = "https://api.flickr.com/";


    public static Retrofit getInstance(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
        }

        return retrofit;
    }

    public static Retrofit getInstancHinhAnh(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(url1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

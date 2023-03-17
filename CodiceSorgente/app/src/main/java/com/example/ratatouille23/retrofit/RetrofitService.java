package com.example.ratatouille23.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private final String ipPCAnielloFisso = "192.168.1.79";
    private final String ipPCAnielloPortatile = "192.168.1.15";
    private final String ipAnielloPortatileUnina = "192.168.231.173";
    private final String ipVittorio = "192.168.0.29";
    private Retrofit retrofit;

    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl("http://" + ipAnielloPortatileUnina + ":8080")
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}

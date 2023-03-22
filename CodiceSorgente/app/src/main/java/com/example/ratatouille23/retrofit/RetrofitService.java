package com.example.ratatouille23.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private final String ipPCAnielloFisso = "192.168.1.79";
    private final String ipPCAnielloPortatile = "192.168.1.30";
    private final String ipAnielloPortatileUnina = "100.74.19.159";
    private final String ipVittorio = "192.168.0.29";
    private Retrofit retrofit;

    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        retrofit = new Retrofit.Builder().baseUrl("http://" + ipPCAnielloPortatile + ":8080")
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}

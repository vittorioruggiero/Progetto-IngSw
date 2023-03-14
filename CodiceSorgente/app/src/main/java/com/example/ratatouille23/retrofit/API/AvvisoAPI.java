package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Avviso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AvvisoAPI {

    @GET("/avviso/get-all")
    Call<List<Avviso>> getAllAvvisi();

    @POST("/avviso/salvataggioAvviso")
    Call<Avviso> salvataggioAvviso(@Body Avviso avviso);

}

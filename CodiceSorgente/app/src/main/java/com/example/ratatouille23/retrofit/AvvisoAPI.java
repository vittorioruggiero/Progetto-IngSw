package com.example.ratatouille23.retrofit;

import com.example.ratatouille23.entity.Avviso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AvvisoAPI {

    @GET("/avviso/get-all")
    Call<List<Avviso>> getAllAvvisi();

    @POST("/avviso/save")
    Call<Avviso> save(@Body Avviso avviso);

}

package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Conto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ContoAPI {

    @GET("/conto/get-all")
    Call<List<Conto>> getAllConto();

    @POST("/conto/save")
    Call<Conto> save(@Body Conto conto);


}

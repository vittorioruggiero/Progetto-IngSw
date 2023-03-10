package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.SingoloOrdine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SingoloOrdineAPI {

    @GET("/singoloOrdine/get-all")
    Call<List<SingoloOrdine>> getAllSingoloOrdine();

    @POST("/singoloOrdine/save")
    Call<SingoloOrdine> save(@Body SingoloOrdine singoloOrdine);

}

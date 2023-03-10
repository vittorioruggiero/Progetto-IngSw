package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.SingoloOrdine;
import com.example.ratatouille23.entity.Supervisore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SupervisoreAPI {

    @GET("/supervisore/get-all")
    Call<List<Supervisore>> getAllSupervisore();

    @POST("/supervisore/save")
    Call<Supervisore> save(@Body Supervisore supervisore);

}

package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.SingoloOrdine;
import com.example.ratatouille23.entity.Supervisore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SupervisoreAPI {

    @GET("/supervisore/get-all")
    Call<List<Supervisore>> getAllSupervisore();

    @GET("/supervisore/get-by-id")
    Call<Supervisore> getSupervisoreById(@Query("email") String email);

    @GET("/supervisore/get-by-username")
    Call<Supervisore> getSupervisoreByUsername(@Query("username") String username);

    @POST("/supervisore/salvataggio-supervisore")
    Call<Supervisore> salvataggioSupervisore(@Body Supervisore supervisore);

}

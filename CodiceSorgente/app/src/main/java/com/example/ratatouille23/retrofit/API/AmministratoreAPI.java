package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Amministratore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AmministratoreAPI {

    @GET("/amministratore/get-all")
    Call<List<Amministratore>> getAllAmministratore();

    @POST("/amministratore/save")
    Call<Amministratore> save(@Body Amministratore amministratore);

}

package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Attivita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddettoSalaAPI {

    @GET("/addettosala/get-all")
    Call<List<AddettoSala>> getAllAddettoSala();

    @GET("/addettosala/get-by-id")
    Call<AddettoSala> getAddettoSalaById(@Query("email") String email);

    @GET("/addettosala/get-by-username")
    Call<AddettoSala> getAddettoSalaByUsername(@Query("username") String username);

    @POST("/addettosala/save")
    Call<AddettoSala> save(@Body AddettoSala addettoSala);


}

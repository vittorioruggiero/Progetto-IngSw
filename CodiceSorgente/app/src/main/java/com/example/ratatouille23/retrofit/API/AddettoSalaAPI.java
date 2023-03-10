package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Attivita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AddettoSalaAPI {

    @GET("/addettosala/get-all")
    Call<List<AddettoSala>> getAllAddettoSala();

    @POST("/addettosala/save")
    Call<AddettoSala> save(@Body AddettoSala addettoSala);


}

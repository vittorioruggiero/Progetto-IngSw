package com.example.ratatouille23.retrofit;

import com.example.ratatouille23.entity.Attivita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AttivitaAPI {

    @GET("/attivita/get-all")
    Call<List<Attivita>> getAllAttivita();

    @POST("/attivita/save")
    Call<Attivita> save(@Body Attivita attivita);

}

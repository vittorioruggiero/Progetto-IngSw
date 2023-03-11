package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.AttivitaPkey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface AttivitaAPI {

    @GET("/attivita/get-all")
    Call<List<Attivita>> getAllAttivita();

    @HTTP(method = "GET", path = "/attivita/get-by-id", hasBody = true)
    Call<Attivita> getAttivitaById(@Body AttivitaPkey attivitaPkey);

    @POST("/attivita/save")
    Call<Attivita> save(@Body Attivita attivita);

    @POST("/attivita/delete-by-id")
    void deleteById(@Body AttivitaPkey attivitaPkey);

    @POST("/attivita/delete")
    void delete(@Body Attivita attivita);

}

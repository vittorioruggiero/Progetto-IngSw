package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Conto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ContoAPI {

    @GET("/conto/get-all")
    Call<List<Conto>> getAllConto();

    @POST("/conto/save")
    Call<Conto> save(@Body Conto conto);

    @POST("/conto/save-con-campi")
    Call<Conto> saveConCampi(@Query("data") java.sql.Date data,
                             @Query("importo") Double importo);

    /*@GET("/attivita/get-by-id")
    Call<Attivita> getAttivitaById(@Query("nome") String nome, @Query("indirizzo") String indirizzo);*/


}

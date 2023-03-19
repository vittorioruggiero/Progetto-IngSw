package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Avviso;
import com.example.ratatouille23.entity.Conto;
import com.example.ratatouille23.entity.Ordinazione;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrdinazioneAPI {

    @GET("/ordinazione/get-all")
    Call<List<Ordinazione>> getAllOrdinazione();

    @GET("/ordinazione/get-all-by-attivita")
    Call<List<Ordinazione>> getAllOrdinazioniByAttivita(@Query("nomeAttivita") String nomeAttivita, @Query("indirizzoAttivita") String indirizzoAttivita);

    @POST("/ordinazione/save")
    Call<Ordinazione> save(@Body Ordinazione ordinazione);

}

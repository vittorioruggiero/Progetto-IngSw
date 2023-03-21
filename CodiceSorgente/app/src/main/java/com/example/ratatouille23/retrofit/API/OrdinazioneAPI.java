package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Ordinazione;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrdinazioneAPI {

    @GET("/ordinazione/get-all")
    Call<List<Ordinazione>> getAllOrdinazione();

    @GET("/ordinazione/get-by-tavolo")
    Call<Ordinazione> getOrdinazioneByTavolo(@Query("nomeAttivita") String nomeAttivita, @Query("indirizzoAttivita") String indirizzoAttivita,
                                                   @Query("numeroTavolo") int numeroTavolo);

    @POST("/ordinazione/save")
    Call<Ordinazione> save(@Body Ordinazione ordinazione);

    @DELETE("/ordinazione/delete-by-id")
    Call<Void> deleteById(@Query("id") int id);

}

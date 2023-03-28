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
    Call<Ordinazione> getOrdinazioneByTavolo(@Query("idAttivita") int idAttivita, @Query("numeroTavolo") int numeroTavolo);

    @POST("/ordinazione/save")
    Call<Ordinazione> save(@Body Ordinazione ordinazione);

    @POST("/ordinazione/save-con-campi")
    Call<Ordinazione> saveConCampi(@Query("numeroTavolo") int numeroTavolo,
                                   @Query("numeroCommensali") int numeroCommensali,
                                   @Query("idAttivita") int idAttivita);

    @DELETE("/ordinazione/delete-by-id")
    Call<Void> deleteById(@Query("id_ordinazione") int id_ordinazione);

}

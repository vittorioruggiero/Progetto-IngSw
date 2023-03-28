package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.SezioneMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SezioneMenuAPI {

    @GET("/sezioneMenu/get-all")
    Call<ArrayList<SezioneMenu>> getAllSezioneMenu();

    @GET("/sezioneMenu/get-by-attivita")
    Call<ArrayList<SezioneMenu>> getSezioniByAttivita(@Query("idAttivita") int idAttivita);

    @POST("/sezioneMenu/save")
    Call<SezioneMenu> save(@Body SezioneMenu sezioneMenu);

    @DELETE("/sezioneMenu/delete-by-id")
    Call<Void> deleteById(@Query("nome") String nome);

}

package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Avviso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AvvisoAPI {

    @GET("/avviso/get-all")
    Call<List<Avviso>> getAllAvvisi();

    @GET("/avviso/get-all-by-attivita")
    Call<List<Avviso>> getAllAvvisiByAttivita(@Query("nomeAttivita") String nomeAttivita, @Query("indirizzoAttivita") String indirizzoAttivita);

    @GET("/avviso/get-by-email")
    Call<List<Avviso>> getAllAvvisiByEmail(@Query("email") String email);

    @POST("/avviso/salvataggioAvviso")
    Call<Avviso> salvataggioAvviso(@Body Avviso avviso);

    @DELETE("/avviso/delete-by-id")
    Call<Void> deleteById(@Query("id") int id);

}

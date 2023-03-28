package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Immagine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ImmagineAPI {

    @GET("/immagine/get-by-id-attivita")
    Call<Immagine> getByIdAttivita (@Query("idAttivita") int idAttivita);



    @POST("/immagine/salva")
    Call<Immagine> salvaImmagine(@Query("uri") String uri,
                                 @Query("idAttivita") int idAttivita);
}

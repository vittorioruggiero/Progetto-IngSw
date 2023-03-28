package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.AttivitaPkey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AttivitaAPI {

    @GET("/attivita/get-all")
    Call<List<Attivita>> getAllAttivita();

    @GET("/attivita/get-by-id")
    Call<Attivita> getAttivitaById(@Query("id") int id);

    @POST("/attivita/save")
    Call<Attivita> save(@Body Attivita attivita);

    @POST("/attivita/update-by-id")
    Call<Attivita> update(@Query("id") int id,
                          @Query("nome") String nome,
                          @Query("indirizzo") String indirizzo,
                          @Query("telefono") String telefono,
                          @Query("capienza") int capienza);

    @POST("/attivita/delete-by-id")
    void deleteById(@Query("id") int id);

    @POST("/attivita/delete")
    void delete(@Body Attivita attivita);

}

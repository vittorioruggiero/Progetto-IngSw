package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.ProdottoMenu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProdottoMenuAPI {

    @GET("/prodottoMenu/get-all")
    Call<List<ProdottoMenu>> getAllProdottoMenu();

    @GET("/prodottoMenu/get-by-id")
    Call<ProdottoMenu> getProdottoById(@Query("nome") String nome);

    @GET("/prodottoMenu/get-by-sezione")
    Call<List<ProdottoMenu>> getProdottiBySezione(@Query("nomeSezione") String nomeSezione);

    @POST("/prodottoMenu/save")
    Call<ProdottoMenu> save(@Body ProdottoMenu prodottoMenu);

    @DELETE("/prodottoMenu/delete-by-id")
    Call<Void> deleteById(@Query("nomeProdotto") String nomeProdotto);

}

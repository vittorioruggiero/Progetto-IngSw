package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.ProdottoMenu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProdottoMenuAPI {

    @GET("/prodottoMenu/get-all")
    Call<List<ProdottoMenu>> getAllProdottoMenu();

    @POST("/prodottoMenu/save")
    Call<ProdottoMenu> save(@Body ProdottoMenu prodottoMenu);

}

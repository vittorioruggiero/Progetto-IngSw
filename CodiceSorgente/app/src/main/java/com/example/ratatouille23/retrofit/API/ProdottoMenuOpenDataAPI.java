package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.ProdottoMenuOpenData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProdottoMenuOpenDataAPI {

    @GET("/prodottoMenuOpenData/get-all")
    Call<List<ProdottoMenuOpenData>> getAllProdottoMenuOpenData();

    @GET("/prodottoMenuOpenData/get-by-nome")
    Call<ProdottoMenuOpenData> getProdottoByNome(@Query("nome") String nome);

}

package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.SezioneMenu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SezioneMenuAPI {

    @GET("/sezioneMenu/get-all")
    Call<List<SezioneMenu>> getAllSezioneMenu();

    @POST("/sezioneMenu/save")
    Call<SezioneMenu> save(@Body SezioneMenu sezioneMenu);

}

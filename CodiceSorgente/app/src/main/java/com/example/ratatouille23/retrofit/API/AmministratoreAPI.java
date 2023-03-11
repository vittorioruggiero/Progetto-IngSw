package com.example.ratatouille23.retrofit.API;

import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.AttivitaPkey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AmministratoreAPI {

    @GET("/admin/get-all")
    Call<List<Amministratore>> getAllAmministratore();

    @HTTP(method = "GET", path = "/admin/get-by-id", hasBody = true)
    Call<Amministratore> getAdminById(@Body String email);

    @HTTP(method = "GET", path = "/admin/get-by-username", hasBody = true)
    Call<Amministratore> getAdminByUsername(@Body String username);

    @POST("/admin/salvataggioAdmin")
    Call<Amministratore> salvataggioAdmin(@Body Amministratore amministratore);


}

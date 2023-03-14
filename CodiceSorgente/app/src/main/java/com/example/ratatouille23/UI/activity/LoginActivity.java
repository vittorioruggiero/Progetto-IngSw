package com.example.ratatouille23.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.AddettoSalaAPI;
import com.example.ratatouille23.retrofit.API.AmministratoreAPI;
import com.example.ratatouille23.retrofit.API.AttivitaAPI;
import com.example.ratatouille23.retrofit.API.SupervisoreAPI;
import com.example.ratatouille23.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText nomeUtenteEditText, passwordEditText;
    private TextView campiErratiTextView, registratiTextView;
    private Button accediButton;
    private AmministratoreAPI amministratoreAPI;
    private AddettoSalaAPI addettoSalaAPI;
    private SupervisoreAPI supervisoreAPI;
    private RetrofitService retrofitService;
    private static Amministratore admin;
    private static AddettoSala addettoSala;
    private static Supervisore supervisore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofitService = new RetrofitService();
        amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);
        supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
        addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);


        registratiTextView = findViewById(R.id.registratiTextView);
        String registratiText = "Hai un ristorante? Registrati";

        SpannableString spannableString = new SpannableString(registratiText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistratiActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan, 19,29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registratiTextView.setText(spannableString);
        registratiTextView.setMovementMethod(LinkMovementMethod.getInstance());

        Login();
    }

    void Login(){

        nomeUtenteEditText = findViewById(R.id.nomeUtenteEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        campiErratiTextView = findViewById(R.id.campiErratiTextView);
        accediButton = findViewById(R.id.accediButton);
        accediButton.setOnClickListener(v -> {

            String username = nomeUtenteEditText.getText().toString();
            checkAdmin(username);

        });

    }

    private void checkAddettoSala(String username) {

        addettoSalaAPI.getAddettoSalaByUsername(username)
                .enqueue(new Callback<AddettoSala>() {
                    @Override
                    public void onResponse(Call<AddettoSala> call, Response<AddettoSala> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            addettoSala = response.body();
                            if(addettoSala != null){
                                if(addettoSala.getPrimoAccesso()){
                                    Intent intent = new Intent(LoginActivity.this, ReimpostaPasswordActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                }else if(nomeUtenteEditText.getText().toString().equals(addettoSala.getNomeUtente())
                                        && passwordEditText.getText().toString().equals(addettoSala.getPassword())){
                                    campiErratiTextView.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(LoginActivity.this, HomeAddettoSalaActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                }
                            }
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                            campiErratiTextView.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onFailure(Call<AddettoSala> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(LoginActivity.this, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void checkSupervisore(String username) {

        supervisoreAPI.getSupervisoreByUsername(username)
                .enqueue(new Callback<Supervisore>() {
                    @Override
                    public void onResponse(Call<Supervisore> call, Response<Supervisore> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            supervisore = response.body();
                            if(supervisore != null){
                                if(supervisore.getPrimoAccesso()){
                                    Intent intent = new Intent(LoginActivity.this, ReimpostaPasswordActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                }else if(nomeUtenteEditText.getText().toString().equals(supervisore.getNomeUtente())
                                        && passwordEditText.getText().toString().equals(supervisore.getPassword())){
                                    campiErratiTextView.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(LoginActivity.this, HomeSupervisoreActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                }
                            }
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Trying addettosala...");
                            checkAddettoSala(username);
                        }

                    }

                    @Override
                    public void onFailure(Call<Supervisore> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(LoginActivity.this, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void checkAdmin(String username) {

        amministratoreAPI.getAdminByUsername(username)
                .enqueue(new Callback<Amministratore>() {
                    @Override
                    public void onResponse(Call<Amministratore> call, Response<Amministratore> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            admin = response.body();
                            if(admin != null){
                                if(nomeUtenteEditText.getText().toString().equals(admin.getNomeUtenteAmministratore())
                                        && passwordEditText.getText().toString().equals(admin.getPasswordAmministratore())){
                                    Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                    campiErratiTextView.setVisibility(View.INVISIBLE);
                                }
                            }
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Trying supervisore...");
                            checkSupervisore(username);
                        }

                    }
                    @Override
                    public void onFailure(Call<Amministratore> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(LoginActivity.this, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public static Amministratore getAdmin(){
        return admin;
    }

    public static AddettoSala getAddettoSala(){
        return addettoSala;
    }

    public static Supervisore getSupervisore(){
        return supervisore;
    }
}
package com.example.ratatouille23.UI.activity;

import static com.example.ratatouille23.UI.activity.LoginActivity.clearAll;
import static com.example.ratatouille23.UI.activity.LoginActivity.getAddettoSala;
import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.AddettoSalaAPI;
import com.example.ratatouille23.retrofit.API.AmministratoreAPI;
import com.example.ratatouille23.retrofit.API.SupervisoreAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReimpostaPasswordActivity extends AppCompatActivity {

    private EditText passwordEditText, confermaPasswordEditText;
    private TextView campiNonCompilatiTextView, passwordDiverseTextView;
    private Button confermaButton;
    private AddettoSala addettoSala;
    private Supervisore supervisore;
    private AddettoSalaAPI addettoSalaAPI;
    private SupervisoreAPI supervisoreAPI;
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimposta_password);

        retrofitService = new RetrofitService();
        supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
        addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);

        ResetPassword();
    }

    void ResetPassword(){

        addettoSala = getAddettoSala();
        supervisore = getSupervisore();

        passwordEditText = findViewById(R.id.passwordEditText);
        confermaPasswordEditText = findViewById(R.id.confermaPasswordEditText);
        confermaButton = findViewById(R.id.confermaButton);
        campiNonCompilatiTextView = findViewById(R.id.campiNonCompilatiTextView);
        passwordDiverseTextView = findViewById(R.id.passwordDiverseTextView);

        confermaButton.setOnClickListener(v -> {

            String password = passwordEditText.getText().toString();

            if(passwordEditText.getText().toString().equals("") || confermaPasswordEditText.getText().toString().equals("")){
                campiNonCompilatiTextView.setVisibility(View.VISIBLE);
                passwordDiverseTextView.setVisibility(View.INVISIBLE);
            }else if(passwordEditText.getText().toString().equals(confermaPasswordEditText.getText().toString())){

                if(addettoSala != null){

                    addettoSala.setPassword(password);
                    addettoSala.setPrimoAccesso(false);
                    addettoSalaAPI.save(addettoSala)
                            .enqueue(new Callback<AddettoSala>() {
                                @Override
                                public void onResponse(Call<AddettoSala> call, Response<AddettoSala> response) {
                                    if(response.body() != null){

                                        Bundle bundle = new Bundle();
                                        bundle.putString("utente", "addetto alla sala");
                                        FirebaseAnalytics.getInstance(ReimpostaPasswordActivity.this).logEvent("password_cambiata", bundle);

                                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                                        Toast.makeText(ReimpostaPasswordActivity.this, "Password cambiata con successo", Toast.LENGTH_SHORT).show();
                                        campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                                        passwordDiverseTextView.setVisibility(View.INVISIBLE);
                                        clearAll();
                                        Intent intent = new Intent(ReimpostaPasswordActivity.this, LoginActivity.class);
                                        ReimpostaPasswordActivity.this.startActivity(intent);
                                    }else{
                                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                    }
                                }

                                @Override
                                public void onFailure(Call<AddettoSala> call, Throwable t) {
                                    Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                                }
                            });

                }else if(supervisore != null){
                    supervisore.setPassword(password);
                    supervisore.setPrimoAccesso(false);
                    supervisoreAPI.salvataggioSupervisore(supervisore)
                            .enqueue(new Callback<Supervisore>() {
                                @Override
                                public void onResponse(Call<Supervisore> call, Response<Supervisore> response) {
                                    if(response.body() != null){

                                        Bundle bundle = new Bundle();
                                        bundle.putString("utente", "supervisore");
                                        FirebaseAnalytics.getInstance(ReimpostaPasswordActivity.this).logEvent("password_cambiata", bundle);

                                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                                        Toast.makeText(ReimpostaPasswordActivity.this, "Password cambiata con successo", Toast.LENGTH_SHORT).show();
                                        campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                                        passwordDiverseTextView.setVisibility(View.INVISIBLE);
                                        clearAll();
                                        Intent intent = new Intent(ReimpostaPasswordActivity.this, LoginActivity.class);
                                        ReimpostaPasswordActivity.this.startActivity(intent);
                                    }else{
                                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                    }
                                }

                                @Override
                                public void onFailure(Call<Supervisore> call, Throwable t) {
                                    Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                                }
                            });
                }
            }else{
                campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                passwordDiverseTextView.setVisibility(View.VISIBLE);
            }
        });

    }
}
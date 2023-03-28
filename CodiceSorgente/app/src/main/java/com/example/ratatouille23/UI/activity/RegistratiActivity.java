package com.example.ratatouille23.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.AmministratoreAPI;
import com.example.ratatouille23.retrofit.API.SupervisoreAPI;
import com.example.ratatouille23.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistratiActivity extends AppCompatActivity {

    private EditText emailEditText, nomeUtenteEditText, passwordEditText, confermaPasswordEditText;
    private TextView campiNonCompilatiTextView;
    private Button registratiButton;
    private SupervisoreAPI supervisoreAPI;
    private AmministratoreAPI amministratoreAPI;
    private Supervisore supervisore = new Supervisore();
    private Amministratore admin = new Amministratore();

    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrati);

        Registrati();
    }

    void Registrati(){

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        nomeUtenteEditText = (EditText) findViewById(R.id.nomeUtenteEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confermaPasswordEditText = (EditText) findViewById(R.id.confermaPasswordEditText);
        campiNonCompilatiTextView = (TextView) findViewById(R.id.campiNonCompilatiTextView);
        registratiButton = (Button) findViewById(R.id.registratiButton);

        retrofitService = new RetrofitService();
        supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
        amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);

        registratiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nomeUtenteEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")
                        || confermaPasswordEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("")){
                    campiNonCompilatiTextView.setVisibility(View.VISIBLE);
                }else if(passwordEditText.getText().toString().equals(confermaPasswordEditText.getText().toString())){
                    String nomeUtente = nomeUtenteEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    /*supervisore.setNomeUtenteSupervisore(nomeUtente);
                    supervisore.setEmailSupervisore(email);
                    supervisore.setPasswordSupervisore(password);
                    admin.setNomeAttivita("Pizzeria");
                    admin.setIndirizzoAttivita("Via Roma");
                    admin.setNomeUtenteAmministratore(nomeUtente);
                    admin.setEmailAmministratore(email);
                    admin.setPasswordAmministratore(password);*/
                    //salvaSupervisore(supervisore);
                    salvaAdmin(admin);
                    campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(RegistratiActivity.this, "Le password non corrispondono", Toast.LENGTH_SHORT).show();
                    campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    private void salvaAdmin(Amministratore admin) {
        amministratoreAPI.salvataggioAdmin(admin)
                .enqueue(new Callback<Amministratore>() {
                    @Override
                    public void onResponse(Call<Amministratore> call, Response<Amministratore> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body().toString());
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<Amministratore> call, Throwable t) {

                    }
                });
    }

    private void salvaSupervisore(Supervisore supervisore) {

        supervisoreAPI.salvataggioSupervisore(supervisore)
                .enqueue(new Callback<Supervisore>() {
                    @Override
                    public void onResponse(Call<Supervisore> call, Response<Supervisore> response) {
                        if(response.body() != null){
                            Toast.makeText(RegistratiActivity.this, "Registrazione effettuata con successo " + response.body(), Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<Supervisore> call, Throwable t) {

                    }
                });

    }
}
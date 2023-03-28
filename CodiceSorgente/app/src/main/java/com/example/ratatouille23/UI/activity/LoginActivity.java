package com.example.ratatouille23.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratatouille23.Controller.Controller;
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

    private Controller controller;
    private EditText nomeUtenteEditText, passwordEditText;
    private TextView campiErratiTextView;
    private Button accediButton;
    /*private AmministratoreAPI amministratoreAPI;
    private AddettoSalaAPI addettoSalaAPI;
    private SupervisoreAPI supervisoreAPI;*/
    private RetrofitService retrofitService;
    private static Amministratore admin;
    private static AddettoSala addettoSala;
    private static Supervisore supervisore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        controller = new Controller();
        retrofitService = new RetrofitService();
        /*amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);
        supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
        addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);*/

        Login();
    }

    void Login(){

        nomeUtenteEditText = findViewById(R.id.nomeUtenteEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        campiErratiTextView = findViewById(R.id.campiErratiTextView);
        accediButton = findViewById(R.id.accediButton);
        accediButton.setOnClickListener(v -> {

            String username = nomeUtenteEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            controller.checkAdmin(this, username, password);

        });

    }

    public void setCampiErratiTextViewVisibility(int visibility) {
        campiErratiTextView.setVisibility(visibility);
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

    public static void setAdmin(Amministratore nuovoAdmin) {
        admin = nuovoAdmin;
    }

    public static void setSupervisore(Supervisore nuovoSupervisore) {
        supervisore = nuovoSupervisore;
    }

    public static void setAddettoSala(AddettoSala nuovoAddettoSala) {
        addettoSala = nuovoAddettoSala;
    }

    public static void clearAll(){
        admin = null;
        addettoSala = null;
        supervisore = null;
    }
}
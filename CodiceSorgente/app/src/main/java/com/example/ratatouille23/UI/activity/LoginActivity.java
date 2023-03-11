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
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.retrofit.API.AmministratoreAPI;
import com.example.ratatouille23.retrofit.API.AttivitaAPI;
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
    private RetrofitService retrofitService;
    private Amministratore admin = new Amministratore();
    private List<Amministratore> adminList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofitService = new RetrofitService();
        amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);

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


            if(nomeUtenteEditText.getText().toString().equals("admin") && passwordEditText.getText().toString().equals("admin")){
                Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                LoginActivity.this.startActivity(intent);
                campiErratiTextView.setVisibility(View.INVISIBLE);
            }

            else if(nomeUtenteEditText.getText().toString().equals("supervisore") && passwordEditText.getText().toString().equals("supervisore")){
                campiErratiTextView.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(LoginActivity.this, HomeSupervisoreActivity.class);
                LoginActivity.this.startActivity(intent);
            }

            else if(nomeUtenteEditText.getText().toString().equals("addettosala") && passwordEditText.getText().toString().equals("addettosala")){
                campiErratiTextView.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(LoginActivity.this, HomeAddettoSalaActivity.class);
                LoginActivity.this.startActivity(intent);
            }

            else if(nomeUtenteEditText.getText().toString().equals("user") && passwordEditText.getText().toString().equals("user")){
                campiErratiTextView.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(LoginActivity.this, ReimpostaPasswordActivity.class);
                LoginActivity.this.startActivity(intent);
            }
            else{
                campiErratiTextView.setVisibility(View.VISIBLE);
            }
        });

    }
}
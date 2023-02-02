package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReimpostaPasswordActivity extends AppCompatActivity {

    EditText passwordEditText, confermaPasswordEditText;
    TextView campiNonCompilatiTextView, passwordDiverseTextView;
    Button confermaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimposta_password);

        ResetPassword();
    }

    void ResetPassword(){

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confermaPasswordEditText = (EditText) findViewById(R.id.confermaPasswordEditText);
        confermaButton = (Button) findViewById(R.id.confermaButton);
        campiNonCompilatiTextView = (TextView) findViewById(R.id.campiNonCompilatiTextView);
        passwordDiverseTextView = (TextView) findViewById(R.id.passwordDiverseTextView);

        confermaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordEditText.getText().toString().equals("") || confermaPasswordEditText.getText().toString().equals("")){
                    campiNonCompilatiTextView.setVisibility(View.VISIBLE);
                    passwordDiverseTextView.setVisibility(View.INVISIBLE);
                }else if(passwordEditText.getText().toString().equals(confermaPasswordEditText.getText().toString())){
                    Toast.makeText(ReimpostaPasswordActivity.this, "Password cambiata con successo", Toast.LENGTH_SHORT).show();
                    campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                    passwordDiverseTextView.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(ReimpostaPasswordActivity.this, MainActivity.class);
                    ReimpostaPasswordActivity.this.startActivity(intent);
                }else{
                    campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                    passwordDiverseTextView.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}
package com.example.ratatouille23;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistratiActivity extends AppCompatActivity {

    EditText emailEditText, nomeUtenteEditText, passwordEditText, confermaPasswordEditText;
    TextView campiNonCompilatiTextView;
    Button registratiButton;

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

        registratiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nomeUtenteEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")
                        || confermaPasswordEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("")){
                    campiNonCompilatiTextView.setVisibility(View.VISIBLE);
                }else if(passwordEditText.getText().toString().equals(confermaPasswordEditText.getText().toString())){
                    Toast.makeText(RegistratiActivity.this, "Registrazione effettuata con successo", Toast.LENGTH_SHORT).show();
                    campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(RegistratiActivity.this, "Le password non corrispondono", Toast.LENGTH_SHORT).show();
                    campiNonCompilatiTextView.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}
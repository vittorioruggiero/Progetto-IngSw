package com.example.loginactivity;

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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nomeUtenteEditText, passwordEditText;
    TextView campiErratiTextView, registratiTextView;
    Button accediButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registratiTextView = (TextView) findViewById(R.id.registratiTextView);
        String registratiText = "Hai un ristorante? Registrati";

        SpannableString spannableString = new SpannableString(registratiText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistratiActivity.class);
                MainActivity.this.startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan, 19,29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registratiTextView.setText(spannableString);
        registratiTextView.setMovementMethod(LinkMovementMethod.getInstance());

        Login();
    }

    
    void Login(){
        
        nomeUtenteEditText = (EditText) findViewById(R.id.nomeUtenteEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        campiErratiTextView = (TextView) findViewById(R.id.campiErratiTextView);
        accediButton = (Button) findViewById(R.id.accediButton);
        accediButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(nomeUtenteEditText.getText().toString().equals("admin") && passwordEditText.getText().toString().equals("admin")){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    MainActivity.this.startActivity(intent);
                }else{
                    campiErratiTextView.setVisibility(View.VISIBLE);
                }
            }
        });
        
    }
}
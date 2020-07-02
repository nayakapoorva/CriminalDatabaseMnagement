package com.example.criminal_dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomePage extends AppCompatActivity {
    Button registerC,detailsC,logoutc;
    FirebaseAuth aAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        registerC = findViewById(R.id.registerC);
        detailsC = findViewById(R.id.deatilsC);
        logoutc = findViewById(R.id.logoutc);

        aAuth = FirebaseAuth.getInstance();

        registerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePage.this,CrimiRegistration.class));
            }
        });

        detailsC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePage.this,CriminalDetails.class));
            }
        });

        logoutc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePage.this,MainActivity.class));
            }
        });


    }
}

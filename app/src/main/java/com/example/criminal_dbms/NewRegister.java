package com.example.criminal_dbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewRegister extends AppCompatActivity {
    EditText fullname,email,password,phone;
    Button register,nregister;
    ProgressDialog progress;
    FirebaseAuth aAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        register = findViewById(R.id.register);
        nregister = findViewById(R.id.nregister);

        progress = new ProgressDialog(this);
        aAuth = FirebaseAuth.getInstance();

        if (aAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nemail = email.getText().toString().trim();
                String npassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(nemail)) {
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(npassword)) {
                    password.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    password.setError("password should be >=6");
                    return;
                }

                progress.setMessage("Registering");
                progress.show();


                aAuth.createUserWithEmailAndPassword(nemail, npassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(NewRegister.this, "registered successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), WelcomePage.class));
                            finish();
                        }
                        else {
                            Toast.makeText(NewRegister.this, "ERROR", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        nregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc  = new Intent(NewRegister.this,MainActivity.class);
                startActivity(abc);
                finish();

            }
        });


    }

}

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button sign,neww;
    private ProgressDialog progress;
    FirebaseAuth aAUTH;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sign = findViewById(R.id.sign);
        neww = findViewById(R.id.neww);

        aAUTH = FirebaseAuth.getInstance();
        progress = new ProgressDialog(this);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nemail = email.getText().toString().trim();
                String npassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(nemail)) {
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(npassword)) {
                    password.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    password.setError("password should be >= 6");
                }
                progress.setMessage("signing in");
                progress.show();

                aAUTH.signInWithEmailAndPassword(nemail, npassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, WelcomePage.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });

        neww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewRegister.class));
                finish();
            }
        });
    }


}

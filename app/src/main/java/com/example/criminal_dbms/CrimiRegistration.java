package com.example.criminal_dbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class CrimiRegistration extends AppCompatActivity {
    EditText cno,name,age,address,haw,crimedate,crimetype,casedescription;
    Button cregister;
    FirebaseAuth aAuth;
    FirebaseFirestore db;
    DatabaseReference ref;
    FirebaseDatabase database;
    int maxid = 0;
    Register register;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimi_registration);

        cno = findViewById(R.id.cno);
        name = findViewById(R.id.name);
        age = findViewById(R.id.cage);
        address = findViewById(R.id.address);
        haw = findViewById(R.id.haw);
        crimedate = findViewById(R.id.crimedate);
        crimetype = findViewById(R.id.crimetype);
        casedescription = findViewById(R.id.casediscription);
        cregister = findViewById(R.id.cregister);

        aAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progress = new ProgressDialog(this);
        register = new Register();
        ref = database.getInstance().getReference().child("Criminal Database");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxid = (int) dataSnapshot.getChildrenCount();
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register.setCriminalno(cno.getText().toString());
                register.setCriminalname(name.getText().toString());
                register.setCriminalage(age.getText().toString());
                register.setCriminaladdress(address.getText().toString());
                register.setCriminalhaw(haw.getText().toString());
                register.setCrimedates(crimedate.getText().toString());
                register.setCrimetypee(crimetype.getText().toString());
                register.setCasedescritions(casedescription.getText().toString());

                ref.child(String.valueOf(maxid+1)).setValue(register);

                Toast.makeText(CrimiRegistration.this,"Data added successfully",Toast.LENGTH_LONG).show();

                startActivity(new Intent(CrimiRegistration.this,WelcomePage.class));

            }
        });



    }
}

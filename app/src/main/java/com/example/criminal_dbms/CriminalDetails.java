package com.example.criminal_dbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CriminalDetails extends AppCompatActivity {
    EditText name;
    RecyclerView recyclerView;
    DatabaseReference ref;
    FirebaseUser firebaseUser;
    FirebaseAuth aAuth;
    Button logoutc;
    ArrayList<String> criminalno;
    ArrayList<String> criminalname;
    ArrayList<String > criminalhaw;
    ArrayList<String > criminalage;
    ArrayList<String> criminaladdress;
    ArrayList<String> crimetypee;
    ArrayList<String> crimedates;
    ArrayList<String> casesecriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_details);
        name = findViewById(R.id.name);
        logoutc = findViewById(R.id.logoutc);
        recyclerView = findViewById(R.id.recyclerview);

        aAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        firebaseUser = aAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        criminalno = new ArrayList<>();
        criminalname = new ArrayList<>();
        criminalhaw = new ArrayList<>();
        criminalage = new ArrayList<>();
        criminaladdress = new ArrayList<>();
        crimetypee = new ArrayList<>();
        crimedates = new ArrayList<>();
        casesecriptions = new ArrayList<>();

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }
                else {
                    criminalno.clear();
                    criminalname.clear();
                    criminalhaw.clear();
                    criminalage.clear();
                    criminaladdress.clear();
                    crimetypee.clear();
                    crimedates.clear();
                    casesecriptions.clear();
                    recyclerView.removeAllViews();
                }



            }
        });
    }

    private <DataStatus> void setAdapter(final String string) {

        ref.child("Criminal Database").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                criminalno.clear();
                criminalname.clear();
                criminalhaw.clear();
                criminalage.clear();
                criminaladdress.clear();
                crimetypee.clear();
                crimedates.clear();
                casesecriptions.clear();
                recyclerView.removeAllViews();


                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    int counter = 0;

                    String  uid = snapshot.getKey();
                    String number = snapshot.child("criminalno").getValue(String.class);
                    String cname = snapshot.child("criminalname").getValue(String.class);
                    String chaw = snapshot.child("criminalhaw").getValue(String.class);
                    String age = snapshot.child("criminalage").getValue(String.class);
                    String address = snapshot.child("criminaladdress").getValue(String.class);
                    String crimetype = snapshot.child("crimetypee").getValue(String.class);
                    String crimedate = snapshot.child("crimedates").getValue(String.class);
                    String casedescription = snapshot.child("casedescritions").getValue(String.class);

                    if (number.toLowerCase().contains(string.toLowerCase())){
                        criminalno.add(number);
                        criminalname.add(cname);
                        criminalhaw.add(chaw);
                        criminalage.add(age);
                        criminaladdress.add(address);
                        crimetypee.add(crimetype);
                        crimedates.add(crimedate);
                        casesecriptions.add(casedescription);
                        counter++;
                    }
                    else if (cname.toLowerCase().contains(string.toLowerCase())){
                        criminalno.add(number);
                        criminalname.add(cname);
                        criminalhaw.add(chaw);
                        criminalage.add(age);
                        criminaladdress.add(address);
                        crimetypee.add(crimetype);
                        crimedates.add(crimedate);
                        casesecriptions.add(casedescription);
                        counter++;
                    }
                    else if (crimetype.toLowerCase().contains(string.toLowerCase())){
                        criminalno.add(number);
                        criminalname.add(cname);
                        criminalhaw.add(chaw);
                        criminalage.add(age);
                        criminaladdress.add(address);
                        crimetypee.add(crimetype);
                        crimedates.add(crimedate);
                        casesecriptions.add(casedescription);
                        counter++;
                    }
                    else if (crimedate.toLowerCase().contains(string.toLowerCase())){
                        criminalno.add(number);
                        criminalname.add(cname);
                        criminalhaw.add(chaw);
                        criminalage.add(age);
                        criminaladdress.add(address);
                        crimetypee.add(crimetype);
                        crimedates.add(crimedate);
                        casesecriptions.add(casedescription);
                        counter++;

                    }
                    else if (casedescription.toLowerCase().contains(string.toLowerCase())){
                        criminalno.add(number);
                        criminalname.add(cname);
                        criminalhaw.add(chaw);
                        criminalage.add(age);
                        criminaladdress.add(address);
                        crimetypee.add(crimetype);
                        crimedates.add(crimedate);
                        casesecriptions.add(casedescription);
                        counter++;
                    }

                    if (counter == 50 )
                    {
                        break;
                    }

                    SearchAdapter searchAdapter = new SearchAdapter(CriminalDetails.this,criminalno,criminalname,criminalage,criminaladdress,criminalhaw,crimetypee,crimedates,casesecriptions);
                    recyclerView.setAdapter(searchAdapter);





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });




    }

    public void back(View view) {

        startActivity(new Intent(CriminalDetails.this,MainActivity.class));
    }
}

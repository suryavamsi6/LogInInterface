package com.example.suryavamsi.javaproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    TextView nm,ph,t3,t4;
    FirebaseDatabase database;
    DatabaseReference ref2;
    String is ,name,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nm = (TextView)findViewById(R.id.name);
        ph = (TextView)findViewById(R.id.phone);
        t3 = (TextView)findViewById(R.id.textView4);
        t4 = (TextView)findViewById(R.id.textView6);
        is = getIntent().getStringExtra("is");
        database = FirebaseDatabase.getInstance();
        ref2 = database.getReference().child("member").child(is);
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                phone = dataSnapshot.child("phone").getValue().toString();
                t3.setText(name);
                t4.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
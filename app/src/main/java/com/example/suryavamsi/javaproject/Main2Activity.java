package com.example.suryavamsi.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    Button bt4;
    DatabaseReference ref2;
    String username ,name,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nm = findViewById(R.id.name);
        ph = findViewById(R.id.phone);
        t3 = findViewById(R.id.textView4);
        t4 = findViewById(R.id.textView6);
        bt4 = findViewById(R.id.button4);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("cur");
        //is = Integer.toString(cur);
        database = FirebaseDatabase.getInstance();
        ref2 = database.getReference().child("member").child(username);
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
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });



    }
    public void openActivity1()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}

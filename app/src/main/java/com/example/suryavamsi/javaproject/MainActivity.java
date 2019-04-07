package com.example.suryavamsi.javaproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView t1 , t2,t3;
    Button bt,bt2;
    String pwd;
    EditText edt1,edt2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    private Vibrator myVib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView11);
        edt1 = findViewById(R.id.editText);
        edt2 = findViewById(R.id.editText2);
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin(edt1.getText().toString(), edt2.getText().toString());
                myVib.vibrate(50);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myVib.vibrate(50);
                openActivity3();

            }
        });
    }
    private void signin(final String username, final String password) {
            ref = database.getReference().child("member");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(username).exists())
                    {
                        pwd = dataSnapshot.child(username).child("password").getValue().toString();
                        if (pwd.equals(password)) {
                                openActivity2(username);
                        }

                        else
                        {
                            t3.setTextColor(Color.parseColor("#FF0000"));
                            t3.setText("Invalid Password!!");

                        }

                    }
                    else {
                        t3.setTextColor(Color.parseColor("#FF0000"));
                        t3.setText("User not registered. Create Account first!");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }


    public void openActivity3(){
        Intent intent = new Intent(this,Main3Activity.class);
        startActivity(intent);}
    public void openActivity2(final String username){
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("cur",username);
        startActivity(intent);}

}



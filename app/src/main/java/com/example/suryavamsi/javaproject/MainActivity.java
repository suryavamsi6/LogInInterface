package com.example.suryavamsi.javaproject;

import android.content.Intent;
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
    TextView t1 , t2;
    Button bt,bt2;
    int i;
    String is,u,p,un,pwd;
    int NotFound,Found;
    EditText edt1,edt2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView)findViewById(R.id.textView);
        t2 = (TextView)findViewById(R.id.textView2);
        edt1 = (EditText)findViewById(R.id.editText);
        edt2 = (EditText)findViewById(R.id.editText2);
        bt = (Button)findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);
        NotFound = 0;
        Found = 0;
        i = 1;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i=1;
                u = edt1.getText().toString();
                p = edt2.getText().toString();
                while(i<=4)
               {

                    is = Integer.toString(i);
                    ref = database.getReference().child("member").child(is);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChild("user"))
                        {
                          //Toast.makeText(getApplicationContext(),"Invalid Username/Password!!",Toast.LENGTH_LONG).show();
                            NotFound = 1;
                        }
                        else {
                            un = dataSnapshot.child("user").getValue().toString();
                            pwd = dataSnapshot.child("password").getValue().toString();
                            if (un.equals(u))
                                if (pwd.equals(p)) {
                                   Found = 1;
                                   //openActivity2();
                                    //Toast.makeText(getApplicationContext(), "Valid Username/Password!!", Toast.LENGTH_LONG).show();
                                    //i = 5;

                                    // NotFound = true;
                                }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                    i++;


               }
                if(NotFound == 1)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Username/Password!!",Toast.LENGTH_SHORT).show();
                    // i=1;
                }
                if(Found == 1){
                    openActivity2();
                }
                Toast.makeText(getApplicationContext(),"outside",Toast.LENGTH_SHORT).show();

            }
        }
        );
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        }

    public void openActivity2(){
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("is", is);
        startActivity(intent);}
}



package com.example.suryavamsi.javaproject;

import android.content.Context;
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
    Button button;
    int i;
    String is,u,p;
    EditText edt1,edt2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref ,ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView)findViewById(R.id.textView);
        t2 = (TextView)findViewById(R.id.textView2);
        edt1 = (EditText)findViewById(R.id.editText);
        edt2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button);
        i = 0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u = edt1.getText().toString();
                p = edt2.getText().toString();

              //  t1.setText(u);
               //if(ref.is()==null)
               //     i=1;
                is = Integer.toString(++i);
                ref = database.getReference().child("member").child(is);

                //ref2 = database.getReference().child("member").child("1").child("password");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChild("user"))
                        {
                             i=0;
                             Toast.makeText(getApplicationContext(),"Setting i = 0!! Click Update Again!!",Toast.LENGTH_LONG).show();
                        }
                        else {
                            String un = dataSnapshot.child("user").getValue().toString();
                            String pwd = dataSnapshot.child("password").getValue().toString();
                            if(un.equals(u))
                               if(pwd.equals(p))
                                 Toast.makeText(getApplicationContext(),"Log in Successfull!",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }
        );


    }
}



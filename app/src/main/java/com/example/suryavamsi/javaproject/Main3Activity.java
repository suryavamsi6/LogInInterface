package com.example.suryavamsi.javaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    Button bt3;
    int max;
    String is;
    EditText et1,et2,et3,et4,et5;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv1 = findViewById(R.id.textView3);
        tv2 = findViewById(R.id.textView5);
        tv3 = findViewById(R.id.textView7);
        tv4 = findViewById(R.id.textView8);
        tv5 = findViewById(R.id.textView9);
        tv6 = findViewById(R.id.textView10);
        bt3 = findViewById(R.id.button3);
        max = getIntent().getIntExtra("max",max);
        is = Integer.toString(max);
        et1 = findViewById(R.id.editText3);
        et2 = findViewById(R.id.editText4);
        et3 = findViewById(R.id.editText5);
        et4 = findViewById(R.id.editText9);
        et5 = findViewById(R.id.editText10);
        ref3 = database.getReference().child("member").child(is);
        bt3.setOnClickListener(new View.OnClickListener() {
            String un,pwd,cpwd,name,phone;
            @Override
            public void onClick(View view) {
                un = et1.getText().toString();
                pwd = et2.getText().toString();
                cpwd = et3.getText().toString();
                name = et4.getText().toString();
                phone = et5.getText().toString();
                if(pwd.equals(cpwd))
                {
                    ref3.child("user").setValue(un);
                    ref3.child("password").setValue(pwd);
                    ref3.child("name").setValue(name);
                    ref3.child("phone").setValue(phone);
                    Toast.makeText(getApplicationContext(),"Account Created!",Toast.LENGTH_SHORT).show();
                    openActivity1();
                }
                else
                {
                    tv6.setText("Passwords don't match. Re-Check them.");
                }


            }
        });

    }
    public void openActivity1()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

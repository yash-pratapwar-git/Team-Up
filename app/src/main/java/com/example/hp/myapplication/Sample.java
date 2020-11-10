package com.example.hp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Sample extends AppCompatActivity {


    EditText phn;
    Button btn;

    FirebaseFirestore mFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        phn=findViewById(R.id.textView5);
        phn.setText("hello there");
        btn=findViewById(R.id.button6);
        mFireStore=FirebaseFirestore.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> userMap= new HashMap<>();

                userMap.put("Phn no",phn.getText().toString());

                mFireStore.collection("PhnNo").add(userMap);
            }
        });



    }
}

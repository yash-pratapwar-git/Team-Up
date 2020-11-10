package com.example.hp.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class profile extends AppCompatActivity {

    private FirebaseAuth firebaseauth;
    //private EditText txt;


    String uid;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Button btn;
    TextView usrnm;
    TextView cnt;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uid=user.getUid();

        btn=findViewById(R.id.button3);
        usrnm=findViewById(R.id.textView2);
        cnt=findViewById(R.id.textView3);
        db=FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this,profile_edit.class));
            }
        });


        if (user.getDisplayName() != null)
        {
            usrnm.setText(user.getDisplayName());
        }

        if (user.getPhoneNumber() != null)
        {
            cnt.setText(user.getPhoneNumber());
        }


        CollectionReference numbers = db.collection("PhnNo");

        Query no= numbers.whereEqualTo("uid",uid);

        no.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        //cnt.setText((CharSequence) document.get("Phn No"));
                        cnt.setText(document.getString("Phn no"));
                    }
                }
            }
        });



        Toast.makeText(this,uid,Toast.LENGTH_SHORT).show();




        //System.out.println("hello"+user.getEmail());
    }


}

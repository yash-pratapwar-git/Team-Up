package com.example.hp.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;



public class profile_edit extends AppCompatActivity {


    FirebaseAuth fauth;
    FirebaseUser usr;
    EditText name;
    EditText phn;
    Button btn;
    FirebaseFirestore mFireStore;
    FirebaseFirestore db;
    //String uid;



    private static final String TAG = "profile_edit";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        fauth=FirebaseAuth.getInstance();
        usr=fauth.getCurrentUser();
        mFireStore=FirebaseFirestore.getInstance();
        db=FirebaseFirestore.getInstance();




        name=findViewById(R.id.editText);
        phn=findViewById(R.id.editText2);
        btn=findViewById(R.id.button4);
        //uid=usr.getUid();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid=usr.getUid();

                String nm=name.getText().toString();
                String cnt=phn.getText().toString();

                if (TextUtils.isEmpty(nm))
                {
                    Toast.makeText(profile_edit.this,"Display name can't be empty",Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(cnt))
                {
                    Toast.makeText(profile_edit.this,"Contact no can't be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cnt.length() <= 9)
                {
                    Toast.makeText(profile_edit.this,"invalid contact number",Toast.LENGTH_SHORT).show();
                    return;
                }


                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(nm)
                            .build();

                    usr.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User profile updated.");
                                    }
                                }
                            });



                    //////////////////////

             /*   final int id;
                final CollectionReference numbers = db.collection("PhnNo");

                Query no= numbers.whereEqualTo("uid",uid);

                no.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                //cnt.setText((CharSequence) document.get("Phn No"));
                                //cnt.setText(document.getString("Phn no"));





                            }
                        }
                    }
                });*/



                /////////////////////////



                Map<String, String> userMap= new HashMap<>();

                userMap.put("Phn no",phn.getText().toString());
                userMap.put("uid",usr.getUid());

                mFireStore.collection("PhnNo").add(userMap);


                Toast.makeText(profile_edit.this,"Successfully Updated",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(profile_edit.this,create_join.class));
            }
        });




    }
}

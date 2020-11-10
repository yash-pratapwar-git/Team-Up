package com.example.hp.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {


    private EditText email;
    private EditText password;
    private Button loginbutton;
    private Button registerlinkbutton;
    private FirebaseAuth firebaseauth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        firebaseauth=FirebaseAuth.getInstance();
       /* if (firebaseauth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),profile.class));
        }*/

       user =FirebaseAuth.getInstance().getCurrentUser();

       if (user != null)
       {
           startActivity(new Intent(getApplicationContext(),create_join.class));
           this.finish();
       }

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginbutton=findViewById(R.id.loginbutton);
        registerlinkbutton=findViewById(R.id.registerlinkbutton);

        loginbutton.setOnClickListener(this);
        registerlinkbutton.setOnClickListener(this);
    }

    private void userlogin()
    {
        String eml=email.getText().toString().trim();
        String pss=password.getText().toString().trim();

        if(TextUtils.isEmpty(eml))
        {
            Toast.makeText(this,"Enter email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pss))
        {
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        // startActivity(new Intent(getApplicationContext(),profile.class));
        firebaseauth.signInWithEmailAndPassword(eml,pss)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            //finish();
                            startActivity(new Intent(getApplicationContext(),create_join.class));

                        }
                        else
                        {
                            Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if (v==loginbutton)
        {
            userlogin();
        }
        if (v==registerlinkbutton)
        {
            finish();
            startActivity(new Intent(this,HomeActivity.class));
        }
    }
}

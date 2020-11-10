package com.example.hp.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private Button loginbutton;
    private Button registerlinkbutton;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseauth= FirebaseAuth.getInstance();

        /*if (firebaseauth.getCurrentUser() != null)
        {
            //finish();
            startActivity(new Intent(getApplicationContext(),profile.class));
        }*/

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginbutton=findViewById(R.id.loginbutton);
        registerlinkbutton=findViewById(R.id.registerlinkbutton);

        loginbutton.setOnClickListener(this);
        registerlinkbutton.setOnClickListener(this);

    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void registeruser()
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

        if (isValidEmail(eml)) {

            if (pss.length() >= 6) {


                firebaseauth.createUserWithEmailAndPassword(eml, pss);
                Toast.makeText(HomeActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, Login.class));

            }
            else {

                Toast.makeText(HomeActivity.this,"password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                return;
            }

        }

        else
        {
            Toast.makeText(HomeActivity.this,"invalid email",Toast.LENGTH_SHORT).show();
            return;
        }



        /*.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(HomeActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(HomeActivity.this,Login.class));


                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/


    }

    @Override
    public void onClick(View v) {

        if(v== loginbutton)
        {
            registeruser();
        }
        if(v== registerlinkbutton)
        {

            finish();
            startActivity(new Intent(HomeActivity.this,Login.class));
        }

    }
}

package com.example.hp.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

public class Create extends AppCompatActivity implements LocationListener {


    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 11;
    private EditText title;
    private EditText sport;
    private EditText venue;
    private EditText time;
    private EditText capacity;
    private Button btn;
    FirebaseFirestore mFireStore;
    FirebaseAuth mAuth;
    FirebaseUser usr;

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    protected Context context;
    double lat;
    double lon;
    Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(Create.this,"permission issues",Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        title=findViewById(R.id.editText3);
        sport=findViewById(R.id.editText4);
        venue=findViewById(R.id.editText5);
        time=findViewById(R.id.editText7);
        capacity=findViewById(R.id.editText9);
        btn=findViewById(R.id.button9);

        mFireStore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        usr=mAuth.getCurrentUser();

        /*lat=location.getLatitude();
        lon=location.getLongitude();*/
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);

        //final




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //GeoPoint geoPoint= new GeoPoint();

                Map<String, String> userMap= new HashMap<>();

                userMap.put("Title",title.getText().toString());
                userMap.put("Sport",sport.getText().toString());
                userMap.put("Venue",venue.getText().toString());
                userMap.put("time",time.getText().toString());
                userMap.put("Capacity",capacity.getText().toString());
                userMap.put("Latitude", Double.toString(lat));
                userMap.put("Longitude",Double.toString(lon));

                //userMap.put("Location",geoPoint.getLongitude());

                mFireStore.collection("Events").add(userMap)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Create.this,"posted successfully",Toast.LENGTH_SHORT).show();
                           // startActivity(new Intent(Create.this,create_join.class));


                        }

                    });



                startActivity(new Intent(Create.this,create_join.class));




            }
        });



    }

    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lon=location.getLongitude();

        //Toast.makeText(this,Double.toString(lat),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

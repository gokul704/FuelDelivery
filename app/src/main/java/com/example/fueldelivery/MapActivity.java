package com.example.fueldelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.sql.Struct;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference lat = database.getReference("datails");
    private FirebaseAuth auth;

    public TextView textView1,textView2,textView3,textView4,textView5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        auth = FirebaseAuth.getInstance();
        textView1=findViewById(R.id.tv1);
        textView2=findViewById(R.id.tv2);
        textView3=findViewById(R.id.tv3);
        textView4=findViewById(R.id.tv4);
        textView5=findViewById(R.id.tv5);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // reuqest for permission
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        } else {
            // already permission granted
            mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location=task.getResult();
                    if(location!=null){
                        Geocoder  geocoder=new Geocoder(MapActivity.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            textView1.setText((int) addresses.get(0).getLatitude());
                            textView2.setText(Html.fromHtml("<b>longitude</b>"+addresses.get(0).getLongitude()));
                            textView3.setText(Html.fromHtml("<b>Country</b>"+addresses.get(0).getCountryName()));
                            textView4.setText(Html.fromHtml("<b>Locality</b>"+addresses.get(0).getLocality()));
                            textView5.setText(Html.fromHtml("<b>Address</b>"+addresses.get(0).getAddressLine(0)));





                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                }
            });        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
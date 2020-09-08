package com.example.fueldelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {
    public Button viewfuels,track,lout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_home_screen);
        viewfuels=findViewById(R.id.button3);
        track=findViewById(R.id.button4);
        lout=findViewById(R.id.button5);
        viewfuels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotolist=new Intent(HomeScreen.this,ListView.class);
                startActivity(gotolist);
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapintnet=new Intent(HomeScreen.this,MapActivity.class);
                startActivity(mapintnet);
            }
        });
        lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //signout firebase
                Intent setupIntent = new Intent(getBaseContext(),MainActivity.class);
                Toast.makeText(getBaseContext(), "Logged Out", Toast.LENGTH_LONG).show(); //if u want to show some text
                setupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(setupIntent);
                finish();
            }
        });
    }
}
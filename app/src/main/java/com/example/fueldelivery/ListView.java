package com.example.fueldelivery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        RecyclerView recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyMovieData[] myMovieData = new MyMovieData[]{
                new MyMovieData("Hp","82/L    900 m away from your location",R.drawable.hp),
                new MyMovieData("Indian oil","81/L 1.2km away from your location",R.drawable.indianoil),
                new MyMovieData("Essaer","82.75/L 1.6km away from your location",R.drawable.essar),
                new MyMovieData("Bharat petrolium","81.68/L 2 km away from your location",R.drawable.bhrathpetrolium),

        };

        MyMovieAdapter myMovieAdapter = new MyMovieAdapter(myMovieData,ListView.this);
        recyclerView.setAdapter(myMovieAdapter);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
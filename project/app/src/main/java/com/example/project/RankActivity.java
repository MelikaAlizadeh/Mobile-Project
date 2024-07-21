package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ranking);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        Intent in = new Intent(RankActivity.this, MainActivity.class);
                        startActivity(in);
                        finish();
                        break;
                    case R.id.profile:
                        Intent i = new Intent(RankActivity.this, ProfileActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.ranking:
                        break;
                    case R.id.training:
                        Intent tr = new Intent(RankActivity.this, GameActivity.class);
                        startActivity(tr);
                        finish();
                        break;
                }
                return true;
            }
        });
    }
}
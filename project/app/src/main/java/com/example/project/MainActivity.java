package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.recom1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MainActivity.this, GameActivity.class);
                //TODO: category
//                s.putExtra("table name", )
                startActivity(s);
                finish();
            }
        });

        findViewById(R.id.recom2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Sorry This quiz doesn't exists yet!", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.recom3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Sorry This quiz doesn't exists yet!", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.recom3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Sorry This quiz doesn't exists yet!", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.recom4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Sorry This quiz doesn't exists yet!", Toast.LENGTH_LONG).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        break;
                    case R.id.profile:
                        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.ranking:
                        Intent in = new Intent(MainActivity.this, RankActivity.class);
                        startActivity(in);
                        finish();
                        break;
                    case R.id.training:
                        Intent tr = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(tr);
                        finish();
                        break;
                }
                return true;
            }
        });
    }
}
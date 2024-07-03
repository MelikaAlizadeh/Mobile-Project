package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.recom1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MainActivity.this, GameActivity.class);
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


    }
}
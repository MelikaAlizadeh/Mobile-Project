package com.example.mobileproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.chip.Chip;

public class HomeActivity extends AppCompatActivity {

    Chip playChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playChip = findViewById(R.id.chip_play);
        playChip.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
    }
}
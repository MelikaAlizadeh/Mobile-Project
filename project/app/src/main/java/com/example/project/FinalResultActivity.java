package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

public class FinalResultActivity extends AppCompatActivity {
    int numberOfAllQs;
    int numberOfCorrects = 0;
    int numberOfWrongs = 0;
    TextView trueTextView;
    TextView wrongTextView;
    TextView allTextView;
    Chip playAgain;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalresult);

        numberOfAllQs = getIntent().getIntExtra("number of all qs", 0);
        numberOfCorrects = getIntent().getIntExtra("number of corrects", 0);
        numberOfWrongs = getIntent().getIntExtra("number of wrongs", 0);

        allTextView = findViewById(R.id.tv_ending_reason);
        playAgain = findViewById(R.id.chip_play_again);
        trueTextView = findViewById(R.id.tv_final_result_true);
        wrongTextView = findViewById(R.id.tv_final_result_false);

        String text = "You answered " + numberOfAllQs + ".";
        allTextView.setText(text);
        text = Integer.toString(numberOfCorrects);
        trueTextView.setText(text);
        text = Integer.toString(numberOfWrongs);
        wrongTextView.setText(text);

        playAgain.setOnClickListener(v -> {
            Intent intent = new Intent(FinalResultActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
package com.example.mobileproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

public class FinalResultActivity extends AppCompatActivity {
    int numberOfAllQs;
    int numberOfCorrects = 0;
    TextView resultTextView;
    TextView endingReasonTextView;
    Chip playAgain;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        numberOfAllQs = getIntent().getIntExtra("number of all qs", 0);
        numberOfCorrects = getIntent().getIntExtra("number of corrects", 0);

        resultTextView = findViewById(R.id.tv_final_result);
        playAgain = findViewById(R.id.chip_play_again);
        endingReasonTextView = findViewById(R.id.tv_ending_reason);

        text = getIntent().getStringExtra("end message");
        endingReasonTextView.setText(text);

        if (text.equals("Time is over!")) {
            text = "You answered " + numberOfAllQs + " questions.\n"
                    + numberOfCorrects + " of them were correct.";
            resultTextView.setText(text);
        } else {
            text = "\n" + numberOfCorrects + " of them were correct.";
            resultTextView.setText(text);
        }

        playAgain.setOnClickListener(v -> {
            Intent intent = new Intent(FinalResultActivity.this,
                    QuestionsActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
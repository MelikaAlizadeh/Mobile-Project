package com.example.mobileproj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class AnswerActivity extends AppCompatActivity {
    String result;
    TextView resultTV;
    TextView messageTV;
    Chip nextChip;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        resultTV = findViewById(R.id.tv_result);
        messageTV = findViewById(R.id.tv_message);
        nextChip = findViewById(R.id.chip_next);

        result = getIntent().getStringExtra("result");
        assert result != null;
        String tmp;
        if (result.equals("correct")) {
            tmp = "CORRECT!";
            resultTV.setText(tmp);
            tmp = "Good job!";
        } else {
            tmp = "WRONG!";
            resultTV.setText(tmp);
            tmp = "The correct answer was "
                    + getIntent().getStringExtra("correctAnswer");
        }
        messageTV.setText(tmp);
        counter = getIntent().getIntExtra("counter", 0);
        nextChip.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuestionsActivity.class);
            intent.putExtra("counter", counter + 1);
            startActivity(intent);
            finish();
        });
    }
}
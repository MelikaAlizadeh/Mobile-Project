package com.example.mobileproj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Chip[] chips = new Chip[5];
    int correctAnswer;
    int numberOfAllQuestions;
    int numberOfCorrectAnswers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tv = findViewById(R.id.q_tv);
        chips[1] = findViewById(R.id.op1);
        chips[2] = findViewById(R.id.op2);
        chips[3] = findViewById(R.id.op3);
        chips[4] = findViewById(R.id.op4);

        showNewQuestion(tv, chips[1], chips[2], chips[3], chips[4]);

        for (int i = 1; i < 5; i++) {
            int finalI = i;
            chips[i].setOnClickListener(v -> {
                numberOfAllQuestions++;
                Intent intent = new Intent(this, AnswerActivity.class);
                if (finalI == correctAnswer) {
                    numberOfCorrectAnswers++;
                    updateIntentCorrect(intent);
                } else {
                    updateIntentWrong(intent);
                }
                startActivity(intent);
                finish();
            });
        }

    }

    private void updateIntentCorrect(Intent intent) {
        intent.putExtra("result", "correct");
        intent.putExtra("correctAnswer", 0);
    }

    private void updateIntentWrong(Intent intent) {
        intent.putExtra("result", "wrong");
        intent.putExtra("correctAnswer", chips[correctAnswer].getText().toString());
    }

    private void showNewQuestion(TextView tv, Chip chip1, Chip chip2, Chip chip3, Chip chip4) {
        //TODO: read question from db


        Question question = new Question("This is a question", "option1",
                "option2", "option3", "option4", 1);
        String questionText = question.getText();
        String option1Text = question.getOption1();
        String option2Text = question.getOption2();
        String option3Text = question.getOption3();
        String option4Text = question.getOption4();
        correctAnswer = question.getCorrectAnswer();

        tv.setText(questionText);
        chip1.setText(option1Text);
        chip2.setText(option2Text);
        chip3.setText(option3Text);
        chip4.setText(option4Text);
    }
}
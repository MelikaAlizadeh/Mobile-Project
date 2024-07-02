package com.example.mobileproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int numberOfAllQuestions;//TODO: update these counters
    int numberOfCorrectAnswers;

    TextView tv;
    Chip[] chips = new Chip[5];
    int correctAnswer;
    TextView resultTV;
    TextView messageTV;
    Chip nextChip;
    Question[] questionArray = new Question[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fillQuestionArray(questionArray);

        tv = findViewById(R.id.q_tv);
        chips[1] = findViewById(R.id.op1);
        chips[2] = findViewById(R.id.op2);
        chips[3] = findViewById(R.id.op3);
        chips[4] = findViewById(R.id.op4);
        resultTV = findViewById(R.id.tv_result);
        messageTV = findViewById(R.id.tv_message);
        nextChip = findViewById(R.id.chip_next);


        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, FinalResultActivity.class);
            intent.putExtra("number of all qs", numberOfAllQuestions);
            intent.putExtra("number of corrects", numberOfCorrectAnswers);
            intent.putExtra("end message", "Time is over!");
            startActivity(intent);
            finish();
        }, 10000);

        showNewQuestion(tv, chips[1], chips[2], chips[3], chips[4]);

        for (int i = 1; i < 5; i++) {
            int finalI = i;
            chips[i].setOnClickListener(v -> {
                numberOfAllQuestions++;
                if (finalI == correctAnswer) {
                    numberOfCorrectAnswers++;
                    String tmp;
                    tmp = "CORRECT!";
                    resultTV.setText(tmp);
                } else {
                    String tmp = "WRONG!";
                    resultTV.setText(tmp);
                    tmp = "The correct answer was "
                            + getIntent().getStringExtra("correctAnswer");
                    messageTV.setText(tmp);
                }
            });

            nextChip.setOnClickListener(v -> {
                showNewQuestion(tv, chips[1], chips[2], chips[3], chips[4]);
            });
        }



    }



    private void showNewQuestion(TextView tv, Chip chip1, Chip chip2, Chip chip3, Chip chip4) {
        //TODO: read question from db

        Random random = new Random();
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

    private void fillQuestionArray(Question[] questionArray) {
        StringBuilder txt = new StringBuilder();
        try {
            File myFile = new File("./questionsDB.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null) {
                txt.append(aDataRow);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
}
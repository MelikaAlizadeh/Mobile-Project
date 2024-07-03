package com.example.mobileproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int numberOfAllQuestions;
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
        }, 20000);

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
        int qNumber = random.nextInt(10);
        Question question = questionArray[qNumber];

//        StringBuilder txt = new StringBuilder();
//        try {
//            AssetManager assetManager = getAssets();
//            Toast.makeText(getBaseContext(), assetManager.toString(), Toast.LENGTH_SHORT).show();
//            InputStream inputStream = assetManager.open("qs_db.txt");
//            BufferedReader myReader = new BufferedReader(new InputStreamReader(inputStream));
//            String aDataRow = "";
//            while ((aDataRow = myReader.readLine()) != null) {
//                txt.append(aDataRow);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            Toast.makeText(getBaseContext(), "errorrrrrrrrrrr", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Question question = new Question()

//        Question question = new Question("This is a question", "option1",
//                "option2", "option3", "option4", 1);
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

    private void fillQuestionArray(Question[] qArray) {
//        StringBuilder txt = new StringBuilder();
//        try {
//            File myFile = new File("./questionsDB.txt");
//            FileInputStream fIn = new FileInputStream(myFile);
//            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
//            String aDataRow = "";
//            while ((aDataRow = myReader.readLine()) != null) {
//                txt.append(aDataRow);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        qArray[0] = new Question("In what year did the Great October " +
                "Socialist Revolution take place?",
                "1917", "1923", "1914", "1920", 1);
        qArray[1] = new Question("What is the largest lake in the world?",
                "Caspian Sea", "Baikal", "Lake Superior",
                "Ontario", 2);
        qArray[2] = new Question("Which planet in the solar system is known as the" +
                " “Red Planet”?", "Venus", "Earth",
                "Mars", "Jupiter", 3);
        qArray[3] = new Question("Who wrote the novel “War and Peace”?",
                "Anton Chekhov", "Fyodor Dostoevsky",
                "Leo Tolstoy", "Ivan Turgenev", 3);
        qArray[4] = new Question("What is the capital of Japan?",
                "Beijing", "Tokyo",
                "Seoul", "Bangkok", 2);
        qArray[5] = new Question("Which river is the longest in the world?",
                "Amazon", "Mississippi",
                "Nile", "Yangtze", 3);
        qArray[6] = new Question("What gas is used to extinguish fires?",
                "Oxygen", "Nitrogen",
                "Carbon dioxide", "Hydrogen", 2);
        qArray[7] = new Question("What animal is the national symbol of Australia?",
                "Kangaroo", "Koala", "Emu", "Crocodile", 1);
        qArray[8] = new Question("Which of the following planets is not a gas giant?",
                "Mars", "Jupiter",
                "Saturn", "Uranus", 1);
        qArray[9] = new Question("What is the name of the process by which plants convert sunlight into energy?",
                "Respiration", "Photosynthesis",
                "Oxidation", "Evolution", 2);
        qArray[10] = new Question("What chemical element is designated as “Hg”?",
                "Silver", "Tin",
                "Copper", "Mercury", 4);
        qArray[11] = new Question("In what year was the first international modern Olympiad held?",
                "1896", "1900",
                "1912", "1924", 1);
        qArray[12] = new Question("For which of these disciplines Nobel Prize is awarded?",
                "Physics, Chemistry", "Physiology",
                "Medicine", "All of the above", 4);
        qArray[13] = new Question("Entomology is the science that studies:",
                "Behavior of human beings", "Insects",
                "The origin and history of technical and scientific terms",
                "The formation of rocks", 2);
        qArray[14] = new Question("Hitler's party is known as:",
                "Labour Party", "Nazi Party",
                "Ku-Klux-Klan", "Democratic Party", 2);
        qArray[15] = new Question("For which Galileo is famous?",
                "Developed the telescope", "Discovered four satellites of Jupiter",
                "Found that the swinging motion of the pendulum results in consistent time measurement.",
                "All of the above", 4);
        qArray[16] = new Question("When the First Afghan War took place in:",
                "1839", "1843",
                "1833", "1848", 1);
        qArray[17] = new Question("Ecology deals with:",
                "Birds", "Cell formation",
                "Relation between organisms and their environment",
                "Tissues", 3);
        qArray[18] = new Question("Which is the largest island?",
                "New Guinea", "Andaman Nicobar",
                "Greenland", "Hawaii", 3);
        qArray[19] = new Question("Which one is the hottest continent?",
                "Africa", "South Asia",
                "North America", "Australia", 1);
        qArray[20] = new Question("What do Koalas usually eat?",
                "Bamboo", "Eucalyptus",
                "Aloe Vera", "Banana", 2);
        qArray[21] = new Question("When the First Afghan War took place in:",
                "1839", "1843",
                "1833", "1848", 1);

    }
}
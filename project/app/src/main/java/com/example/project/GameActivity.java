package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private final int numberOfQuestions = 10;
    private final int wholeTime = 10; //in seconds

    int numberOfAnsweredQuestions;
    int numberOfCorrectAnswers;
    int numberOfWrongs;
    TextView tv;
    CardView[] cards = new CardView[5];
    int correctAnswer;
    Chip nextChip;
    Question[] questionArray = new Question[numberOfQuestions];
    int currentNumber = 0;
    Set<Integer> uniqueIntegers = new HashSet<>();
    TextView[] opTVs = new TextView[5];
    Timer timer = new Timer();
    int second = 0;
    TextView timerTextView;
    Intent intent;
    private String category;
    private static final String TAG = "GameActivity";
    private static final String API_URL_GET_QUESTIONS = "http://10.0.2.2:3000/getQuestions";


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        category = getIntent().getStringExtra("table name");

//        fillQuestionArray(questionArray);
        fetchQuestions();

//        timerTextView = findViewById(R.id.timerTextView);
//
//        tv = findViewById(R.id.q_tv);
//        cards[1] = findViewById(R.id.card_op1);
//        cards[2] = findViewById(R.id.card_op2);
//        cards[3] = findViewById(R.id.card_op3);
//        cards[4] = findViewById(R.id.card_op4);
//        opTVs[1] = findViewById(R.id.op1);
//        opTVs[2] = findViewById(R.id.op2);
//        opTVs[3] = findViewById(R.id.op3);
//        opTVs[4] = findViewById(R.id.op4);
//        nextChip = findViewById(R.id.chips_next);
//
//        startTheTimer();
//
//        showNewQuestion();
//
//        setCardsListeners();
//
//        nextChip.setOnClickListener(v -> {
//            if (currentNumber == numberOfQuestions) {
//                finishTheGame();
//                this.finish();
//            }
//            showNewQuestion();
//        });
    }

    private void setCardsListeners() {
        for (int i = 1; i < 5; i++) {
            int finalI = i;
            int finalI1 = i;
            cards[i].setOnClickListener(v -> {
                numberOfAnsweredQuestions++;
                if (finalI == correctAnswer) {
                    cards[finalI1].setCardBackgroundColor(Color.GREEN);
                    numberOfCorrectAnswers++;
                } else {
                    cards[finalI1].setCardBackgroundColor(Color.RED);
                    cards[correctAnswer].setCardBackgroundColor(Color.GREEN);
                    numberOfWrongs++;
                }
                new Handler().postDelayed(() -> {
                    if (currentNumber == numberOfQuestions) {
                        finishTheGame();
                        this.finish();
                    }
                    showNewQuestion();

                }, 500);
            });
        }
    }

    private void finishTheGame() {
        GameActivity.this.runOnUiThread(() -> {
            intent = new Intent(GameActivity.this, FinalResultActivity.class);
            intent.putExtra("number of all qs", numberOfAnsweredQuestions);
            intent.putExtra("number of corrects", numberOfCorrectAnswers);
            intent.putExtra("number of wrongs", numberOfWrongs);
            timer.cancel();
            startActivity(intent);
            finish();
        });

    }

    private void startTheTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                GameActivity.this.runOnUiThread(() -> {
                    second++;
                    if (second == wholeTime) finishTheGame();
                    timerTextView.setText(String.valueOf(second));
                });
            }
        }, 0, 1000);
    }


    @SuppressLint("ResourceAsColor")
    private void showNewQuestion() {
        Question question = questionArray[currentNumber];
        currentNumber++;

        for (int k = 1; k < 5; k++) {
            cards[k].setCardBackgroundColor(R.color.lightBlue);
            setCardsListeners();
        }

        tv.setText(question.getText());
        opTVs[1].setText(question.getOption1());
        opTVs[2].setText(question.getOption2());
        opTVs[3].setText(question.getOption3());
        opTVs[4].setText(question.getOption4());
        correctAnswer = question.getCorrectAnswer();
    }

    private void fetchQuestions() {
        String requestUrl = API_URL_GET_QUESTIONS + "?n=" + numberOfQuestions
                + "&&table=" + category;

        NetworkUtil.fetchJson(requestUrl, new NetworkUtil.OnFetchCompleteListener() {
            @Override
            public void onFetchComplete(JSONArray result) {
                Toast.makeText(GameActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFetchComplete(JSONObject result) {
                Toast.makeText(GameActivity.this, "obj" + result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFetchFailed(Exception e) {
                Log.e(TAG, "Network request failed", e);
                Toast.makeText(GameActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

//    private void fillQuestionArray(Question[] qArray) {
//
//        //TODO: This function has to be changed completely.
//
//        Random random = new Random();
//
//        while (uniqueIntegers.size() < numberOfQuestions) {
//            int randomInt = random.nextInt(36);
//            uniqueIntegers.add(randomInt);
//        }
//
//        qArray[0] = new Question("In what year did the Great October " +
//                "Socialist Revolution take place?",
//                "1917", "1923", "1914", "1920", 1);
//        qArray[1] = new Question("What is the largest lake in the world?",
//                "Caspian Sea", "Baikal", "Lake Superior",
//                "Ontario", 2);
//        qArray[2] = new Question("Which planet in the solar system is known as the" +
//                " “Red Planet”?", "Venus", "Earth",
//                "Mars", "Jupiter", 3);
//        qArray[3] = new Question("Who wrote the novel “War and Peace”?",
//                "Anton Chekhov", "Fyodor Dostoevsky",
//                "Leo Tolstoy", "Ivan Turgenev", 3);
//        qArray[4] = new Question("What is the capital of Japan?",
//                "Beijing", "Tokyo",
//                "Seoul", "Bangkok", 2);
//        qArray[5] = new Question("Which river is the longest in the world?",
//                "Amazon", "Mississippi",
//                "Nile", "Yangtze", 3);
//        qArray[6] = new Question("What gas is used to extinguish fires?",
//                "Oxygen", "Nitrogen",
//                "Carbon dioxide", "Hydrogen", 2);
//        qArray[7] = new Question("What animal is the national symbol of Australia?",
//                "Kangaroo", "Koala", "Emu", "Crocodile", 1);
//        qArray[8] = new Question("Which of the following planets is not a gas giant?",
//                "Mars", "Jupiter",
//                "Saturn", "Uranus", 1);
//        qArray[9] = new Question("What is the name of the process by which plants convert sunlight into energy?",
//                "Respiration", "Photosynthesis",
//                "Oxidation", "Evolution", 2);
//        qArray[10] = new Question("What chemical element is designated as “Hg”?",
//                "Silver", "Tin",
//                "Copper", "Mercury", 4);
//        qArray[11] = new Question("In what year was the first international modern Olympiad held?",
//                "1896", "1900",
//                "1912", "1924", 1);
//        qArray[12] = new Question("For which of these disciplines Nobel Prize is awarded?",
//                "Physics, Chemistry", "Physiology",
//                "Medicine", "All of the above", 4);
//        qArray[13] = new Question("Entomology is the science that studies:",
//                "Behavior of human beings", "Insects",
//                "The origin and history of technical and scientific terms",
//                "The formation of rocks", 2);
//        qArray[14] = new Question("Hitler's party is known as:",
//                "Labour Party", "Nazi Party",
//                "Ku-Klux-Klan", "Democratic Party", 2);
//        qArray[15] = new Question("For which Galileo is famous?",
//                "Developed the telescope", "Discovered four satellites of Jupiter",
//                "Found that the swinging motion of the pendulum results in consistent time measurement.",
//                "All of the above", 4);
//        qArray[16] = new Question("When the First Afghan War took place in:",
//                "1839", "1843",
//                "1833", "1848", 1);
//        qArray[17] = new Question("Ecology deals with:",
//                "Birds", "Cell formation",
//                "Relation between organisms and their environment",
//                "Tissues", 3);
//        qArray[18] = new Question("Which is the largest island?",
//                "New Guinea", "Andaman Nicobar",
//                "Greenland", "Hawaii", 3);
//        qArray[19] = new Question("Which one is the hottest continent?",
//                "Africa", "South Asia",
//                "North America", "Australia", 1);
//        qArray[20] = new Question("What do Koalas usually eat?",
//                "Bamboo", "Eucalyptus",
//                "Aloe Vera", "Banana", 2);
//        qArray[21] = new Question("When the First Afghan War took place in:",
//                "Brioche", "Baguette",
//                "White bread", "Ciabatta", 2);
//        qArray[22] = new Question("What is the official currency of Japan?",
//                "Won", "Yuan",
//                "Yen", "Dollars", 3);
//        qArray[23] = new Question("What is the phobia of thunder and rain?",
//                "Agoraphobia", "Ombrophobia",
//                "Acrophobia", "Claustrophobia", 2);
//        qArray[24] = new Question("What does Carpe Diem mean in Latin?",
//                "Enjoy the moment", "Have no fear",
//                "Sorry I blew it", "Hello", 1);
//        qArray[25] = new Question("Which one of the following countries is not in Africa?",
//                "Morocco", "Yemen",
//                "Sudan", "Algeria", 2);
//        qArray[26] = new Question("What is considered the lung of the Earth?",
//                "Amazon rainforest", "The Mississippi River",
//                "The Sahara", "Everest", 1);
//        qArray[27] = new Question("Where does the Sushi come from?",
//                "Japan", "China",
//                "America", "South Korea", 1);
//        qArray[28] = new Question("In which century the Mona Lisa was painted?",
//                "18th century", "15th century",
//                "16th century", "14th century",
//                3);
//        qArray[29] = new Question("Who wrote the “Great Gatsby” novel?",
//                "Leo Tolstoy", "Hemingway",
//                "Stephen King", "F. Scott Fitzgerald",
//                4);
//        qArray[30] = new Question("Which is the richest country in the world?",
//                "Qatar", "Russia",
//                "The USA", "The UAE", 1);
//        qArray[31] = new Question("Which county is the biggest grower of coffee?",
//                "Spain", "India",
//                "Ethiopia", "Brazil", 4);
//        qArray[32] = new Question("How many bones are in the body of an adult human?",
//                "330", "206",
//                "250", "210", 2);
//        qArray[33] = new Question("When the humans use more facial muscles?",
//                "While smiling", "While frowning",
//                "While sleeping", "While talking",
//                2);
//        qArray[34] = new Question("Who wrote “Crime and Punishment”?",
//                "Leo Tolstoy", "Fyodor Dostoevsky",
//                "Anton Chekhov", "Ivan Turgenev", 2);
//        qArray[35] = new Question("In what year was the United Nations (UN) founded?",
//                "1945", "1919",
//                "1956", "1961", 1);
//    }
}
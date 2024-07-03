package com.example.mobileproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int numberOfAllQuestions;
    int numberOfCorrectAnswers;

    TextView tv;
    CardView[] cards = new CardView[5];
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
        cards[1] = (android.widget.TextView) findViewById(R.id.op1);
        cards[2] = findViewById(R.id.op2);
        cards[3] = findViewById(R.id.op3);
        cards[4] = findViewById(R.id.op4);
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

        showNewQuestion(tv, cards[1], cards[2], cards[3], cards[4]);

        for (int i = 1; i < 5; i++) {
            int finalI = i;
            cards[i].setOnClickListener(v -> {
                numberOfAllQuestions++;
                if (finalI == correctAnswer) {
                    numberOfCorrectAnswers++;
                    String tmp;
                    tmp = "CORRECT!";
                    resultTV.setText(tmp);
                } else {

                }
            });

            nextChip.setOnClickListener(v -> {
                showNewQuestion(tv, cards[1], cards[2], cards[3], cards[4]);
            });
        }


    }


    private void showNewQuestion(TextView tv, Chip chip1, Chip chip2, Chip chip3, Chip chip4) {
        //TODO: read question from db

        Random random = new Random();
        int qNumber = random.nextInt(21);
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
                "Brioche", "Baguette",
                "White bread", "Ciabatta", 2);
        qArray[22] = new Question("What is the official currency of Japan?",
                "Won", "Yuan",
                "Yen", "Dollars", 3);
        qArray[23] = new Question("What is the phobia of thunder and rain?",
                "Agoraphobia", "Ombrophobia",
                "Acrophobia", "Claustrophobia", 2);
        qArray[24] = new Question("What does Carpe Diem mean in Latin?",
                "Enjoy the moment", "Have no fear",
                "Sorry I blew it", "Hello", 1);
        qArray[25] = new Question("Which one of the following countries is not in Africa?",
                "Morocco", "Yemen",
                "Sudan", "Algeria", 2);
        qArray[26] = new Question("What is considered the lung of the Earth?",
                "Amazon rainforest", "The Mississippi River",
                "The Sahara", "Everest", 1);
        qArray[27] = new Question("Where does the Sushi come from?",
                "Japan", "China",
                "America", "South Korea", 1);
        qArray[28] = new Question("In which century the Mona Lisa was painted?",
                "18th century", "15th century",
                "16th century", "14th century",
                3);
        qArray[29] = new Question("Who wrote the “Great Gatsby” novel?",
                "Leo Tolstoy", "Hemingway",
                "Stephen King", "F. Scott Fitzgerald",
                4);
        qArray[30] = new Question("Which is the richest country in the world?",
                "Qatar", "Russia",
                "The USA", "The UAE", 1);
        qArray[31] = new Question("Which county is the biggest grower of coffee?",
                "Spain", "India",
                "Ethiopia", "Brazil", 4);
        qArray[32] = new Question("How many bones are in the body of an adult human?",
                "330", "206",
                "250", "210", 2);
        qArray[33] = new Question("When the humans use more facial muscles?",
                "While smiling", "While frowning",
                "While sleeping", "While talking",
                2);
        qArray[34] = new Question("Who wrote “Crime and Punishment”?",
                "Leo Tolstoy", "Fyodor Dostoevsky",
                "Anton Chekhov", "Ivan Turgenev", 2);



    }
}
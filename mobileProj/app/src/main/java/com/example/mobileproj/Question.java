package com.example.mobileproj;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @ColumnInfo(name = "question_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "question_text")
    @NonNull
    private String text;

    @ColumnInfo(name = "option1")
    @NonNull
    private String option1;

    @ColumnInfo(name = "option2")
    @NonNull
    private String option2;

    @ColumnInfo(name = "option3")
    @NonNull
    private String option3;

    @ColumnInfo(name = "option4")
    @NonNull
    private String option4;

    @ColumnInfo(name = "answer")
    @NonNull
    private int correctAnswer;

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @NonNull
    public String getOption1() {
        return option1;
    }

    public void setOption1(@NonNull String option1) {
        this.option1 = option1;
    }

    @NonNull
    public String getOption2() {
        return option2;
    }

    public void setOption2(@NonNull String option2) {
        this.option2 = option2;
    }

    @NonNull
    public String getOption3() {
        return option3;
    }

    public void setOption3(@NonNull String option3) {
        this.option3 = option3;
    }

    @NonNull
    public String getOption4() {
        return option4;
    }

    public void setOption4(@NonNull String option4) {
        this.option4 = option4;
    }

    @Ignore
    public Question(@NonNull String text, @NonNull String option1, @NonNull String option2,
                    @NonNull String option3, @NonNull String option4, @NonNull int correctAnswer) {
        this.text = text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
    }
}

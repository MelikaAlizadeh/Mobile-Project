package com.example.mobileproj;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    public void addQuestion(Question question);

    @Update
    public void updateQuestion(Question question);

    @Delete
    public void deleteQuestion(Question question);
    @Query("SELECT * FROM questions") public List<Question> getAllQuestions();
}

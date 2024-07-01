package com.example.project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    public void addUser(User user);
    @Update
    public void updateUser(User user);
    @Delete
    public void deleteUser(User user);
    @Query("SELECT * FROM user")
    public List<User> getAllUsers();
    @Query("SELECT * FROM user WHERE username==:username")
    public User getUser(String username);
}

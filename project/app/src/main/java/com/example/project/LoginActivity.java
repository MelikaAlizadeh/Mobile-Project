package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    List<User> usersList;
    UserDB userDB;
    boolean isCheckUsernameExists = false;
    boolean isCheckUserExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        userDB = Room.databaseBuilder(getApplicationContext(), UserDB.class, "userDB").addCallback(myCallBack).build();


        TextInputLayout usernameLayout = findViewById(R.id.textField);
        TextInputEditText username = (TextInputEditText) usernameLayout.getEditText();
        TextInputLayout passwordLayout = findViewById(R.id.textField2);
        TextInputEditText password = (TextInputEditText) passwordLayout.getEditText();
        User newUser = new User(username.getText().toString(), password.getText().toString());

        ImageView login = findViewById(R.id.btnsignin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckUsernameExists = false;
                isCheckUserExists = false;
                checkAllUsersInBackground(newUser);
                String givenUsername = username.getText().toString();
                String givenPassword = password.getText().toString();

                if (givenUsername.length() == 0) {
                    username.setError("Field is empty!");
                } else if (givenPassword.length() == 0) {
                    password.setError("Field is empty!");
                }
                else if (!isCheckUserExists) {
                    username.setError("Invalid combination of username & password!");
                    password.setError("Invalid combination of username & password!");
                } else if (isCheckUserExists) {
                    Toast.makeText(getBaseContext(), "Welcome back " + givenUsername, Toast.LENGTH_LONG).show();
                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                }
            }
        });

        ImageView register = findViewById(R.id.btnsignup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splash = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(splash);
                finish();
            }
        });
    }

    public void checkAllUsersInBackground(User checkUser) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //background task
                usersList = userDB.getUserDAO().getAllUsers();

                //on finishing task
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (User u : usersList) {
                            if (u.getUsername().equals(checkUser.getUsername())) {
                                if(u.getPassword().equals(checkUser.getPassword())){
                                    isCheckUserExists=true;
                                    break;
                                }
                            }
                        }
                    }
                });
            }
        });
    }
}
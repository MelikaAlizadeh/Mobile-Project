package com.example.project;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

//    List<User> usersList;
//    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        RoomDatabase.Callback myCallBack=new RoomDatabase.Callback() {
//            @Override
//            public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                super.onCreate(db);
//            }
//
//            @Override
//            public void onOpen(@NonNull SupportSQLiteDatabase db) {
//                super.onOpen(db);
//            }
//        };
//
//        userDB = Room.databaseBuilder(getApplicationContext(),UserDB.class,"UserDB").addCallback(myCallBack).build();
//        usersList=userDB.getUserDAO().getAllUsers();

        //for test before having database
        String hUsername = "m";
        String hPassword = "10";

        TextInputLayout usernameLayout = findViewById(R.id.textField);
        TextInputEditText username = (TextInputEditText) usernameLayout.getEditText();
        TextInputLayout passwordLayout = findViewById(R.id.textField2);
        TextInputEditText password = (TextInputEditText) passwordLayout.getEditText();

        ImageView login = findViewById(R.id.btnsignin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String givenUsername = username.getText().toString();
                String givenPassword = password.getText().toString();
                if (givenUsername.length() == 0) {
                    username.setError("Field is empty!");
                } else if (givenPassword.length() == 0) {
                    password.setError("Field is empty!");
                } else if (!givenPassword.equals(hPassword) || !givenUsername.equals(hUsername)) {
                    password.setError("Invalid combination of username & password!");
                    username.setError("Invalid combination of username & password!");
                } else if (givenPassword.equals(hPassword) && givenUsername.equals(hUsername)) {
                    Toast.makeText(getBaseContext(), "Welcome back " + givenUsername, Toast.LENGTH_LONG).show();
                    Intent main = new Intent(LoginActivity.this, ProfileActivity.class);
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
}
//TODO: change checking parts according to the database and its functions
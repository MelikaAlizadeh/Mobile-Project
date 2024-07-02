package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    List<User> usersList;
    boolean isCheckUsernameExists = false;
    boolean isCheckUserExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserDatabase db = new UserDatabase(this);

        TextInputLayout usernameLayout = findViewById(R.id.textField);
        TextInputEditText username = (TextInputEditText) usernameLayout.getEditText();
        TextInputLayout passwordLayout = findViewById(R.id.textField2);
        TextInputEditText password = (TextInputEditText) passwordLayout.getEditText();

        ImageView login = findViewById(R.id.btnsignin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckUsernameExists = false;
                User newUser = new User(username.getText().toString(), password.getText().toString());
                String getUserCorrectPassword = getUserFromDb(newUser, db);
                CheckUsernameExists(newUser, db);
                String givenUsername = username.getText().toString();
                String givenPassword = password.getText().toString();

                if (givenUsername.length() == 0) {
                    username.setError("Field is empty!");
                } else if (givenPassword.length() == 0) {
                    password.setError("Field is empty!");
                } else if (!isCheckUsernameExists || !getUserCorrectPassword.equals(newUser.getPassword())) {
                    username.setError("Invalid combination of username & password!");
                    password.setError("Invalid combination of username & password!");
                } else if (isCheckUsernameExists) {
                    Toast.makeText(getBaseContext(), "Welcome back " + givenUsername, Toast.LENGTH_LONG).show();
                    UserDatabase.currentUser = newUser;
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

    private String getUserFromDb(User newUser, UserDatabase db) {
        usersList = db.getAllUsers();
        for (User u : usersList) {
            if (u.getUsername().equals(newUser.getUsername())) {
                return u.getPassword();
            }
        }
        return "";
    }

    private void CheckUsernameExists(User newUser, UserDatabase db) {
        usersList = db.getAllUsers();
        for (User u : usersList) {
            if (u.getUsername().equals(newUser.getUsername())) {
                isCheckUsernameExists = true;
                break;
            }
        }
    }
}
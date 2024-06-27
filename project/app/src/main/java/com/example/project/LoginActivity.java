package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        String hUsername = "m";
//        String hPassword = "10";
//
//        EditText username = findViewById(R.id.username);
//        EditText password = findViewById(R.id.password);
//
//        Button login = findViewById(R.id.loginButton);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String givenUsername = username.getText().toString();
//                String givenPassword = password.getText().toString();
//                if (givenUsername.length() == 0 || givenPassword.length() == 0) {
//                    Toast.makeText(getBaseContext(), "Fields are empty!", Toast.LENGTH_LONG).show();
//                } else if (!givenPassword.equals(hPassword) || !givenUsername.equals(hUsername)) {
//                    Toast.makeText(getBaseContext(), "Invalid username/password!", Toast.LENGTH_LONG).show();
//                } else if (givenPassword.equals(hPassword) && givenUsername.equals(hUsername)) {
//                    Toast.makeText(getBaseContext(), "Welcome back " + givenUsername, Toast.LENGTH_LONG).show();
//                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(main);
//                    finish();
//                }
//            }
//        });
    }
}
//TODO: change checking parts according to the database and its functions
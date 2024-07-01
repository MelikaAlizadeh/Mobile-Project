package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //for test before having database
        String hUsername = "m";
        String hPassword = "10";

        EditText username = findViewById(R.id.textField);
        EditText password = findViewById(R.id.textField2);

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
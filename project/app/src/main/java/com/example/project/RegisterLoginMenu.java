package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterLoginMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login_menu);
//        Button registerButton=findViewById(R.id.resisterButton);
//        Button loginButton=findViewById(R.id.loginButton);
//
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent register = new Intent(RegisterLoginMenu.this, RegisterActivity.class);
//                startActivity(register);
//                finish();
//            }
//        });
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent login = new Intent(RegisterLoginMenu.this, LoginActivity.class);
//                startActivity(login);
//                finish();
//            }
//        });
    }
}
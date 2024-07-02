package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profileImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        String username = getIntent().getStringExtra("currentUserUsername");
//        String password = getIntent().getStringExtra("currentUserPassword");
//        String email = getIntent().getStringExtra("currentUserEmail");
//
//        profileImageView = findViewById(R.id.profileImage);
//
//        File directory = new File(getFilesDir(), "project/images");
//        File imageFile = new File(directory, "selected_image.jpg");
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(imageFile);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            fos.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String imagePath = imageFile.getAbsolutePath();
//        File imgFile = new File(imagePath);
//        if (imgFile.exists()) {
//            profileImageView.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
//            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//            bottomNavigationView.setSelectedItemId(R.id.nav_profile);
//        }
//
//        findViewById(R.id.chip_1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent splash = new Intent(ProfileActivity.this, RegisterActivity.class);
//                startActivity(splash);
//                finish();
//            }
//        });
    }
}
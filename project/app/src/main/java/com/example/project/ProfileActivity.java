package com.example.project;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profileImageView;
    private static final String IMAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyApp/profile_image.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImageView = findViewById(R.id.imageView);

        String imagePath = getIntent().getStringExtra("imagePath");
        if (imagePath != null) {
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                profileImageView.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
            }
        }
    }
}
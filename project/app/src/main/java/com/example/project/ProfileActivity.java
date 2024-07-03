package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    List<User> usersList;
    User selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        UserDatabase db = new UserDatabase(this);

        String imagePath = "data/data/com.example.project/files/project/images/selected_image.jpg";
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.profileImage);
            myImage.setImageBitmap(myBitmap);
        }

        findViewById(R.id.chip_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase.currentUser = null;
                Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        TextInputLayout cityLayout = findViewById(R.id.textField1);
        TextInputEditText city = (TextInputEditText) cityLayout.getEditText();
        String cityStr = city.getText().toString();
        TextInputLayout regionLayout = findViewById(R.id.textField2);
        TextInputEditText region = (TextInputEditText) regionLayout.getEditText();
        String regionStr = region.getText().toString();

        selected = UserDatabase.currentUser;
        selected=findCorrectUser(selected,db);

        TextView name=findViewById(R.id.profileName);
        TextView mail=findViewById(R.id.profileEmail);
        name.setText(selected.getUsername());
        mail.setText(selected.getEmail());

        findViewById(R.id.chip_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(cityStr.length() == 0 || regionStr.length() == 0)) {
                    db.deleteUser(selected);
                    selected.setCity(cityStr);
                    selected.setRegion(regionStr);
                    db.addUser(selected);
                }
            }
        });
    }

    private User findCorrectUser(User newUser, UserDatabase db) {
        usersList = db.getAllUsers();
        for (User u : usersList) {
            if (u.getUsername().equals(newUser.getUsername()) && u.getPassword().equals(newUser.getPassword())) {
                return u;
            }
        }
        return newUser;
    }
}
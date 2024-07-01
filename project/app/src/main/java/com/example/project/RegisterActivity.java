package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}

//        String hEmail = "mm";
//        String hUsername = "m";
//        String hPassword = "10";
//
//        EditText username = findViewById(R.id.username);
//        EditText password = findViewById(R.id.password);
//        EditText email = findViewById(R.id.email);
//        EditText name = findViewById(R.id.name);
//        EditText city = findViewById(R.id.city);
//        EditText region = findViewById(R.id.region);
//        EditText type = findViewById(R.id.type);
//        User newUser=new User(username,password,email,name,city,region,type);
//
//        Button register = findViewById(R.id.registerButton);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String givenUsername = username.getText().toString();
//                String givenPassword = password.getText().toString();
//                String givenEmail = email.getText().toString();
//                if (givenUsername.length() == 0 || givenPassword.length() == 0 || givenEmail.length() == 0) {
//                    Toast.makeText(getBaseContext(), "Fields are empty!", Toast.LENGTH_LONG).show();
//                } else if (givenPassword.equals(hPassword) && givenUsername.equals(hUsername) && givenEmail.equals(hEmail)) {
//                    Toast.makeText(getBaseContext(), "You already have an account!", Toast.LENGTH_LONG).show();
//                } else if (!isUsernameValid(givenUsername)) {
//                    Toast.makeText(getBaseContext(), "Invalid username format!", Toast.LENGTH_LONG).show();
//                } else if (!isPasswordWeak(givenPassword).equals("success")) {
//                    Toast.makeText(getBaseContext(), isPasswordWeak(givenPassword), Toast.LENGTH_LONG).show();
//                } else if (!isEmailValid(givenEmail)) {
//                    Toast.makeText(getBaseContext(), "Invalid email format!", Toast.LENGTH_LONG).show();
//                } else {
//                    print(givenUsername, givenPassword, givenEmail);
//                    //TODO: add newUser to db
//                    Intent main = new Intent(RegisterActivity.this, MainActivity.class);
//                    startActivity(main);
//                    finish();
//                }
//            }
//        });
//
//    }
//
//    private void print(String givenUsername, String givenPassword, String givenEmail) {
//        Toast.makeText(getBaseContext(), "Welcome " + givenUsername + "\n" + givenPassword + "\n" + givenEmail, Toast.LENGTH_LONG).show();
//    }
//
//    public boolean isUsernameValid(String username) {
//        if (username.matches("^[a-zA-Z0-9_]+$"))
//            return true;
//        else
//            return false;
//    }
//
//    public String isPasswordWeak(String password) {
//        if (password.length() < 6)
//            return "The password is too short!";
//        else if (!password.matches("(.*[a-z].*)"))
//            return "Password should have at least one lowercase letter!";
//        else if (!password.matches("(.*[A-Z].*)"))
//            return "Password should have at least one uppercase letter!";
//        else if (!password.matches("(.*[0-9].*)"))
//            return "Password should have at least one digit!";
//        else if (!password.matches("(.*[^a-zA-Z\\d\\s:].*)"))
//            return "Password should have least one alphanumeric character!";
//        else
//            return "success";
//    }
//
//    public boolean isEmailValid(String email) {
//        if (email.matches("^(?<firstGroup>\\S+)@(?<secondGroup>\\S+)\\.(?<thirdGroup>\\S+)$"))
//            return true;
//        else
//            return false;
//    }
//}

//TODO: change checking parts according to the database and its functions

//public class RegisterActivity extends AppCompatActivity {
//
//    private static final int PICK_IMAGE = 1;
//    private ImageView imageView;
//    private String imagePath;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        imageView = findViewById(R.id.imageView);
//
//        findViewById(R.id.button).setOnClickListener(v -> openGallery());
//
//        Button b=findViewById(R.id.button2);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
//                intent.putExtra("imagePath", imagePath);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void openGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, PICK_IMAGE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
//            Uri selectedImage = data.getData();
//            imageView.setImageURI(selectedImage);
//            saveImageToProjectFolder(selectedImage);
//        }
//    }
//
//    private void saveImageToProjectFolder(Uri selectedImageUri) {
//        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
//            File directory = new File(getFilesDir(), "project/images");
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//            File imageFile = new File(directory, "selected_image.jpg");
//            FileOutputStream fos = new FileOutputStream(imageFile);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.close();
//            imagePath = imageFile.getAbsolutePath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
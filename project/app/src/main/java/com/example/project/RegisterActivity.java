package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {

    List<User> usersList;
    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //hardcode for testing before having db
        String hEmail = "mm";
        String hUsername = "m";
        String hPassword = "10";

        TextInputLayout usernameLayout = findViewById(R.id.textField);
        TextInputEditText username = (TextInputEditText) usernameLayout.getEditText();
        TextInputLayout emailLayout = findViewById(R.id.textField2);
        TextInputEditText email = (TextInputEditText) emailLayout.getEditText();
        TextInputLayout passwordLayout = findViewById(R.id.textField3);
        TextInputEditText password = (TextInputEditText) passwordLayout.getEditText();
        TextInputLayout passwordCheckLayout = findViewById(R.id.textField4);
        TextInputEditText passwordCheck = (TextInputEditText) passwordCheckLayout.getEditText();
        User newUser = new User(username.getText().toString(), password.getText().toString(), email.getText().toString());

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


        ImageView register = findViewById(R.id.btnsignup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserInBackground(newUser);
                print(username.getText().toString(), password.getText().toString(), email.getText().toString());
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
//                    //TODO: add newUser to db
//                    Intent splash = new Intent(RegisterActivity.this, MainActivity.class);
//                    startActivity(splash);
//                    finish();
//                }
            }
        });

        ImageView login = findViewById(R.id.btnsignin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(main);
                finish();
            }
        });
    }

    public void addUserInBackground(User user) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //background task
                userDB.getUserDAO().addUser(user);

                //on finishing task
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Welcome", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void getUsersInBackground(User user) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //background task
                userDB.getUserDAO().addUser(user);

                //on finishing task
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Welcome", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    //
    private void print(String givenUsername, String givenPassword, String givenEmail) {
        Toast.makeText(getBaseContext(), "Welcome " + givenUsername + "\n" + givenPassword + "\n" + givenEmail, Toast.LENGTH_LONG).show();
    }
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
}


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
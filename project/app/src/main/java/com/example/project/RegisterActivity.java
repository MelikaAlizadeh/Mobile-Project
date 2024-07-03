package com.example.project;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    List<User> usersList;
    boolean isCheckUsernameExists = false;
    boolean isCheckUserExists = false;
    boolean isImageSet = false;

    private static final int PICK_IMAGE = 1;
    private ImageView imageView;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        UserDatabase db = new UserDatabase(this);

        TextInputLayout usernameLayout = findViewById(R.id.textField);
        TextInputEditText username = (TextInputEditText) usernameLayout.getEditText();
        TextInputLayout emailLayout = findViewById(R.id.textField2);
        TextInputEditText email = (TextInputEditText) emailLayout.getEditText();
        TextInputLayout passwordLayout = findViewById(R.id.textField3);
        TextInputEditText password = (TextInputEditText) passwordLayout.getEditText();
        TextInputLayout passwordCheckLayout = findViewById(R.id.textField4);
        TextInputEditText passwordCheck = (TextInputEditText) passwordCheckLayout.getEditText();

        //to check repeated password is correct or not
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String passwords = password.getText().toString();
                String repeatPassword = passwordCheck.getText().toString();

                if (!repeatPassword.equals(passwords)) {
                    passwordCheckLayout.setBoxStrokeColor(Color.RED);
                    passwordCheckLayout.setHintTextColor(ColorStateList.valueOf(Color.RED));
                } else {
                    passwordCheckLayout.setBoxStrokeColor(getResources().getColor(R.color.hardBlue));
                    passwordCheckLayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.hardBlue)));
                }
            }
        };

        password.addTextChangedListener(textWatcher);
        passwordCheck.addTextChangedListener(textWatcher);

        //to check rest of the fields
        ImageView register = findViewById(R.id.btnsignup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckUsernameExists = false;
                isCheckUserExists = false;
                User newUser = new User(username.getText().toString(), password.getText().toString(), email.getText().toString());
                checkUserExists(newUser, db);
                CheckUsernameExists(newUser, db);
//                print(username.getText().toString(), password.getText().toString(), email.getText().toString());
//                print(newUser.getUsername(), newUser.getPassword(), newUser.getEmail());
                String givenUsername = username.getText().toString();
                String givenPassword = password.getText().toString();
                String givenEmail = email.getText().toString();

                if (givenUsername.length() == 0) {
                    username.setError("Field is empty!");
                } else if (isCheckUsernameExists) {
                    username.setError("This username already exists!");
                } else if (!isUsernameValid(givenUsername)) {
                    username.setError("Invalid username format!");
                } else if (givenEmail.length() == 0) {
                    email.setError("Field is empty!");
                } else if (isCheckUserExists) {
                    email.setError("You already have an account!");
                } else if (!isEmailValid(givenEmail)) {
                    email.setError("Invalid email format!");
                } else if (givenPassword.length() == 0) {
                    password.setError("Field is empty!");
                } else if (!isPasswordWeak(givenPassword).equals("success")) {
                    password.setError(isPasswordWeak(givenPassword));
                } else if (!isImageSet) {
                    Toast.makeText(getBaseContext(), "Please choose an image!", Toast.LENGTH_LONG).show();
                }else {
                    db.addUser(newUser);
                    Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                }
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

        imageView = findViewById(R.id.signUpHeader);

        findViewById(R.id.chip_1).setOnClickListener(v -> openGallery());
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

    private void checkUserExists(User newUser, UserDatabase db) {
        usersList = db.getAllUsers();
        for (User u : usersList) {
            if (u.getEmail().equals(newUser.getEmail())) {
                isCheckUserExists = true;
                break;
            }
        }

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
            saveImageToProjectFolder(selectedImage);
        }
    }

    private void saveImageToProjectFolder(Uri selectedImageUri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            File directory = new File(getFilesDir(), "project/images");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File imageFile = new File(directory, "selected_image.jpg");
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            imagePath = imageFile.getAbsolutePath();
            isImageSet=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void print(String givenUsername, String givenPassword, String givenEmail) {
        Toast.makeText(getBaseContext(), "Welcome " + givenUsername + "\n" + givenPassword + "\n" + givenEmail, Toast.LENGTH_LONG).show();
    }

    public boolean isUsernameValid(String username) {
        if (username.matches("^[a-zA-Z0-9_]+$"))
            return true;
        else
            return false;
    }

    public String isPasswordWeak(String password) {
        if (password.length() < 6)
            return "The password is too short!";
        else if (!password.matches("(.*[a-z].*)"))
            return "Password should have at least one lowercase letter!";
        else if (!password.matches("(.*[A-Z].*)"))
            return "Password should have at least one uppercase letter!";
        else if (!password.matches("(.*[0-9].*)"))
            return "Password should have at least one digit!";
        else if (!password.matches("(.*[^a-zA-Z\\d\\s:].*)"))
            return "Password should have least one alphanumeric character!";
        else
            return "success";
    }

    public boolean isEmailValid(String email) {
        if (email.matches("^(?<firstGroup>\\S+)@(?<secondGroup>\\S+)\\.(?<thirdGroup>\\S+)$"))
            return true;
        else
            return false;
    }
}
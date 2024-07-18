package com.example.project;

import android.annotation.SuppressLint;
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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
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

        TextInputLayout usernameLayout = findViewById(R.id.textField);
        TextInputEditText username = (TextInputEditText) usernameLayout.getEditText();
        TextInputLayout emailLayout = findViewById(R.id.textField2);
        TextInputEditText email = (TextInputEditText) emailLayout.getEditText();
        TextInputLayout passwordLayout = findViewById(R.id.textField3);
        TextInputEditText password = (TextInputEditText) passwordLayout.getEditText();
        TextInputLayout passwordCheckLayout = findViewById(R.id.textField4);
        TextInputEditText passwordCheck = (TextInputEditText) passwordCheckLayout.getEditText();
        TextInputLayout countryLayout = findViewById(R.id.textField5);
        @SuppressLint("WrongViewCast") MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

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
                String repeatPasswords = passwordCheck.getText().toString();
                if (!repeatPasswords.equals(passwords)) {
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

        //check country box
        String[] countries = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
                "Antigua and Barbuda", "Argentina", "Armenia", "Australia",
                "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
                "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
                "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil",
                "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde",
                "Cambodia", "Cameroon", "Canada", "Central African Republic",
                "Chad", "Chile", "China", "Colombia", "Comoros", "Congo",
                "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
                "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
                "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland",
                "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
                "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau",
                "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India",
                "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy",
                "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati",
                "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
                "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
                "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
                "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia",
                "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
                "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand",
                "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia",
                "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama",
                "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland",
                "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis",
                "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
                "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
                "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka",
                "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan",
                "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago",
                "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
                "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City",
                "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                boolean isValid = false;
                for (String country : countries) {
                    if (country.equalsIgnoreCase(input)) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) {
                    countryLayout.setError("Invalid country");
                } else {
                    countryLayout.setError(null);
                }
            }
        });


        //to check rest of the fields
        ImageView register = findViewById(R.id.btnsignup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString();
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                String repeatPasswordStr = passwordCheck.getText().toString();
                String countryStr = autoCompleteTextView.getText().toString();
                isCheckUsernameExists = false;
                isCheckUserExists = false;
                User newUser = new User(usernameStr, passwordStr, emailStr,countryStr,0);
//                checkUserExists(newUser);
//                CheckUsernameExists(newUser);

                if (usernameStr.length() == 0) {
                    usernameLayout.setError("Field is empty!");
                    passwordLayout.setError(null);
                    countryLayout.setError(null);
                    emailLayout.setError(null);
                }
//                else if (isCheckUsernameExists) {
//                    usernameLayout.setError("This username already exists!");
//                    passwordLayout.setError(null);
//                    countryLayout.setError(null);
//                    emailLayout.setError(null);
//                }
                else if (!isUsernameValid(usernameStr)) {
                    usernameLayout.setError("Invalid username format!");
                    passwordLayout.setError(null);
                    countryLayout.setError(null);
                    emailLayout.setError(null);
                } else if (emailStr.length() == 0) {
                    emailLayout.setError("Field is empty!");
                    usernameLayout.setError(null);
                    passwordLayout.setError(null);
                    countryLayout.setError(null);
                }
//                else if (isCheckUserExists) {
//                    emailLayout.setError("You already have an account!");
//                    usernameLayout.setError(null);
//                    passwordLayout.setError(null);
//                    countryLayout.setError(null);
//                }
                else if (!isEmailValid(emailStr)) {
                    emailLayout.setError("Invalid email format!");
                    usernameLayout.setError(null);
                    passwordLayout.setError(null);
                    countryLayout.setError(null);
                }
                else if (passwordStr.length() == 0) {
                    passwordLayout.setError("Field is empty!");
                    usernameLayout.setError(null);
                    emailLayout.setError(null);
                    countryLayout.setError(null);
                } else if (!isPasswordWeak(passwordStr).equals("success")) {
                    passwordLayout.setError(isPasswordWeak(passwordStr));
                    usernameLayout.setError(null);
                    emailLayout.setError(null);
                    countryLayout.setError(null);
                } else if (countryStr.length()==0) {
                    countryLayout.setError("Field is empty!");
                    usernameLayout.setError(null);
                    emailLayout.setError(null);
                    passwordLayout.setError(null);
                } else if (!isImageSet) {
                    Toast.makeText(getBaseContext(), "Please choose an image!", Toast.LENGTH_LONG).show();
                    usernameLayout.setError(null);
                    emailLayout.setError(null);
                    passwordLayout.setError(null);
                    countryLayout.setError(null);
                }else {

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

//    private void CheckUsernameExists(User newUser) {
//        usersList = db.getAllUsers();
//        for (User u : usersList) {
//            if (u.getUsername().equals(newUser.getUsername())) {
//                isCheckUsernameExists = true;
//                break;
//            }
//        }
//    }
//
//    private void checkUserExists(User newUser) {
//        usersList = db.getAllUsers();
//        for (User u : usersList) {
//            if (u.getEmail().equals(newUser.getEmail())) {
//                isCheckUserExists = true;
//                break;
//            }
//        }
//    }

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
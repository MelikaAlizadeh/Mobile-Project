package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {
    boolean check1 = true, check2 = true, check3 = true, check4 = true, check5 = true, check6 = true, check7 = true;

    List<User> usersList;
    boolean isCheckUsernameExists = false;
    boolean isCheckUserExists = false;
    boolean isImageSet = false;

    private static final int PICK_IMAGE = 1;
    private ImageView imageView;
    private String imagePath;

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

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

        //to check username
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String usernameStr = username.getText().toString();
                isCheckUsernameExists = false;
                new FetchUsernameTask().execute(usernameStr);
                if (usernameStr.length() == 0) {
                    usernameLayout.setError("Field is empty!");
                    check2 = true;
                } else if (isCheckUsernameExists) {
                    usernameLayout.setError("This username already exists!");
                    check2 = true;
                } else if (!isUsernameValid(usernameStr)) {
                    usernameLayout.setError("Invalid username format!");
                    check2 = true;
                } else {
                    usernameLayout.setError(null);
                    check2 = false;
                }
            }
        });

        //to check email
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String emailStr = email.getText().toString();
                isCheckUserExists = false;
                new FetchUserTask().execute(emailStr);
                if (emailStr.length() == 0) {
                    emailLayout.setError("Field is empty!");
                    check3 = true;
                } else if (isCheckUserExists) {
                    emailLayout.setError("You already have an account!");
                    check3 = true;
                } else if (!isEmailValid(emailStr)) {
                    emailLayout.setError("Invalid email format!");
                    check3 = true;
                } else {
                    emailLayout.setError(null);
                    check3 = false;
                }
            }
        });

        //to check password
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String passwordStr = password.getText().toString();
                if (passwordStr.length() == 0) {
                    passwordLayout.setError("Field is empty!");
                    check4 = true;
                } else if (!isPasswordWeak(passwordStr).equals("success")) {
                    passwordLayout.setError(isPasswordWeak(passwordStr));
                    check4 = true;
                } else {
                    passwordLayout.setError(null);
                    check4 = false;
                }
            }
        });

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
                String passwordStr = password.getText().toString();
                String repeatPasswordStr = passwordCheck.getText().toString();
                if (!repeatPasswordStr.equals(passwordStr)) {
                    passwordCheckLayout.setError("Passwords do not match!");
                    check5 = true;
                } else {
                    passwordCheckLayout.setError(null);
                    check5 = false;
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
                String countryStr = autoCompleteTextView.getText().toString();
                String input = editable.toString();
                boolean isValid = false;
                for (String country : countries) {
                    if (country.equalsIgnoreCase(input)) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) {
                    countryLayout.setError("Invalid country!");
                    check6 = true;
                } else if (countryStr.length() == 0) {
                    countryLayout.setError("Field is empty!");
                    check6 = true;
                } else {
                    countryLayout.setError(null);
                    check6 = false;
                }
            }
        });


        //to check all fields
        ImageView register = findViewById(R.id.btnsignup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString();
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                String countryStr = autoCompleteTextView.getText().toString();
                User newUser = new User(usernameStr, passwordStr, emailStr, "0", countryStr);

                if (!check2 && !check3 && !check4 && !check5 && !check6) {
                    if (!isImageSet) {
                        Toast.makeText(getBaseContext(), "Please choose an image!", Toast.LENGTH_LONG).show();
                    } else {
                        registerUser(usernameStr, passwordStr, emailStr, "0", countryStr);
                        MainActivity.currentUser = newUser;
                        Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(main);
                        finish();
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "Please fill the fields correctly!", Toast.LENGTH_LONG).show();
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


    public class FetchUserTask extends AsyncTask<String, Void, String> {
        private static final String TAG = "FetchUserTask";
        private static final String API_URL = "http://10.0.2.2:3000/getUser"; // Replace with your URL

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(API_URL + "?email=" + params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                result = stringBuilder.toString();
            } catch (Exception e) {
                Log.e(TAG, "Error during network operation", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.length() == 0) {
//                    textView.setText("Useer dosen't exist");
                    isCheckUserExists = false;
                } else {
                    JSONObject jsonObject = new JSONObject(result);
                    String username = jsonObject.getString("username");
                    String email = jsonObject.getString("email");

                    // Update UI here
                    Log.d(TAG, "User: " + username + ", Email: " + email);
                    isCheckUserExists = true;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error parsing JSON", e);
            }
        }
    }

    public class FetchUsernameTask extends AsyncTask<String, Void, String> {
        private static final String TAG = "FetchUserTask";
        private static final String API_URL = "http://10.0.2.2:3000/getUser"; // Replace with your URL

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(API_URL + "?username=" + params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                result = stringBuilder.toString();
            } catch (Exception e) {
                Log.e(TAG, "Error during network operation", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.length() == 0) {
//                    textView.setText("Useer dosen't exist");
                    isCheckUsernameExists = false;
                } else {
                    JSONObject jsonObject = new JSONObject(result);
                    String username = jsonObject.getString("username");
                    String email = jsonObject.getString("email");

                    // Update UI here
                    Log.d(TAG, "User: " + username + ", Email: " + email);
                    isCheckUsernameExists = true;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error parsing JSON", e);
            }
        }
    }


    private void registerUser(String username, String password, String email, String score, String country) {
        new RegisterUserTask().execute(username, password, email, score, country);
    }

    private class RegisterUserTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String email = params[2];
            String score = params[3];
            String country = params[4];

            OkHttpClient client = new OkHttpClient();

            // Create JSON object
            User user = new User(username, password, email, score, country);
            Gson gson = new Gson();
            String json = gson.toJson(user);

            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
//                    .url("http://192.168.1.10:3000/users") // Use your local IP address
                    .url("http://10.0.2.2:3000/createUser") // Use this for emulator
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    Log.e("RegisterUserTask", "Unexpected code " + response);
                    throw new IOException("Unexpected code " + response);
                }
                return response.body().string();
            } catch (IOException e) {
                Log.e("RegisterUserTask", "Failed to register user", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
//                textView.setText(result);
            } else {
//                textView.setText("Failed to register user");
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
            isImageSet = true;
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
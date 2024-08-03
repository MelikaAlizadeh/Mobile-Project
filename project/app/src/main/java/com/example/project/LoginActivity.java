package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    boolean check1=true,check2=true;
    List<User> usersList;
    String correctPassword;
    boolean isCheckUsernameExists = false;
    boolean isCheckUserExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputLayout usernameLayout = findViewById(R.id.textField);
        TextInputEditText username = (TextInputEditText) usernameLayout.getEditText();
        TextInputLayout passwordLayout = findViewById(R.id.textField2);
        TextInputEditText password = (TextInputEditText) passwordLayout.getEditText();

        // Check if the user is logged in
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // User is already logged in, redirect to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

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
                    check1 = true;
                } else if (!isCheckUsernameExists) {
                    usernameLayout.setError("Invalid username!");
                    check1 = true;
                } else {
                    usernameLayout.setError(null);
                    check1 = false;
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
                    check2 = true;
                } else if (!correctPassword.equals(passwordStr)) {
                    passwordLayout.setError("Invalid combination of username & password!");
                    check2 = true;
                } else {
                    passwordLayout.setError(null);
                    check2 = false;
                }
            }
        });

        ImageView login = findViewById(R.id.btnsignin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString();
                String passwordStr = password.getText().toString();
                User newUser = new User(usernameStr, passwordStr);

                if (isCheckUsernameExists&&!check1&&!check2) {
                    Toast.makeText(getBaseContext(), "Welcome back " + usernameStr, Toast.LENGTH_LONG).show();
                    MainActivity.currentUser=newUser;

                    // Save login state and credentials
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("username", usernameStr);
                    editor.putString("password", passwordStr);
                    editor.apply();

                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(), "Please fill the fields correctly!", Toast.LENGTH_LONG).show();
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
                    String password = jsonObject.getString("password");

                    // Update UI here
                    Log.d(TAG, "User: " + username + ", Password: " + password);
                    correctPassword = password;
                    isCheckUsernameExists = true;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error parsing JSON", e);
            }
        }
    }


}
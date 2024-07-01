package com.moeein.kingofquizes;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class textWatcher extends AppCompatActivity {
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        TextInputLayout textField3 = findViewById(R.id.textField3);
        TextInputLayout textField4 = findViewById(R.id.textField4);
        TextInputEditText editText1 = (TextInputEditText) textField3.getEditText();
        TextInputEditText editText2 = (TextInputEditText) textField4.getEditText();
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editText1.getText().toString();
                String repeatPassword = editText2.getText().toString();

                if (!repeatPassword.equals(password)) {
                    textField4.setBoxStrokeColor(Color.RED);
                    textField4.setHintTextColor(ColorStateList.valueOf(Color.RED));
                } else {
                    textField4.setBoxStrokeColor(getResources().getColor(R.color.hardBlue));
                    textField4.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.hardBlue)));
                }
            }
        };

        editText1.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);
    }
}
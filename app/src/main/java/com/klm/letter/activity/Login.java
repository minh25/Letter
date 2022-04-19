package com.klm.letter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.klm.letter.R;

public class Login extends AppCompatActivity {

    private EditText edit_text_email;
    private EditText edit_text_password;
    private Button button_login;
    private Button button_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_text_email = findViewById(R.id.edit_text_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        button_login = findViewById(R.id.button_login);
        button_signup = findViewById(R.id.button_signup);

        button_signup.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SignUp.class)));
    }
}
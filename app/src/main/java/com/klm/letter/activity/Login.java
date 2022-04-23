package com.klm.letter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.klm.letter.R;

public class Login extends AppCompatActivity {

    private EditText edit_text_email;
    private EditText edit_text_password;
    private Button button_login;
    private Button button_signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init instances
        edit_text_email = findViewById(R.id.edit_text_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        button_login = findViewById(R.id.button_login);
        button_signup = findViewById(R.id.button_signup);

        mAuth = FirebaseAuth.getInstance();

        // Specify instances
        button_login.setOnClickListener(view -> {
            String email = edit_text_email.getText().toString();
            String password = edit_text_password.getText().toString();

            login(email, password);
        });
        button_signup.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SignUp.class)));
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
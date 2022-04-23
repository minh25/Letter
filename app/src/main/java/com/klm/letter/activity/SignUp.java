package com.klm.letter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.klm.letter.R;

public class SignUp extends AppCompatActivity {

    private EditText edit_text_name;
    private EditText edit_text_email;
    private EditText edit_text_password;
    private Button button_signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //
        edit_text_name = findViewById(R.id.edit_text_name);
        edit_text_email = findViewById(R.id.edit_text_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        button_signup = findViewById(R.id.button_signup);

        mAuth = FirebaseAuth.getInstance();

        //
        button_signup.setOnClickListener(view -> {
            String name = edit_text_name.getText().toString();
            String email = edit_text_email.getText().toString();
            String password = edit_text_password.getText().toString();

            signup(name, email, password);
        });
    }

    private void signup(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(SignUp.this, Home.class);
                        startActivity(intent);
                    } else {
                        Log.w("SIGNUP_FAIL", "signInWithEmail:failure", task.getException());
                        Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    AppCompatButton SignInBtn;
    TextView forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignInBtn = findViewById(R.id.SignIn_button);
        forgot_password = findViewById(R.id.forgot_password_txtbtn);


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenForgotPassword();
            }
        });

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMainActivity();
            }
        });

    }

    private void OpenForgotPassword() {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
        finish();
    }

    private void OpenMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
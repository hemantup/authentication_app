package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgotPassword extends AppCompatActivity {


    AppCompatButton sendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        sendotp = findViewById(R.id.Confirm_button_confirm_email);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenOtpVerificationEmail();
            }
        });

    }

    private void OpenOtpVerificationEmail() {
        Intent intent = new Intent(this, OtpVerificationEmail.class);
        startActivity(intent);
        finish();
    }
}
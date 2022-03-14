package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotPassword extends AppCompatActivity {

    TextInputEditText etEmail;
    AppCompatButton confirmEmailandSendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        confirmEmailandSendOtp = findViewById(R.id.Confirm_button_confirm_email);
        etEmail = findViewById(R.id.eEmailForVerification);

        confirmEmailandSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendOtpOnEmail();
                OpenOtpVerificationEmail();
            }
        });

    }

    private void SendOtpOnEmail() {
        String emailtxt = etEmail.getText().toString().trim();

    }

    private void OpenOtpVerificationEmail() {
        String emailtxt = etEmail.getText().toString().trim();
        Intent intent = new Intent(this, OtpVerificationEmail.class);
        intent.putExtra("email",emailtxt);
        startActivity(intent);
        finish();
    }
}
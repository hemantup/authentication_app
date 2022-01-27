package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OtpVerificationEmail extends AppCompatActivity {

    AppCompatButton confirmotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_email);

        confirmotp = findViewById(R.id.Confirm_button_otp_email);

        confirmotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCreatePassword();
            }
        });

    }

    private void OpenCreatePassword() {
        Intent intent = new Intent(this, CreatePassword.class);
        startActivity(intent);
        finish();
    }
}
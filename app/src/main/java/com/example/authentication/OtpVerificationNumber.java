package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OtpVerificationNumber extends AppCompatActivity {

    AppCompatButton confirmbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_number);

        confirmbtn = findViewById(R.id.Confirm_button_otp_number);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenSignIn();
            }
        });

    }

    private void OpenSignIn() {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
        finish();
    }
}
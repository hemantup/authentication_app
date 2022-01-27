package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreatePassword extends AppCompatActivity {

    AppCompatButton createPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        createPassword = findViewById(R.id.confirm_button_create_password);

        createPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenSignIn();
            }
        });
    }

    private void OpenSignIn() {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
        finish();
    }
}
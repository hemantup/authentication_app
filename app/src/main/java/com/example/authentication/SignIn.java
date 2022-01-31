package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SignIn extends AppCompatActivity {

    AppCompatButton SignInBtn;
    TextView forgot_password,signup;
    TextInputLayout tlUsername, tlPassword;
    TextInputEditText etUsername, etPassword;
    FirebaseFirestore final_firestore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignInBtn = findViewById(R.id.SignIn_button);
        forgot_password = findViewById(R.id.forgot_password_txtbtn);
        tlUsername = findViewById(R.id.userName);
        tlPassword = findViewById(R.id.password);
        etUsername = findViewById(R.id.eUserName);
        etPassword = findViewById(R.id.ePassword);
        signup = findViewById(R.id.tvSignUn);


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenForgotPassword();
            }
        });

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getentries();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });

    }

    private void OpenForgotPassword() {
        Intent intent = new Intent(SignIn.this,ForgotPassword.class);
        startActivity(intent);
    }

    private void getentries() {
        String password = etPassword.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        DocumentReference userRef = final_firestore.collection("entries").document(username);
        userRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String passwordDB = value.getString("Password");
                if(passwordDB.equals(password)){
                    OpenMainActivity();
                }else{
                    etPassword.setError("Wrong Password");
                }
            }
        });

    }

    private void OpenMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
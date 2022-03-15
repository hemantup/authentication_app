package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class OtpVerificationNumber extends AppCompatActivity {

    AppCompatButton confirmBtn,sendOtpBtn;
    TextInputEditText otpText;

    private FirebaseAuth mAuth;

    String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_number);

        confirmBtn = findViewById(R.id.Confirm_button_otp_number);
        sendOtpBtn = findViewById(R.id.Send_button_otp_number);
        otpText = findViewById(R.id.eotp_number);
        User user = (User) getIntent().getSerializableExtra("userInformation");

        mAuth = FirebaseAuth.getInstance();


        sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+91" + user.MobileNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS)       // Timeout and unit
                                .setActivity(OtpVerificationNumber.this)        // Activity (for callback binding)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                    }
                                    @Override
                                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    verificationCode = verificationId;
                                    sendOtpBtn.setVisibility(View.GONE);
                                    confirmBtn.setVisibility(View.VISIBLE);
                                    }
                                })          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });



        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sOtpText = otpText.getText().toString().trim();
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationCode,sOtpText);
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                        FirebaseFirestore final_firestore = FirebaseFirestore.getInstance();
                                        final_firestore.collection("entries").document(user.UniversityRollNumber).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(OtpVerificationNumber.this,"registered",Toast.LENGTH_SHORT).show();
                                                    OpenSignIn();
                                                }else{
                                                    Toast.makeText(OtpVerificationNumber.this,"not registered",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                }else {
                                    Toast.makeText(OtpVerificationNumber.this,"Invalid OTP",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    private void OpenSignIn() {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
        finish();
    }
}
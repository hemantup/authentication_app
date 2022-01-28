package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputEditText firstname, lastname, urn, email, password, address, phone;
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2,autoCompleteTxt3;

    ArrayAdapter<String> adapteritem,adapteritem2,adapteritem3;
    AppCompatButton signup;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        String year_dropdown[] = getResources().getStringArray(R.array.year_dropdown);
        String branch_dropdown[] = getResources().getStringArray(R.array.branch_dropdown);
        String MobileNumberCode[]= getResources().getStringArray(R.array.preNumberCode);

        firstname = findViewById(R.id.eFirstName);
        lastname = findViewById(R.id.eLastName);
        urn = findViewById(R.id.eUniversityRoll);
        email = findViewById(R.id.eEmail);
        password = findViewById(R.id.ePassword);
        address = findViewById(R.id.eAddressLine);
        phone = findViewById(R.id.eMobileNumber);
        autoCompleteTxt=findViewById(R.id.AutoCompleteTextViewYear);
        autoCompleteTxt2= findViewById(R.id.AutoCompleteTextViewBranch);
        autoCompleteTxt3=findViewById(R.id.AutoCompleteTextViewNumberCode);
        signup = findViewById(R.id.SignUp_button);


        adapteritem = new ArrayAdapter<String>(this,R.layout.dropdown,year_dropdown);
        adapteritem2 = new ArrayAdapter<String>(this,R.layout.dropdown,branch_dropdown);
        adapteritem3 = new ArrayAdapter<String>(this,R.layout.dropdown,MobileNumberCode);

        autoCompleteTxt.setAdapter(adapteritem);
        autoCompleteTxt2.setAdapter(adapteritem2);
        autoCompleteTxt3.setAdapter(adapteritem3);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName= firstname.getText().toString().trim();
                String lastName= lastname.getText().toString().trim();
                String UniversityRollNumber= urn.getText().toString().trim();
                String Email= email.getText().toString().trim();
                String Password =password.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String MobileNumber = phone.getText().toString().trim();


                mAuth.createUserWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    user user = new user(firstName,lastName,UniversityRollNumber,Email, Password,Address,MobileNumber);
                                    FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = mdatabase.getReference().child("Users");
                                        myRef
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(SignUp.this, "Registered", Toast.LENGTH_SHORT).show();
                                                OpenOtpVerificationNumber();
                                            }else{
                                                Toast.makeText(SignUp.this, "Failed to register", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                                }else{
                                    Toast.makeText(SignUp.this,"failed to register try again",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }


    private void OpenOtpVerificationNumber() {
        Intent intent = new Intent(this, OtpVerificationNumber.class);
        startActivity(intent);
        finish();
    }
}
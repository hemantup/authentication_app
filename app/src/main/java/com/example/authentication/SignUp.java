package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    //  layout variables
    TextInputEditText etFirstName, etLastName, etUniRollNo, etEmail, etPassword, etAddress, etMobileNumber;
    TextInputLayout tlFirstName, tlLastName, tlUniRollNo, tlEmail, tlPassword, tlAddress, tlMobileNumber, tlYear, tlBranch;
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    TextView tvSignIn;

    //  Array adapter for dropdown menu
    ArrayAdapter<String> adapterItem, adapterItem2;
    AppCompatButton signup;

    String genderTxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //  assigning variables their layout
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etUniRollNo = findViewById(R.id.etUniversityRoll);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddressLine);
        etMobileNumber = findViewById(R.id.etMobileNumber);

        tlFirstName = findViewById(R.id.tvFirstName);
        tlLastName = findViewById(R.id.tvLastName);
        tlUniRollNo = findViewById(R.id.tvUniversityRoll);
        tlEmail = findViewById(R.id.tvEmail);
        tlPassword = findViewById(R.id.tvPassword);
        tlAddress = findViewById(R.id.tvAddress);
        tlMobileNumber = findViewById(R.id.tvMobileNumber);
        tlYear = findViewById(R.id.tvYear);
        tlBranch = findViewById(R.id.tvBranch);

        tvSignIn = findViewById(R.id.tvSignIn);

        String[] year_dropdown = getResources().getStringArray(R.array.year_dropdown);
        String[] branch_dropdown = getResources().getStringArray(R.array.branch_dropdown);
      
        autoCompleteTxt=findViewById(R.id.AutoCompleteTextViewYear);
        autoCompleteTxt2= findViewById(R.id.AutoCompleteTextViewBranch);

        signup = findViewById(R.id.SignUp_button);

        adapterItem = new ArrayAdapter<String>(this, R.layout.dropdown, year_dropdown);
        adapterItem2 = new ArrayAdapter<String>(this, R.layout.dropdown, branch_dropdown);

        autoCompleteTxt.setAdapter(adapterItem);
        autoCompleteTxt2.setAdapter(adapterItem2);


        //  onTextChanged on First Name
        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  auto generated
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  first name constraint
                String firstName= etFirstName.getText().toString().trim();
                if(firstName.isEmpty()){
                    etFirstName.requestFocus();
                    tlFirstName.setErrorEnabled(true);
                    tlFirstName.setError("Can't be left empty");
                }else{
                    tlFirstName.setErrorEnabled(false);
                    tlFirstName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // auto generated
            }
        });

        //  onTextChanged LastName
        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  auto generated
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  first name constraint
                String lastName = etLastName.getText().toString().trim();
                if(lastName.isEmpty()){
                    etLastName.requestFocus();
                    tlLastName.setErrorEnabled(true);
                    tlLastName.setError("Can't be left empty");
                }else{
                    tlLastName.setErrorEnabled(false);
                    tlLastName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // auto generated
            }
        });

        //  onTextChange University Roll
        etUniRollNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // University Roll number constraint
                String UniversityRollNumber= etUniRollNo.getText().toString().trim();
                if(UniversityRollNumber.isEmpty()){
                    etUniRollNo.requestFocus();
                    tlUniRollNo.setErrorEnabled(true);
                    tlUniRollNo.setError("Can't be left empty");
                }else if(UniversityRollNumber.length()<13){
                    etUniRollNo.requestFocus();
                    tlUniRollNo.setErrorEnabled(true);
                    tlUniRollNo.setError("University Roll Number must be 13 digits");
                }else{
                    tlUniRollNo.setErrorEnabled(false);
                    tlUniRollNo.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Ontextchanged mobile number
        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String mobileNumber = etMobileNumber.getText().toString().trim();
                if(mobileNumber.isEmpty()){
                    etMobileNumber.requestFocus();
                    tlMobileNumber.setErrorEnabled(true);
                    tlMobileNumber.setError("*required");
                }else if(mobileNumber.length() !=10){
                    etMobileNumber.requestFocus();
                    tlMobileNumber.setErrorEnabled(true);
                    tlMobileNumber.setError("Mobile number must be of 10 digits");
                }else{
                    tlMobileNumber.setErrorEnabled(false);
                    tlMobileNumber.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //  Onclick listener on Sign Up Button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Credential to string
                String Address = etAddress.getText().toString().trim();
                String MobileNumber = etMobileNumber.getText().toString().trim();
                String Password = etPassword.getText().toString().trim();
                String Email = etEmail.getText().toString().trim();
                String branch = autoCompleteTxt2.getText().toString().trim();
                String year = autoCompleteTxt.getText().toString().trim();
                String UniversityRollNumber= etUniRollNo.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String firstName = etFirstName.getText().toString().trim();

                User user = new User(firstName,lastName,UniversityRollNumber,Email, Password,Address,MobileNumber,year,branch);
                infoVerification(user);
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignIn();
            }
        });
    }

    private void openSignIn() {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    private void infoVerification(User user) {

        //  Year constraint
        if(user.year.isEmpty()){
            tlYear.setErrorEnabled(true);
            tlYear.setError("Year Required");
            return;
        }else{
            tlYear.setErrorEnabled(false);
            tlYear.setError(null);
        }

        //  branch constraint
        if(user.branch.isEmpty()){
            tlBranch.setErrorEnabled(true);
            tlBranch.setError("Branch Required");
            return;
        }else{
            tlBranch.setErrorEnabled(false);
            tlBranch.setError(null);
        }

        //  Password and Email constraints
        if(user.Password.isEmpty() | user.Email.isEmpty()){
            if (user.Password.isEmpty()){
                etPassword.requestFocus();
                tlPassword.setErrorEnabled(true);
                tlPassword.setError("*Required");
                return;
            }else{
                etEmail.requestFocus();
                tlEmail.setErrorEnabled(true);
                tlEmail.setError("provide an email");
                return;
            }

        }else {
            String checkPassword = "^" +
                    "(?=.*[@#$%&+=])" +  //at least 1 special character
                    "(?=.*[0-9])" +      //at least 1 digit
                    "(?=.*[a-zA-Z])" +   //any letter
                    "(?=\\S+$)" +        //no white spaces
                    ".{8,}";             //at least 8 character
            if(!user.Password.matches(checkPassword) | !Patterns.EMAIL_ADDRESS.matcher(user.Email).matches()){
                if(!user.Password.matches(checkPassword)){
                    etPassword.requestFocus();
                    tlPassword.setErrorEnabled(true);
                    tlPassword.setError("Password must be 8character long and 1digit1specialchar with no white spaces");
                    return;
                }else{
                    etEmail.requestFocus();
                    tlEmail.setErrorEnabled(true);
                    tlEmail.setError("Provide a valid Email");
                    return;
                }

            }else{
                tlPassword.setErrorEnabled(false);
                tlPassword.setError(null);
                tlEmail.setErrorEnabled(false);
                tlEmail.setError(null);

                //  open Otp number verification activity
                OpenOtpVerificationNumber(user);
            }
        }


    }

    private void OpenOtpVerificationNumber(User user) {
        Intent intent = new Intent(this, OtpVerificationNumber.class);
        intent.putExtra("userInformation", user);
        startActivity(intent);
    }
}
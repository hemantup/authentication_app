package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.annotation.Nullable;

public class SignUp extends AppCompatActivity {

    //  layout variables
    TextInputEditText etFirstName, etLastName, etUniRollNo, etEmail, etPassword, etAddress, etMobileNumber;
    TextInputLayout tlFirstName, tlLastName, tlUniRollNo, tlEmail, tlPassword, tlAddress, tlMobileNumber, tlYear, tlBranch;
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    TextView tvSignIn;
    RadioGroup rgGender;

    String SGender="";

    //  Array adapter for dropdown menu
    ArrayAdapter<String> adapterItem, adapterItem2;
    AppCompatButton signup;

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

        rgGender = findViewById(R.id.radioGroupGender);

        tvSignIn = findViewById(R.id.tvSignIn);

        String[] year_dropdown = getResources().getStringArray(R.array.year_dropdown);
        String[] branch_dropdown = getResources().getStringArray(R.array.branch_dropdown);
      
        autoCompleteTxt=findViewById(R.id.AutoCompleteTextViewYear);
        autoCompleteTxt2= findViewById(R.id.AutoCompleteTextViewBranch);

        signup = findViewById(R.id.SignUp_button);

        adapterItem = new ArrayAdapter<>(this, R.layout.dropdown, year_dropdown);
        adapterItem2 = new ArrayAdapter<>(this, R.layout.dropdown, branch_dropdown);

        autoCompleteTxt.setAdapter(adapterItem);
        autoCompleteTxt2.setAdapter(adapterItem2);


        //  onTextChanged on First Name
        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  auto generated
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                validateName(tlFirstName, etFirstName, editable);
            }
        });

        //  onTextChanged LastName
        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  auto generated
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                validateName(tlLastName, etLastName, editable);
            }
        });

        //  onTextChange University Roll
        etUniRollNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                validateUniversityRoll(tlUniRollNo,etUniRollNo,editable.toString().trim());
            }
        });

        //Ontextchanged mobile number
        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                validateNumber(tlMobileNumber,etMobileNumber,editable.toString().trim());
            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail(tlEmail,etEmail,s.toString().trim());
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

    private boolean validateYearBranch(@Nullable TextInputLayout inputLayout, @Nullable String year){
        //  Year constraint
        if(inputLayout == null){
            return false;
        }
        if(TextUtils.isEmpty(year)){
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("Year Required");
            return false;
        }else{
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            return true;
        }
    }

    private boolean validateNumber(@Nullable TextInputLayout inputLayout,
                                   @Nullable EditText editText, @Nullable String number){
        if (inputLayout == null || editText == null || number == null) {
            return false;
        }
        if(TextUtils.isEmpty(number)) {
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("*required");
            return false;
        }else if(number.length() != 10){
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("Mobile number must be of 10 digits");
            return false;
        }else{
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            return true;
        }
    }

    private boolean validateUniversityRoll(@Nullable TextInputLayout inputLayout,
                                           @Nullable EditText editText, @Nullable String uniRoll){
        if(inputLayout == null || editText == null ) {
            return false;
        }
        // University Roll number constraint
        if(TextUtils.isEmpty(uniRoll)){
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("Can't be left empty");
            return false;
        }else if(uniRoll.length()<13){
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("University Roll Number must be 13 digits");
            return false;
        }else{
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            return true;
        }
    }

    private boolean validateName(@Nullable TextInputLayout inputLayout,
                                 @Nullable EditText editText, @Nullable String name) {
        if (inputLayout == null || editText == null) {
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("Can't be left empty");

            return false;
        } else {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);

            return true;
        }
    }

    private boolean validateName(@Nullable TextInputLayout inputLayout,
                                 @Nullable EditText editText, @Nullable Editable name) {
        return validateName(inputLayout, editText, name != null ? name.toString().trim() : null);
    }

    private boolean validateEmail(@Nullable TextInputLayout inputLayout,
                                  @Nullable EditText editText, @Nullable String email){
        if(inputLayout == null || editText == null || email == null ) {
            return false;

        }
        if(TextUtils.isEmpty(email)){
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("provide an email address");
            return false;
            }else if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editText.requestFocus();
                    inputLayout.setErrorEnabled(true);
                    inputLayout.setError("Provide an valid email");
                    return false;
                }else{
                inputLayout.setErrorEnabled(false);
                inputLayout.setError(null);
                return true;
            }
    }

    private boolean validatePassword(@Nullable TextInputLayout inputLayout,
                                     @Nullable EditText editText, @Nullable String password) {

        if(inputLayout == null || editText == null ) {
            return false;
        }
        String checkPassword = "^" +
                "(?=.*[@#$%&+=])" +  //at least 1 special character
                "(?=.*[0-9])" +      //at least 1 digit
                "(?=.*[a-zA-Z])" +   //any letter
                "(?=\\S+$)" +        //no white spaces
                ".{8,}";             //at least 8 character
        if (!password.matches(checkPassword)) {
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("Password must be 8character long and 1digit1specialchar with no white spaces");
            return false;
        } else {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            return true;
        }
    }

    private boolean validateAddress(@Nullable TextInputLayout inputLayout,
                                    @Nullable EditText editText,@Nullable String address){
        if(inputLayout ==null || editText == null){
            return false;
        }
        if (TextUtils.isEmpty(address)){
            editText.requestFocus();
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("Address required");
            return false;
        }else{
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            return true;
        }
    }

    private void openSignIn() {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    private void infoVerification(User user) {

            int checkedRadioButtonId = rgGender.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.rbMale) {
                SGender = "Male";
            }else if (checkedRadioButtonId == R.id.rbFemale) {
                SGender = "Female";
            }else if(checkedRadioButtonId == R.id.rbOther)
                    SGender = "Other";

            if(TextUtils.isEmpty(SGender)){
                Toast.makeText(this, "gender select kr lo maharaj", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(this, SGender, Toast.LENGTH_SHORT).show();

            boolean number = validateNumber(tlMobileNumber,etMobileNumber,etMobileNumber.getText().toString().trim());
            boolean address = validateAddress(tlAddress,etAddress, etAddress.getText().toString().trim());
            boolean password = validatePassword(tlPassword,etPassword,etPassword.getText().toString().trim());
            boolean branch = validateYearBranch(tlBranch, autoCompleteTxt2.getText().toString().trim());
            boolean year = validateYearBranch(tlYear, autoCompleteTxt.getText().toString().trim());
            boolean email = validateEmail(tlEmail,etEmail,etEmail.getText().toString().trim());
            boolean uniRoll = validateUniversityRoll(tlUniRollNo,etUniRollNo,etUniRollNo.getText().toString().trim());
            boolean lastName = validateName(tlLastName, etLastName, etLastName.getText());
            boolean firstName = validateName(tlFirstName, etFirstName, etFirstName.getText());

            if (!firstName || !lastName || !uniRoll || !email || !year || !branch || !password || !address || !number) {
                return;
            }else{
                //  open Otp number verification activity
                OpenOtpVerificationNumber(user);
                }
        }
    private void OpenOtpVerificationNumber(User user) {
        Intent intent = new Intent(this, OtpVerificationNumber.class);
        intent.putExtra("userInformation", user);
        startActivity(intent);
    }
}
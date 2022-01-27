package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class SignUp extends AppCompatActivity {
    
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2,autoCompleteTxt3;

    ArrayAdapter<String> adapteritem,adapteritem2,adapteritem3;
    AppCompatButton signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        String year_dropdown[] = getResources().getStringArray(R.array.year_dropdown);
        String branch_dropdown[] = getResources().getStringArray(R.array.branch_dropdown);
        String MobileNumberCode[]= getResources().getStringArray(R.array.preNumberCode);

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
                OpenOtpVerificationNumber();
            }
        });

    }

    private void OpenOtpVerificationNumber() {
        Intent intent = new Intent(this, OtpVerificationNumber.class);
        startActivity(intent);
        finish();
    }
}
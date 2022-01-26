package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class SignUp extends AppCompatActivity {
    
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;

    ArrayAdapter<String> adapteritem,adapteritem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        String year_dropdown[] = getResources().getStringArray(R.array.year_dropdown);
        String branch_dropdown[] = getResources().getStringArray(R.array.branch_dropdown);

        autoCompleteTxt=findViewById(R.id.AutoCompleteTextView);
        autoCompleteTxt2= findViewById(R.id.AutoCompleteTextView2);

        adapteritem = new ArrayAdapter<String>(this,R.layout.dropdown,year_dropdown);
        adapteritem2 = new ArrayAdapter<String>(this,R.layout.dropdown,branch_dropdown);

        autoCompleteTxt.setAdapter(adapteritem);
        autoCompleteTxt2.setAdapter(adapteritem2);

    }
}
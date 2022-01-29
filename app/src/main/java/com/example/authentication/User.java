package com.example.authentication;

import java.io.Serializable;

public class User implements Serializable {
    public String firstName,lastName,UniversityRollNumber,Email, Password,Address,MobileNumber,year,branch;

    public User(){

    }

    public User(String fName, String lName, String Unr, String email, String password, String add, String MobNo,String year,String branch){
        this.firstName = fName;
        this.lastName = lName;
        this.UniversityRollNumber = Unr;
        this.Email = email;
        this.Password = password;
        this.Address = add;
        this.MobileNumber = MobNo;
        this.year = year;
        this.branch = branch;
    }
}

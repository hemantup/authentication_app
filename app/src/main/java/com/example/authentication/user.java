package com.example.authentication;

public class user {
    public String firstName,lastName,UniversityRollNumber,Email, Password,Address,MobileNumber;

    public user(){

    }

    public user(String fName,String lName, String Unr, String email, String password, String add, String MobNo){
        this.firstName = fName;
        this.lastName = lName;
        this.UniversityRollNumber = Unr;
        this.Email = email;
        this.Password = password;
        this.Address = add;
        this.MobileNumber = MobNo;
    }
}

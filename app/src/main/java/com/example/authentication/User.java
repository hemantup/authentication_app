package com.example.authentication;

public class User {
    public String firstname, lastname, urn, email, password, address, phone;
    public User(){

    }
    public User(String firstname,String lastname,String urn,String email,String password,String address,String phone){
        this.firstname=firstname;
        this.lastname=lastname;
        this.urn=urn;
        this.email=email;
        this.password=password;
        this.address=address;
        this.phone=phone;

    }
}

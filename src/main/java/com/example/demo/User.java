package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String firstname ;
    private String lastname ;
    private String userName ;
    private String email ;
    private String password ;
    private String phoneNumber ;

    public User(String firstname, String lastname, String userName, String email, String phoneNumber, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public static boolean checkPass (String password) {
//        Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:
        Pattern pattern1 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

        Matcher matcher1 = pattern1.matcher(password);

        return matcher1.find() ;
    }

    public static boolean checkUserName (String userName) {
//        username is 8-20 characters long / no _ or . at the beginning / no __ or _. or ._ or .. inside / no _ or . at the end
        Pattern pattern = Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Matcher matcher = pattern.matcher(userName);

        return matcher.find();
    }

    public static boolean checkName (String name) {
        Pattern pattern = Pattern.compile("^[a-zA-z]{2,30}");
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    public static boolean checkPhoneNum (String phoneNumber) {
        Pattern pattern = Pattern.compile("^[0-9]\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.find();
    }

    public static boolean checkEmail (String email) {
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") ;

        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }


    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

package com.example.user.internproject.model;

import android.text.TextUtils;
import android.util.Patterns;

public class LoginUser {

    private String strEmailAddress;
    private String strPassword;

    public LoginUser() {
    }

    public LoginUser(String EmailAddress, String Password) {
        strEmailAddress = EmailAddress;
        strPassword = Password;
    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }
    public void setStrEmailAddress(String strEmailAdress){
        this.strEmailAddress = strEmailAdress;
    }

    public void setStrPassword(String strPassword){
        this.strPassword = strPassword;
    }
    public String getStrPassword() {
        return strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }
    public boolean isValid(){
        return !TextUtils.isEmpty(strEmailAddress) && !TextUtils.isEmpty(strPassword) && getStrPassword().length()>5;
    }

}

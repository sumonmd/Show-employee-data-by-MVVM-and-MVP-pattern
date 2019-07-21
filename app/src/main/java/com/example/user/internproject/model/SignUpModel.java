package com.example.user.internproject.model;

import android.text.TextUtils;
import android.util.Patterns;

public class SignUpModel {
    private String strEmailAddress;
    private String strPassword;
    private String strFullName;
    private String confirmPassword;

    public SignUpModel() {
    }

    public SignUpModel(String strEmailAddress, String strPassword, String strFullName, String confirmPassword) {
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
        this.strFullName = strFullName;
        this.confirmPassword = confirmPassword;
    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public void setStrEmailAddress(String strEmailAddress) {
        this.strEmailAddress = strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrFullName() {
        return strFullName;
    }

    public void setStrFullName(String strFullName) {
        this.strFullName = strFullName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }

    public boolean isConformPasswordMatch(){
        return strPassword.equals(confirmPassword);
    }
    public boolean isValid(){
        return !TextUtils.isEmpty(strEmailAddress) && !TextUtils.isEmpty(strPassword) && getStrPassword().length()>5;
    }

}

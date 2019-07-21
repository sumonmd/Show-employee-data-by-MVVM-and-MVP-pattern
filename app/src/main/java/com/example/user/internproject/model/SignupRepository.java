package com.example.user.internproject.model;


import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.lifecycle.MutableLiveData;

public class SignupRepository {
    public SignupRepository() {
    }

    private MutableLiveData<String> userMutableLiveData;


    public MutableLiveData<String> getUser(SignUpModel signUpModel) {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        ParseUser user = new ParseUser();
// Set the user's username and password, which can be obtained by a forms

        user.setUsername(signUpModel.getStrEmailAddress().trim());
        user.setEmail(signUpModel.getStrEmailAddress().trim());
        user.setPassword(signUpModel.getStrPassword());
        user.put("name",signUpModel.getStrFullName());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    userMutableLiveData.setValue("True");
                } else {

                   userMutableLiveData.setValue("false");
                }
            }
        });


        return userMutableLiveData;

    }

}

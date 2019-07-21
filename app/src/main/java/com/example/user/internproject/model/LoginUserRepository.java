
package com.example.user.internproject.model;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.lifecycle.MutableLiveData;


public class LoginUserRepository{

    public LoginUserRepository() {
    }

    private MutableLiveData<String> userMutableLiveData;


    public MutableLiveData<String> getUser(LoginUser loginUser) {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        ParseUser.logInInBackground( loginUser.getStrEmailAddress(),loginUser.getStrPassword() , new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    userMutableLiveData.setValue("true");
                } else {
                    userMutableLiveData.setValue("false");
                }
            }
        });

        return userMutableLiveData;

    }


}


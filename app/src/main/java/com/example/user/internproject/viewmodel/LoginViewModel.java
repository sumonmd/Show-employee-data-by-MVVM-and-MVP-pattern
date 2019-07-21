package com.example.user.internproject.viewmodel;

import android.view.View;

import com.example.user.internproject.model.LoginUser;
import com.example.user.internproject.model.LoginUserRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    private LoginUserRepository loginUserRepository = new LoginUserRepository();
    private MutableLiveData<LoginUser> userMutableLiveData;

    public MutableLiveData<LoginUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }


    public void onClick(View view) {
        LoginUser loginUser = new LoginUser(EmailAddress.getValue(), Password.getValue());
        userMutableLiveData.setValue(loginUser);
    }

    public MutableLiveData<String> Signp(LoginUser loginUser){
        return loginUserRepository.getUser(loginUser);
    }

}

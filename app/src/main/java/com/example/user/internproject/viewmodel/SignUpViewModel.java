package com.example.user.internproject.viewmodel;

import android.view.View;

import com.example.user.internproject.model.SignUpModel;
import com.example.user.internproject.model.SignupRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignUpViewModel extends ViewModel {
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    public MutableLiveData<String> FullName = new MutableLiveData<>();
    public MutableLiveData<String>ConformPassword = new MutableLiveData<>();

    private SignupRepository signupRepository = new SignupRepository();
    private MutableLiveData<SignUpModel> userMutableLiveData;

    public MutableLiveData<SignUpModel> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(View view) {
        SignUpModel signUpModel = new SignUpModel(EmailAddress.getValue(), Password.getValue(),FullName.getValue(),ConformPassword.getValue());
        userMutableLiveData.setValue(signUpModel);
    }

    public MutableLiveData<String> Signp(SignUpModel signUpModel){
        return signupRepository.getUser(signUpModel);
    }

}

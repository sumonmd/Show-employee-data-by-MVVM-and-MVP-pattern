package com.example.user.internproject.view;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.internproject.R;

import com.example.user.internproject.databinding.ActivitySinginBinding;


import com.example.user.internproject.model.LoginUser;
import com.example.user.internproject.viewmodel.LoginViewModel;


import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SigninActivity extends AppCompatActivity{


    private LoginViewModel loginViewModel;

    private ActivitySinginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(SigninActivity.this, R.layout.activity_singin);
        binding.setLifecycleOwner(this);

        binding.setLoginViewModel(loginViewModel);


        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress())) {
                        binding.edEmail.setError("Enter an E-Mail Address");
                        binding.edEmail.requestFocus();
                    }
                    else if (!loginUser.isEmailValid()) {
                        binding.edEmail.setError("Enter a Valid E-mail Address");
                        binding.edEmail.requestFocus();
                    }
                    else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword())) {
                        binding.edPassword.setError("Enter a Password");
                        binding.edPassword.requestFocus();
                    }
                    else if (!loginUser.isPasswordLengthGreaterThan5()) {
                        binding.edPassword.setError("Enter at least 6 Digit password");
                        binding.edPassword.requestFocus();
                    }
                    else {
                        loginViewModel.Signp(loginUser).observe(SigninActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                if(s.equals("true")){
                                    Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(SigninActivity.this, "Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                }

            }
        });
    }





    public void Signup(View view) {
        Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {

        Intent intent = new Intent(SigninActivity.this,ResetPasswordActivity.class);
        startActivity(intent);

    }

    public void backUp(View view) {
        onBackPressed();
    }

}

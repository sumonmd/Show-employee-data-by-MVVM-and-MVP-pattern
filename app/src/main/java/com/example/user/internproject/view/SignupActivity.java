package com.example.user.internproject.view;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.user.internproject.R;
import com.example.user.internproject.databinding.ActivitySignupBinding;

import com.example.user.internproject.model.SignUpModel;

import com.example.user.internproject.viewmodel.SignUpViewModel;


import java.util.Objects;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SignupActivity extends AppCompatActivity {


    private SignUpViewModel signupViewModel;
    private ActivitySignupBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signupViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        binding = DataBindingUtil.setContentView(SignupActivity.this, R.layout.activity_signup);
        binding.setLifecycleOwner(this);

        binding.setSignUpViewModel(signupViewModel);

        signupViewModel.getUser().observe(this, new Observer<SignUpModel>() {
            @Override
            public void onChanged(SignUpModel signUpModel) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (TextUtils.isEmpty(Objects.requireNonNull(signUpModel).getStrEmailAddress())) {
                        binding.edEmail.setError("Enter an E-Mail Address");
                        binding.edEmail.requestFocus();
                    } else if (!signUpModel.isEmailValid()) {
                        binding.edEmail.setError("Enter a Valid E-mail Address");
                        binding.edEmail.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(signUpModel).getStrPassword())) {
                        binding.edPassword.setError("Enter a Password");
                        binding.edPassword.requestFocus();
                    } else if (!signUpModel.isPasswordLengthGreaterThan5()) {
                        binding.edPassword.setError("Enter at least 6 Digit password");
                        binding.edPassword.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(signUpModel).getConfirmPassword())) {
                        binding.edConfirmPassword.setError("Enter a Conform Password");
                        binding.edConfirmPassword.requestFocus();
                    }else if (!signUpModel.isConformPasswordMatch()) {
                        binding.edConfirmPassword.setError("Conform Password don't Match");
                        binding.edConfirmPassword.requestFocus();
                    }else{
                        signupViewModel.Signp(signUpModel).observe(SignupActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                if(s.equals("True")){
                                    Intent intent = new Intent(SignupActivity.this,SigninActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(SignupActivity.this,"Error", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }
                }
            }
        });

    }



    public void backUp2(View view) {
        onBackPressed();
    }
}

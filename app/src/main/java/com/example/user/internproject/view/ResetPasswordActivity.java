package com.example.user.internproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.internproject.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email = findViewById(R.id.edEmail);
    }


    public void resetPassword(View view) {
        if (TextUtils.isEmpty(email.getText())) {

            email.setError("Email is required!");

        } else {
            ParseUser.requestPasswordResetInBackground(email.getText().toString(), new RequestPasswordResetCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // An email was successfully sent with reset instructions.
                        Toast.makeText(ResetPasswordActivity.this, "An Email was successfully sent with reset instruction", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResetPasswordActivity.this,SigninActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Something went wrong. Look at the ParseException to see what's up.
                        Toast.makeText(ResetPasswordActivity.this, "Something Wrong", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    public void backUp3(View view) {
        onBackPressed();
    }
}

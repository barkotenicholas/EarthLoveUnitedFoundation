package com.example.earth.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.earth.R;
import com.example.earth.databinding.ActivitySignUpBinding;
import com.example.earth.ui.otp.OtpVerificationActivity;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding signUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = signUpBinding.getRoot();
        setContentView(view);

        signUpBinding.signButton.setOnClickListener( view1->{
            Intent intent = new Intent(getApplicationContext(), OtpVerificationActivity.class);
            startActivity(intent);
        });
    }
}
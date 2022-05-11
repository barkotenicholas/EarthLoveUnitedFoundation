package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.earth.R;
import com.example.earth.databinding.ActivityLaunchBinding;
import com.example.earth.ui.login.ActivityLogin;
import com.example.earth.ui.signup.SignUpActivity;

public class LaunchActivity extends AppCompatActivity {


    ActivityLaunchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaunchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.signUp.setOnClickListener(view->{
            startActivity(new Intent(LaunchActivity.this, SignUpActivity.class));

        });

        binding.logIn.setOnClickListener(view->{
            startActivity(new Intent(LaunchActivity.this, ActivityLogin.class));
        });

    }
}
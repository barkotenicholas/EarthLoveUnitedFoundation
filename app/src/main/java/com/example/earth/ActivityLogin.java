package com.example.earth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.earth.databinding.ActivityLoginBinding;
import com.example.earth.ui.signup.SignUpActivity;

public class ActivityLogin extends AppCompatActivity {


    ActivityLoginBinding binding;
    String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.layLogin.forgotpass.setOnClickListener(view -> {
            startActivity(new Intent(ActivityLogin.this,ResetPassword.class));
        });

        binding.signupbtn.setOnClickListener(view -> {
            startActivity(new Intent(ActivityLogin.this, SignUpActivity.class));
        });

        binding.layLogin.signinbutton.setOnClickListener(view -> {
                login();
        });

    }

    private void login() {

        if(valid() && validatePass()){


        }

    }

    private boolean valid() {

        String email  = binding.layLogin.Name.getText().toString().trim();

        if(email.isEmpty()){
            binding.layLogin.Name.setError("Please input email");
            return false;
        }else if(!email.matches(emailRegex)){
            binding.layLogin.Name.setError("Invalid Email!");
            binding.layLogin.Name.getText().clear();
            return false;
        }else {
            binding.layLogin.Name.setError(null);
            return true;
        }

    }

    public boolean validatePass(){
        String pass   = binding.layLogin.password.getText().toString().trim();
        if(pass.isEmpty()){
            binding.layLogin.password.setError("Please input password");
            binding.layLogin.password.getText().clear();
            return false;
        }else {
            binding.layLogin.password.setError(null);
            return true;
        }
    }
}
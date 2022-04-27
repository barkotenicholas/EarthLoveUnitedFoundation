package com.example.earth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.earth.databinding.ActivityLoginBinding;

public class ActivityLogin extends AppCompatActivity {


    ActivityLoginBinding binding;
    String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.layLogin.forgotpass.setOnClickListener(view -> {
            startActivity(new Intent(ActivityLogin.this,ResetPassword.class));
        });

        binding.signupbtn.setOnClickListener(view -> {
            startActivity(new Intent(ActivityLogin.this,ActivityRegister.class));
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

        String email  = binding.layLogin.email.getText().toString().trim();

        if(email.isEmpty()){
            binding.layLogin.email.setError("Please input email");
            return false;
        }else if(!email.matches(emailRegex)){
            binding.layLogin.email.setError("Invalid Email!");
            return false;
        }else {
            binding.layLogin.email.setError(null);
            return true;
        }

    }

    public boolean validatePass(){
        String pass   = binding.layLogin.password.getText().toString().trim();
        if(pass.isEmpty()){
            binding.layLogin.password.setError("Please input password");
            return false;
        }else {
            binding.layLogin.password.setError(null);
            return true;
        }
    }
}
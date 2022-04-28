package com.example.earth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.earth.databinding.ActivityRegisterBinding;
import com.example.earth.models.UserProfile;

public class ActivityRegister extends AppCompatActivity {


    ActivityRegisterBinding binding;
    String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.button.setOnClickListener(view -> {
            if(validateName() && validateEmail()  && validatepass()){
                String pass = binding.layRegister.password.getText().toString().trim();
                String email  = binding.layRegister.Email.getText().toString().trim();
                String name  = binding.layRegister.Name.getText().toString().trim();

                UserProfile userProfile = new UserProfile();

                userProfile.setName(name);
                userProfile.setEmail(email);
                userProfile.setPass(pass);


                Intent intent = new Intent(ActivityRegister.this,ActivityPhone.class);
                intent.putExtra("PROFILE",userProfile);
                startActivity(intent);

            }
        });
    }

    private boolean validatepass() {

        String pass = binding.layRegister.password.getText().toString().trim();
        String pass1 = binding.layRegister.confirmPass.getText().toString().trim();
        if(pass.isEmpty()){
            binding.layRegister.password.setError("Please input Password");
            return false;
        }else if (pass1.isEmpty()){
            binding.layRegister.confirmPass.setError("Please input Password");
            return false;
        }
        else if(!pass.equals(pass1)){
            binding.layRegister.confirmPass.setError("Password Not same");
            binding.layRegister.password.setError("Password Not same");
            return false;
        }else {
            binding.layRegister.confirmPass.setError(null);
            binding.layRegister.password.setError(null);
            return true;
        }

    }

    private boolean validateEmail() {
        String email  = binding.layRegister.Email.getText().toString().trim();

        if(email.isEmpty()){
            binding.layRegister.Email.setError("Please input email");
            return false;
        }else if(!email.matches(emailRegex)){
            binding.layRegister.Email.setError("Invalid Email!");
            return false;
        }else {
            binding.layRegister.Email.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        String name  = binding.layRegister.Name.getText().toString().trim();

        if(name.isEmpty()){
            binding.layRegister.Name.setError("Please input name");
            return false;
        }else{
            binding.layRegister.Name.setError(null);
            return true;
        }

    }
}
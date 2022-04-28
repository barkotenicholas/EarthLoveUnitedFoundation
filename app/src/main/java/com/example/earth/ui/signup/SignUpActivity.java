package com.example.earth.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
            String name = signUpBinding.nameEditText.getText().toString().trim();
            String email = signUpBinding.emailEditText.getText().toString().trim();
            String password = signUpBinding.userPassword.getText().toString().trim();
            String confirmPassword = signUpBinding.confirmPassword.getText().toString().trim();

            boolean validEmail = isValidEmail(email);
            boolean validName = isValidName(name);
            boolean validPassword = isValidPassword(password, confirmPassword);

            if (!validEmail || !validName || !validPassword) {
                Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), OtpVerificationActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidEmail(String Email) {

        boolean validEmail = (Email != null && Email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"));
        if (!validEmail) {
            signUpBinding.emailEditText.getText().clear();
            signUpBinding.emailEditText.setError("Please enter a valid Email");
            return false;
        }
        return true;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            signUpBinding.nameEditText.getText().clear();
            signUpBinding.nameEditText.setError("Kindly enter your name ");
            return false;
        }

        if(!name.matches("([a-zA-z]+|[a-zA-Z]+\\s[a-zA-Z]+)*")){
            signUpBinding.nameEditText.getText().clear();
            signUpBinding.nameEditText.setError("Kindly give a valid name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            signUpBinding.userPassword.getText().clear();
            signUpBinding.confirmPassword.getText().clear();
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_LONG).show();
            signUpBinding.userPassword.setError("Passwords must have 6 characters or more");
            return false;
        } else if (!password.equals(confirmPassword)) {
            signUpBinding.confirmPassword.getText().clear();
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
            signUpBinding.confirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}
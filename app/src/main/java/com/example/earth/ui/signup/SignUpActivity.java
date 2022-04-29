package com.example.earth.ui.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.databinding.ActivitySignUpBinding;
import com.example.earth.models.UserProfile;
import com.example.earth.ui.otp.OtpVerificationActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding signUpBinding;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = signUpBinding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();





        signUpBinding.signButton.setOnClickListener( view1->{
            String name = signUpBinding.nameEditText.getText().toString().trim();
            String email = signUpBinding.emailEditText.getText().toString().trim();
            String password = signUpBinding.userPassword.getText().toString().trim();
            String confirmPassword = signUpBinding.confirmPassword.getText().toString().trim();
            String phoneNumber =signUpBinding.phoneNumberEdit.getText().toString().trim();
            boolean validEmail = isValidEmail(email);
            boolean validName = isValidName(name);
            boolean validPassword = isValidPassword(password, confirmPassword);

            if (!validEmail || !validName || !validPassword) {
                Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                return;
            }

            UserProfile userProfile = new UserProfile();
            userProfile.setName(name);
            userProfile.setEmail(email);
            userProfile.setPass(password);
            userProfile.setPhone(phoneNumber);
            intent = new Intent(getApplicationContext(), OtpVerificationActivity.class);
            intent.putExtra("EXTRA",userProfile);
            signUp(userProfile);


        });
    }

    private void signUp(UserProfile userProfile) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(userProfile.getPhone())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(getmCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks getmCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String c = phoneAuthCredential.getSmsCode();
                if(c != null){

                    intent.putExtra("CODE", c);
                    startActivity(intent);
                }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(SignUpActivity.this,"Error could not verify phone",Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {

            Log.d("TAG", "onCodeSent:" + verificationId);

            mVerificationId = verificationId;
            mResendToken = token;
        }
    };

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
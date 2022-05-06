package com.example.earth.ui.signup;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.earth.databinding.ActivityResetPasswordBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    ActivityResetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        binding.passwordreset.setOnClickListener(view -> {

            if(valid()){
                String email = binding.recoverytext.getText().toString().trim();
                sendRecoveryEmail(email);
            }

        });

    }

    private void sendRecoveryEmail(String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                       showDialogBox();
                    }
                });
    }

    private void showDialogBox() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ResetPassword.this);
        builder1.setMessage("Recovery email has been sent to your inbox.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                (dialog, id) -> dialog.cancel());
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private boolean valid() {

        String email = binding.recoverytext.getText().toString().trim();

        if(email.isEmpty()){
            binding.recoverytext.setError("Please input email");
            binding.recoverytext.getText().clear();
            return  false;
        }
        if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            binding.recoverytext.setError("Please input correct email format");
            binding.recoverytext.getText().clear();
            return false;
        }else {
            binding.recoverytext.setError(null);
            return true;
        }
    }
}
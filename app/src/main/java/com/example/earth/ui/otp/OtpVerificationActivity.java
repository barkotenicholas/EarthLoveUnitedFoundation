package com.example.earth.ui.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.earth.R;
import com.example.earth.adapters.OtpAdapter;
import com.example.earth.adapters.OtpNumberAdapter;
import com.example.earth.databinding.ActivityOtpVerificationBinding;
import com.example.earth.models.UserProfile;

public class OtpVerificationActivity extends AppCompatActivity {

    private ActivityOtpVerificationBinding otpVerificationBinding;
    UserProfile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        otpVerificationBinding = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        View view = otpVerificationBinding.getRoot();
        setContentView(view);

        Intent intent= getIntent();
        userProfile = (UserProfile) intent.getSerializableExtra("EXTRA");
        String code = intent.getStringExtra("CODE");
        OtpAdapter adapter = new OtpAdapter(getSupportFragmentManager(),getLifecycle(),code);
        otpVerificationBinding.otpViewPager.setAdapter(adapter);
    }
}
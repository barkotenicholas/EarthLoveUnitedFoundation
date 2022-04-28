package com.example.earth.ui.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.earth.R;
import com.example.earth.adapters.OtpNumberAdapter;
import com.example.earth.databinding.ActivityOtpVerificationBinding;

public class OtpVerificationActivity extends AppCompatActivity {

    private ActivityOtpVerificationBinding otpVerificationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        otpVerificationBinding = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        View view = otpVerificationBinding.getRoot();
        setContentView(view);

        OtpNumberAdapter adapter = new OtpNumberAdapter(getSupportFragmentManager(),getLifecycle());

        otpVerificationBinding.otpViewPager.setAdapter(adapter);
    }
}
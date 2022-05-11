package com.example.earth.ui.otp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.adapters.OtpAdapter;
import com.example.earth.databinding.ActivityOtpVerificationBinding;
import com.example.earth.databinding.FragmentNumberBinding;

import java.util.Objects;
import java.util.zip.Inflater;


public class NumberFragment extends Fragment {

    private FragmentNumberBinding numberBinding;
    private ActivityOtpVerificationBinding otpVerificationBinding;

    public NumberFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        otpVerificationBinding = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        numberBinding = FragmentNumberBinding.inflate(inflater,container,false);




        return numberBinding.getRoot();
    }

}
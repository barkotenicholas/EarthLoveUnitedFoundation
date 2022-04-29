package com.example.earth.ui.otp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.databinding.FragmentCodeBinding;

public class CodeFragment extends Fragment {


    FragmentCodeBinding binding;
    public CodeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCodeBinding.inflate(inflater,container,false);
        String code = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
          code = bundle.getString("CODE");
        }

        binding.otpCodeNext.setText(code);

        return binding.getRoot();
    }
}
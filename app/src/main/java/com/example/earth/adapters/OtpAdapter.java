package com.example.earth.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.earth.ui.otp.CodeFragment;

public class OtpAdapter extends FragmentStateAdapter {

    String code;

    public OtpAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String code) {
        super(fragmentManager, lifecycle);
        this.code = code;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        CodeFragment codeFragment = new CodeFragment();
        bundle.putString("CODE", this.code);
        codeFragment.setArguments(bundle);

        return codeFragment;
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

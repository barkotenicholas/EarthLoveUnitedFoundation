package com.example.earth.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.earth.ui.otp.CodeFragment;

public class OtpAdapter extends FragmentStateAdapter {

    public OtpAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new CodeFragment();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

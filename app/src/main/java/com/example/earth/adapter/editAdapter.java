package com.example.earth.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.earth.ui.CreateProfileFragment;
import com.example.earth.ui.EditFragment;

public class editAdapter extends FragmentStateAdapter {
    public editAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new EditFragment();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
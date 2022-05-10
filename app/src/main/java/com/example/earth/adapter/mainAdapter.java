package com.example.earth.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.earth.ui.ClubsFragment;
import com.example.earth.ui.CreateProfileFragment;
import com.example.earth.ui.NotificationsFragment;

public class mainAdapter  extends FragmentStateAdapter {
    public mainAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new NotificationsFragment();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

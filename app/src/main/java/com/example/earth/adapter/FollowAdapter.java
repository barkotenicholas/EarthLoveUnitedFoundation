package com.example.earth.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.earth.ui.FollowersFragment;
import com.example.earth.ui.FollowingFragment;

public class FollowAdapter extends FragmentStateAdapter {
    public FollowAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FollowersFragment();
            case 1:
                return new FollowingFragment();
        }
        return new FollowersFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

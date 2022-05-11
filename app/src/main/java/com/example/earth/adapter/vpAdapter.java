package com.example.earth.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.earth.ui.CommentsPostFragment;
import com.example.earth.ui.PhotoPostsFragment;
import com.example.earth.ui.VideosPostFragment;

import java.util.ArrayList;

public class vpAdapter extends FragmentStateAdapter {

    public vpAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PhotoPostsFragment();
            case 1:
                return new VideosPostFragment();
            case 2:
                return new CommentsPostFragment();
        }
        return new PhotoPostsFragment();
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}

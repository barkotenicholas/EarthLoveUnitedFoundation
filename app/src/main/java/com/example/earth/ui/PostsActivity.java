package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.earth.R;
import com.example.earth.adapter.vpAdapter;
import com.example.earth.databinding.ActivityPostsBinding;
import com.example.earth.databinding.ActivityProfileBinding;
import com.google.android.material.tabs.TabLayout;

public class PostsActivity extends AppCompatActivity {
private TabLayout tabLayout;
private ViewPager2 viewPager;
ActivityPostsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}
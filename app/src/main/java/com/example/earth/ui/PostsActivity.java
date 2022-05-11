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
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PostsActivity extends AppCompatActivity {
private TabLayout tabLayout;
private ViewPager2 viewPager;
ActivityPostsBinding binding;
    List<String> tabsLabels;
    vpAdapter vpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostsBinding.inflate(getLayoutInflater());

        tabsLabels=new ArrayList<String>(Arrays.asList("Images","Videos","Posts"));
        viewPager=binding.tabViewPager;
        tabLayout=binding.postsTabLayout;
        vpAdapter=new vpAdapter(getSupportFragmentManager(),getLifecycle());
        binding.tabViewPager.setAdapter(vpAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabsLabels.get (position))
        ).attach();
        setContentView(binding.getRoot());
        }
    }

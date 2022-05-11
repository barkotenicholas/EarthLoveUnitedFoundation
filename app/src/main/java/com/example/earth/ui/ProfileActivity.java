package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.earth.adapter.profileAdapter;
import com.example.earth.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
ActivityProfileBinding binding;
profileAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack("ROOT", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        adapter=new profileAdapter(getSupportFragmentManager(),getLifecycle());
        binding.createProfileViewPager.setAdapter(adapter);
}}
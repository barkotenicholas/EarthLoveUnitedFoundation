package com.example.earth.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.earth.R;
import com.example.earth.adapter.InterestsRecyclerviewAdapter;
import com.example.earth.adapter.profileAdapter;
import com.example.earth.databinding.ActivityProfileBinding;
import com.example.earth.models.User;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
ActivityProfileBinding binding;
profileAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter=new profileAdapter(getSupportFragmentManager(),getLifecycle());
        binding.createProfileViewPager.setAdapter(adapter);
}}
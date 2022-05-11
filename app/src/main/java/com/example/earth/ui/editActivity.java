package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.earth.R;
import com.example.earth.adapter.editAdapter;
import com.example.earth.adapter.mainAdapter;
import com.example.earth.databinding.ActivityEditBinding;
import com.example.earth.databinding.ActivityMainBinding;

public class editActivity extends AppCompatActivity {
ActivityEditBinding binding;
editAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter=new editAdapter(getSupportFragmentManager(),getLifecycle());
        binding.editViewPager.setAdapter(adapter);
    }
}
package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.earth.R;
import com.example.earth.databinding.ActivityMainBinding;
import com.example.earth.databinding.ActivityProfileBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
  }
}
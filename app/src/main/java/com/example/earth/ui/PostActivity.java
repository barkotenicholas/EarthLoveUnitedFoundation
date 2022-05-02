package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.earth.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import com.example.earth.R;

public class PostActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.one.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();

        });
    }
}
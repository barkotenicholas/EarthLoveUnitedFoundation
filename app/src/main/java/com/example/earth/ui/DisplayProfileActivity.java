package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.earth.R;
import com.example.earth.databinding.ActivityDisplayProfileBinding;
import com.example.earth.databinding.ActivityMainBinding;

public class DisplayProfileActivity extends AppCompatActivity {
ActivityDisplayProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        binding = ActivityDisplayProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
binding.followersLayout3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});
        binding.followingLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.savedLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
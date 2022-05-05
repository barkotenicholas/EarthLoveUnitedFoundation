package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        AppCompatActivity activity=(AppCompatActivity)view.getContext();
       FollowersFragment FollowersFragment=new FollowersFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.followersFragment,FollowersFragment).addToBackStack(null).commit();
    }
});
        binding.followingLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                FollowingFragment FollowingFragment=new FollowingFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.followingFragment,FollowingFragment).addToBackStack(null).commit();
            }
        });
        binding.savedLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayProfileActivity.this, PostsActivity.class);
                startActivity(intent);
            }
        });
    }

}
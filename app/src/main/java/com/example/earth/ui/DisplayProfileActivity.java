package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.databinding.ActivityDisplayProfileBinding;
import com.example.earth.databinding.ActivityMainBinding;
import com.example.earth.models.profile;
import com.google.gson.Gson;

public class DisplayProfileActivity extends AppCompatActivity {
ActivityDisplayProfileBinding binding;
    private SharedPreferences mSharedPreferences;
    Gson gson;
    profile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayProfileBinding.inflate(getLayoutInflater());
       gson=new Gson();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userDetails=mSharedPreferences.getString("userDetails","");
        userProfile=gson.fromJson(userDetails,profile.class);
Log.d("userName",userProfile.getName());
        System.out.println(userProfile.getName());
      binding.newName.setText(userProfile.getName());
      binding.profileAge.setText(userProfile.getBirthday());
      binding.userStory.setText(userProfile.getStory());
      binding.userImage.setImageURI(userProfile.getImageUri());

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
        setContentView(binding.getRoot());
    }


}
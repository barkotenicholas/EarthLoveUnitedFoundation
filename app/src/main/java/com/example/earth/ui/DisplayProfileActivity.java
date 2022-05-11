package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.adapter.blackListAdapter;
import com.example.earth.adapter.clubsAdapter;
import com.example.earth.adapter.photoPostAdapter;
import com.example.earth.databinding.ActivityDisplayProfileBinding;
import com.example.earth.databinding.ActivityMainBinding;
import com.example.earth.models.profile;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayProfileActivity extends AppCompatActivity {
ActivityDisplayProfileBinding binding;
    private SharedPreferences mSharedPreferences;
    List<String> interestsLists;
    List<String> clubsLists;
    List<Integer> postsLists;
    RecyclerView interestsRecycler;
    RecyclerView clubsRecycler;
    RecyclerView postsRecycler;
    Gson gson;
    profile userProfile;
    blackListAdapter interestsAdapter;
    blackListAdapter clubsAdapter;
    photoPostAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayProfileBinding.inflate(getLayoutInflater());
       gson=new Gson();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userDetails=mSharedPreferences.getString("userDetails","");
        userProfile=gson.fromJson(userDetails,profile.class);
        System.out.println(userProfile.getName());
      binding.newName.setText(userProfile.getName());
      binding.profileAge.setText(userProfile.getBirthday());
      binding.userStory.setText(userProfile.getStory());
      binding.userImage.setImageURI(userProfile.getImageUri());

      interestsRecycler=binding.interestsRecycler;
        clubsRecycler=binding.groupsRecycler;
        postsRecycler=binding.photosRecycler;

        clubsLists=new ArrayList<String>(Arrays.asList("Environment","Severe Weather","Deforestation","Polar Landscape","Water Levels","Forest Fire","Deforestation"));
        clubsAdapter=new blackListAdapter(this,clubsLists);
        clubsRecycler.setAdapter( clubsAdapter);
        clubsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        postsLists=new ArrayList<Integer>(Arrays.asList(R.drawable.contactsshare,R.drawable.girl,R.drawable.globe,R.drawable.logo,R.drawable.loveunited,R.drawable.globe,R.drawable.logo));
        postsAdapter=new photoPostAdapter(this,postsLists);
        postsRecycler.setAdapter( postsAdapter);
        postsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        interestsLists=new ArrayList<String>(Arrays.asList("Severe Weather","Deforestation","Environment","Polar Landscape","Forest Fire","Deforestation","Water Levels"));
        interestsAdapter=new blackListAdapter(this,interestsLists);
        interestsRecycler.setAdapter( interestsAdapter);
        interestsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
binding.followersLayout3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AppCompatActivity activity=(AppCompatActivity)view.getContext();
       FollowersFragment FollowersFragment=new FollowersFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.scrollView2,FollowersFragment).addToBackStack(null).commit();
    }
});
        binding.followingLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                FollowingFragment FollowingFragment=new FollowingFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.scrollView2,FollowingFragment).addToBackStack(null).commit();
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
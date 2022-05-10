package com.example.earth.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.earth.R;
import com.example.earth.adapter.FollowAdapter;
import com.example.earth.databinding.ActivityFollowBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class FollowActivity extends AppCompatActivity {
    private ActivityFollowBinding followBinding;
    private TabLayout followLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            followBinding = ActivityFollowBinding.inflate(getLayoutInflater());
            View view = followBinding.getRoot();

            List<String> tabHeads = Arrays.asList("Followers", "Following");
            followLayout = followBinding.followTabLayout;
            viewPager = followBinding.followViewPager;
            setContentView(view);

            FollowAdapter adapter = new FollowAdapter(getSupportFragmentManager(), getLifecycle());
            viewPager.setAdapter(adapter);

            new TabLayoutMediator(followLayout, viewPager, (tab, position) -> tab.setText(tabHeads.get(position))).attach();


            followBinding.followBackButton.setOnClickListener(view1 -> {
               /*To Do
               *
               * Go Back to previous Activity
               * */
            });


            /* To Do
            *
            * 1)Firebase Recycler For Followers and Following
            * 2)Following and remove buttons cause change in firebase on click
            * 3)Sort options change data in firebase List
            *
            * */

    }
}
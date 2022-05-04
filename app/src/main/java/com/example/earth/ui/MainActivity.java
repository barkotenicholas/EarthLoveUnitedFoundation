package com.example.earth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.earth.R;

public class MainActivity extends AppCompatActivity {
    public MainActivity() {
        super(R.layout.activity_main);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.notificationFrameLayout, NotificationsFragment.class, null)
                    .commit();

    }}
}
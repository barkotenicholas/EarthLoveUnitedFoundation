package com.example.earth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView storiesBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    private void init(){
        storiesBar = findViewById(R.id.storiesrecycler);
    }
}
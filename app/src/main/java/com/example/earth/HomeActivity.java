package com.example.earth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.earth.Adapters.postAdapter;
import com.example.earth.Adapters.storiesAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView storiesBar;
    private RecyclerView postBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        onit();
    }


    private void init(){

        List<story> stories = new ArrayList<>();
        storiesBar =(RecyclerView)findViewById(R.id.storiesrecycler);
        stories.add(new story(false));
        stories.add(new story(true));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(true));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(true));
        storiesAdapter adapter = new storiesAdapter(stories, this);

        storiesBar.setAdapter(adapter);
        storiesBar.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        storiesBar.addItemDecoration(new storiesDecoration(10));
    }

    private void onit(){

        List<post> posts = new ArrayList<>();
        postBar =(RecyclerView)findViewById(R.id.postsrecycler);
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));
        posts.add(new post(false));

        postAdapter adapter = new postAdapter(posts, this);

        postBar.setAdapter(adapter);
        postBar.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }


}
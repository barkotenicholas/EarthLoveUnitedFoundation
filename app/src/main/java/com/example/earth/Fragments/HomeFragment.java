package com.example.earth.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.Adapters.postAdapter;
import com.example.earth.Adapters.storiesAdapter;
import com.example.earth.R;
import com.example.earth.models.post;
import com.example.earth.storiesDecoration;
import com.example.earth.models.story;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView storiesBar;
    private RecyclerView postBar;
    private List<post> posts;
    private List<story> stories;
    private storiesAdapter storiesAdapter;
    private postAdapter postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home2, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        storiesBar =view.findViewById(R.id.storiesrecycler);
        postBar =view.findViewById(R.id.postsrecycler);

        storiesBar.setHasFixedSize(true);
        postBar.setHasFixedSize(true);
        posts = new ArrayList<>();

        storiesBar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        stories = new ArrayList<>();
        storiesAdapter = new storiesAdapter(stories, getContext());
        storiesBar.setAdapter(storiesAdapter);
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

        storiesBar.addItemDecoration(new storiesDecoration(10));



        posts = new ArrayList<>();
        postAdapter = new postAdapter(posts, getContext());
        postBar.setAdapter(postAdapter);
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
        postBar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        return view;

    }




}
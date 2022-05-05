package com.example.earth.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.adapter.clubsAdapter;
import com.example.earth.adapter.postAdapter;
import com.example.earth.databinding.FragmentClubsBinding;
import com.example.earth.databinding.FragmentCommentsPostBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentsPostFragment extends Fragment {
FragmentCommentsPostBinding binding;
    RecyclerView dataList;
    com.example.earth.adapter.postAdapter postAdapter;
    List<String> posts;

    public CommentsPostFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=  FragmentCommentsPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        posts=new ArrayList<String>(Arrays.asList("#Environment","#Severe Weather","#Deforestation","#Polar Landscape","#Water Levels","#Forest Fire","#Deforestation","#Polar Landscape"));
        postAdapter=new postAdapter(getContext(), posts);
        dataList= binding.commentsRecycler;
        dataList.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataList.setAdapter( postAdapter);

    }
}
package com.example.earth.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.adapter.InterestsRecyclerviewAdapter;
import com.example.earth.adapter.photoPostAdapter;
import com.example.earth.databinding.FragmentInterestsBinding;
import com.example.earth.databinding.FragmentPhotoPostsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhotoPostsFragment extends Fragment {
FragmentPhotoPostsBinding binding;
    List<Integer> images;
    RecyclerView dataList;
    photoPostAdapter photoPostAdapter;
    public PhotoPostsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=  FragmentPhotoPostsBinding.inflate(inflater, container, false);
        return binding.getRoot();}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        images=new ArrayList<Integer>(Arrays.asList(R.drawable.contactsshare,R.drawable.girl,R.drawable.globe,R.drawable.logo,R.drawable.grey_round_buttons,R.drawable.loveunited,R.drawable.globe,R.drawable.logo));
        photoPostAdapter=new photoPostAdapter(getContext(),images);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
        dataList= binding.photoPostsRecycler;
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(photoPostAdapter);
    }
}
package com.example.earth.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.adapter.clubsAdapter;
import com.example.earth.databinding.FragmentClubsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClubsFragment extends Fragment {
    FragmentClubsBinding binding;
    RecyclerView dataList;
    clubsAdapter clubsAdapter;
    List<String> clubLabels;

    public ClubsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=  FragmentClubsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clubLabels=new ArrayList<String>(Arrays.asList("#Environment","#Severe Weather","#Deforestation","#Polar Landscape","#Water Levels","#Forest Fire","#Deforestation","#Polar Landscape"));
        clubsAdapter=new clubsAdapter(getContext(),clubLabels);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        dataList= binding.clubsRecycler;
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter( clubsAdapter);
        Log.d("before click","not yet clicked");
        binding.clubNotNow.setOnClickListener(view1 -> {


            FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.ClubsFrameLayout,new NotificationsFragment());
            transaction.commit();
            Toast.makeText(getContext(),"clicked",Toast.LENGTH_LONG).show();
            Log.d("after click","not yet clicked");

        });
        binding.clubNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.ClubsFrameLayout,new NotificationsFragment());
                transaction.commit();
                Log.d("after click","not yet clicked");
            }
        });
    }
}
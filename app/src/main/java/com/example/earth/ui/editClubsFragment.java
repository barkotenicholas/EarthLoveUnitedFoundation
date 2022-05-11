package com.example.earth.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.adapter.blackListAdapter;
import com.example.earth.adapter.checkAdapter;
import com.example.earth.databinding.ActivityDisplayProfileBinding;
import com.example.earth.databinding.FragmentEditClubsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class editClubsFragment extends Fragment {
    FragmentEditClubsBinding binding;
    List<String> clubsLists;
    RecyclerView clubsRecycler;
    checkAdapter clubsAdapter;
    public editClubsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     binding= FragmentEditClubsBinding.inflate(getLayoutInflater());
        clubsRecycler=binding.clubsRecycler;
        clubsLists=new ArrayList<String>(Arrays.asList("Environment","Severe Weather","Deforestation","Polar Landscape","Water Levels","Forest Fire","Deforestation"));
        clubsAdapter=new checkAdapter(getContext(),clubsLists);
        clubsRecycler.setAdapter( clubsAdapter);
        clubsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return binding.getRoot();}
}
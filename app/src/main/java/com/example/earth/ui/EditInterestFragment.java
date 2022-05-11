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
import com.example.earth.databinding.FragmentEditInterestBinding;
import com.example.earth.databinding.FragmentInterestsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditInterestFragment extends Fragment {
FragmentEditInterestBinding binding;
    List<String> interestsLists;
    RecyclerView interestsRecycler;
    checkAdapter interestsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentEditInterestBinding.inflate(inflater, container, false);
        interestsRecycler=binding.checkBoxRecycler;
        interestsLists=new ArrayList<String>(Arrays.asList("Severe Weather","Deforestation","Environment","Polar Landscape","Forest Fire","Deforestation","Water Levels"));
        interestsAdapter=new checkAdapter(getContext(),interestsLists);
        interestsRecycler.setAdapter( interestsAdapter);
        interestsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return binding.getRoot();}
    }

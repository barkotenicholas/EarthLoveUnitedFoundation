package com.example.earth.ui;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.adapter.InterestsRecyclerviewAdapter;
import com.example.earth.databinding.FragmentInterestsBinding;
import com.example.earth.models.profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterestsFragment extends Fragment {
    FragmentInterestsBinding binding;
    String sendUri;
    List<String> interestLabels;
    List<Integer>images;
    RecyclerView dataList;
    profile newProfile;
    InterestsRecyclerviewAdapter interestsRecyclerviewAdapter;
    public InterestsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInterestsBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            newProfile = (profile) getArguments().getSerializable("prof");
        }
        Fragment profileFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.createProfileFragment);
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        binding.next.setOnClickListener(view -> {


        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        interestLabels= new ArrayList<>(Arrays.asList("Environment", "Severe Weather", "Deforestation", "Polar Landscape", "Water Levels", "Forest Fire", "Deforestation", "Polar Landscape"));
        images= new ArrayList<>(Arrays.asList(R.drawable.contactsshare, R.drawable.girl, R.drawable.globe, R.drawable.logo, R.drawable.grey_round_buttons, R.drawable.loveunited, R.drawable.globe, R.drawable.logo));
        interestsRecyclerviewAdapter=new InterestsRecyclerviewAdapter(getContext(),images,interestLabels);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList= binding.interestRecyclerView;
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(interestsRecyclerviewAdapter);
        binding.notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.next.setOnClickListener(view1 -> {
            ArrayList<String> selected = interestsRecyclerviewAdapter.getSelectedLabels();

            newProfile.setInterests(selected);
            Bundle bundle = new Bundle();
            bundle.putSerializable("prof",newProfile);
            ClubsFragment clubsFragment = new ClubsFragment();
            clubsFragment.setArguments(bundle);
            FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.interestFrameLayout,clubsFragment);
            transaction.addToBackStack("CLUBS");
            transaction.commit();

        });

    }

}
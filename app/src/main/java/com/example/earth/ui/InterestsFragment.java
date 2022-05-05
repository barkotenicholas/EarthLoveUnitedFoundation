package com.example.earth.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.earth.models.User;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterestsFragment extends Fragment {
    FragmentInterestsBinding binding;
    String sendUri;
    List<String> interestLabels;
    List<Integer>images;
    RecyclerView dataList;
    User newUser;
    InterestsRecyclerviewAdapter interestsRecyclerviewAdapter;
    public InterestsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInterestsBinding.inflate(inflater, container, false);
//        Bundle bundle = this.getArguments();
//        newUser=bundle.getParcelable("newUser");
//        Bundle bundle1=new Bundle();
//        bundle1.putParcelable("newUser1", Parcels.wrap(newUser));
//        ClubsFragment ClubsFragment=new ClubsFragment();
//        ClubsFragment.setArguments(bundle1);

        Fragment profileFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.createProfileFragment);
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    return;
                }
            }
        });
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        interestLabels=new ArrayList<String>(Arrays.asList("Environment","Severe Weather","Deforestation","Polar Landscape","Water Levels","Forest Fire","Deforestation","Polar Landscape"));
        images=new ArrayList<Integer>(Arrays.asList(R.drawable.contactsshare,R.drawable.girl,R.drawable.globe,R.drawable.logo,R.drawable.grey_round_buttons,R.drawable.loveunited,R.drawable.globe,R.drawable.logo));
        interestsRecyclerviewAdapter=new InterestsRecyclerviewAdapter(getContext(),images,interestLabels);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList= binding.interestRecyclerView;
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(interestsRecyclerviewAdapter);
        binding.notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.interestFrameLayout,new ClubsFragment());
                transaction.addToBackStack("CLUBS");
                transaction.commit();

                Toast.makeText(getContext(),getContext().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
package com.example.earth.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.adapter.blackListAdapter;
import com.example.earth.databinding.FragmentEditBinding;
import com.example.earth.models.profile;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.Unit;

public class EditFragment extends Fragment {
FragmentEditBinding binding;
    RecyclerView interestsRecycler;
    RecyclerView clubsRecycler;
    List<String> interestsLists;
    List<String> clubsLists;
    blackListAdapter interestsAdapter;
    blackListAdapter clubsAdapter;
    Uri sendUri;
    Gson gson;
    profile userProfile;
    private SharedPreferences mSharedPreferences;
    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentEditBinding.inflate(getLayoutInflater());
        gson=new Gson();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userDetails=mSharedPreferences.getString("userDetails","");
        userProfile=gson.fromJson(userDetails,profile.class);
        Log.d("userName",userProfile.getName());
        System.out.println(userProfile.getName());
        binding.BirthdayEditText.setText(userProfile.getBirthday());
        binding.locationEditText.setText(userProfile.getLocation());
        binding.MyStoryEditText.setText(userProfile.getStory());
        binding.nameEditText.setText(userProfile.getName());
        binding.pronounsEditText.setText(userProfile.getPronoun());
        binding.WebsiteEditText.setText(userProfile.getWebsite());
        interestsRecycler=binding.interestsRecycler;
        clubsRecycler=binding.groupsRecycler;

        clubsLists=new ArrayList<String>(Arrays.asList("Environment","Severe Weather","Deforestation","Polar Landscape","Water Levels","Forest Fire","Deforestation"));
        clubsAdapter=new blackListAdapter(getContext(),clubsLists);
        clubsRecycler.setAdapter( clubsAdapter);
        clubsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        interestsLists=new ArrayList<String>(Arrays.asList("Severe Weather","Deforestation","Environment","Polar Landscape","Forest Fire","Deforestation","Water Levels"));
        interestsAdapter=new blackListAdapter(getContext(),interestsLists);
        interestsRecycler.setAdapter( interestsAdapter);
        interestsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
binding.editGroups.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.editFrameLayout,new EditInterestFragment());
        transaction.addToBackStack("editINTERESTS");
        transaction.commit();
    }
});
        binding.textInterestEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.editFrameLayout,new editClubsFragment());
                transaction.addToBackStack("editClubs");
                transaction.commit();
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(getActivity()).compress(1024).maxResultSize(1080,1080).createIntent(intent->{startForProfileImageResult.launch(intent);
                    return Unit.INSTANCE;});
            }
        });
        return binding.getRoot();}
        private ActivityResultLauncher startForProfileImageResult=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Uri uri=data.getData();
                            sendUri=uri;
                            binding.profilePhoto.setImageURI( uri);
                        }
                    }
                });
    }

package com.example.earth.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.databinding.FragmentInterestsBinding;
import com.example.earth.databinding.FragmentShareContactsBinding;


public class ShareContactsFragment extends Fragment {
FragmentShareContactsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentShareContactsBinding.inflate(inflater, container, false);
        binding.notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.shareContactsFrameLayout,new DisplayProfileFragment());
                transaction.commit();

            }
        });
        return binding.getRoot();}
}
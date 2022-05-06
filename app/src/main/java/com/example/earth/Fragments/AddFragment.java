package com.example.earth.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.earth.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.addpost, container, false);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_navigation);

        navBar.setVisibility(View.GONE);
        Spinner spino = view.findViewById(R.id.coursesspinner);

        List<String> courses = new ArrayList<>();
        courses.add(0,"Anyone");
        courses.add("Friends");
        ArrayAdapter ad
                = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                courses);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        spino.setAdapter(ad);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                navBar.setVisibility(View.VISIBLE);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container, new HomeFragment());
                ft.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        return view;
    }




}

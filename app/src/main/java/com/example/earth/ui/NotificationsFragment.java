package com.example.earth.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {
FragmentNotificationsBinding binding;
    public NotificationsFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
//        binding=FragmentNotificationsBinding.inflate(inflater, container, false);
//        binding.notNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ShareContactsFragment shareContactsFragment=new ShareContactsFragment();
//                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.notificationFrameLayout, shareContactsFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//        return binding.getRoot();}
      }
    }

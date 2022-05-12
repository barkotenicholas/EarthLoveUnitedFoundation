package com.example.earth.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.databinding.FragmentDisplayProfileBinding;
import com.example.earth.models.Follo;
import com.example.earth.models.profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DisplayProfileFragment extends Fragment {


    FragmentDisplayProfileBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    ArrayList<String> clubs = new ArrayList<>();
    ArrayList<String> interest = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDisplayProfileBinding.inflate(inflater, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("Profile").child("Clubs");
        databaseReference1 = firebaseDatabase.getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("Profile").child("Interest");


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading Profile....");
        progressDialog.show();
        readInterest();

        return binding.getRoot();
    }

    private void readClubs() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot a : snapshot.getChildren()) {
                    clubs.add(a.getValue(String.class));
                }
                addClubs();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        readProfile();

    }

    private void addClubs() {
        for (String name : clubs) {
            Button button = new Button(getContext());
            button.setBackgroundResource(R.drawable.green_rounded_corner);
            button.setTextColor(Color.WHITE);
            button.setText(name);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15,15,15,15);
            button.setLayoutParams(params);
            binding.ine.addView(button);
        }

    }

    private void readInterest() {

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot a : snapshot.getChildren()) {
                    interest.add(a.getValue(String.class));
                }
                addInterst();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        readClubs();
    }
    private void readProfile () {

        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                profile profile = dataSnapshot.getValue(profile.class);

                assert profile != null;

                addViews(profile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void addViews (profile profile){

        Picasso.get()
                .load(profile.getProfileImage())
                .into(binding.userImage);

        binding.newName.setText(profile.getName());
        binding.userStory.setText(profile.getStory());
        binding.textView7.setText("From " + profile.getLocation());

        progressDialog.dismiss();


    }
    private void addInterst() {
        for (String name : interest) {
            Button button = new Button(getContext());
            button.setBackgroundResource(R.drawable.green_rounded_corner);
            button.setTextColor(Color.WHITE);
            button.setText(name);
            button.setPadding(15,15,15,15);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15,15,15,15);
            button.setLayoutParams(params);

            binding.button13.addView(button);
        }



    }

}
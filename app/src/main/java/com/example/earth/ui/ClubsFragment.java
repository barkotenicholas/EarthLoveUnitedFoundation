package com.example.earth.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import com.example.earth.models.profile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClubsFragment extends Fragment {
    FragmentClubsBinding clubsBinding;
    RecyclerView dataList;
    clubsAdapter clubsAdapter;
    List<String> clubLabels;
    profile newProfile;
    ArrayList<String> clubs;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    StorageReference postrefrance;
    String url = "";
String uid;
    public ClubsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        clubsBinding=  FragmentClubsBinding.inflate(inflater, container, false);


        if (getArguments() != null) {
            newProfile = (profile) getArguments().getSerializable("prof");
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return clubsBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clubLabels= new ArrayList<>(Arrays.asList("#Environment", "#Severe Weather", "#Deforestation", "#Polar Landscape", "#Water Levels", "#Forest Fire", "#Deforestation", "#Polar Landscape"));
        clubsAdapter=new clubsAdapter(getContext(),clubLabels);
        postrefrance= FirebaseStorage.getInstance().getReference("profilepics");

        myRef =  FirebaseDatabase.getInstance().getReference();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        uid =FirebaseAuth.getInstance().getUid();
        dataList= clubsBinding.clubsRecycler;
        dataList.setLayoutManager(gridLayoutManager);
        newProfile.setUid(uid);

        dataList.setAdapter( clubsAdapter);
        Log.d("before click","not yet clicked");
        clubsBinding.clubNotNow.setOnClickListener(view1 -> {
             clubs = clubsAdapter.getSelectedClubs();
             newProfile.setClubs(clubs);
            Toast.makeText(getActivity(),"NotNow"+newProfile.getImageUri()+" "+clubs.size(),Toast.LENGTH_LONG).show();
            saveProfile();
        });
        clubsBinding.clubNext.setOnClickListener(view1 ->{

            Toast.makeText(getActivity(),"Next",Toast.LENGTH_LONG).show();

        });

    }

    private void saveProfile() {
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Uploading....");
        progressDialog.show();
        final String id=myRef.push().getKey();
        UploadTask uploadTask;
        StorageMetadata metadata=new StorageMetadata.Builder().setContentType("image/jpeg").build();
        uploadTask=postrefrance.child("rjha_"+id).putFile(newProfile.getImageUri(),metadata);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            url = uri.toString();
                            Map<String, Object> profile = new HashMap<>();
                            profile.put("Name",newProfile.getName());
                            profile.put("BirthDay", newProfile.getBirthday());
                            profile.put("uid", newProfile.getUid());
                            profile.put("Location", newProfile.getLocation());
                            profile.put("Story", newProfile.getStory());
                            profile.put("Pronoun", newProfile.getPronoun());
                            profile.put("WebSite", newProfile.getWebsite());
                            profile.put("ProfileImage", url);
                            profile.put("Interest", newProfile.getInterests());
                            profile.put("Clubs", newProfile.getClubs());
                            myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("Profile");

                            myRef.setValue(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), "Profile Added ", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
            }
        });



      //  myRef.setValue(users);

    }

}
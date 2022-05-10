package com.example.earth.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.earth.Adapters.postAdapter;
import com.example.earth.adapters.storiesAdapter;
import com.example.earth.R;
import com.example.earth.models.Posts;
import com.example.earth.models.post;
import com.example.earth.storiesDecoration;
import com.example.earth.models.story;
import com.example.earth.ui.LaunchActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView storiesBar;
    private RecyclerView postBar;
    private List<post> posts;
    private List<story> stories;
    private storiesAdapter storiesAdapter;
    private postAdapter postAdapter;
    AlertDialog.Builder builder;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Posts> list =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home2, container, false);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Posts");

        ImageView imageView = view.findViewById(R.id.imageView6);
        builder = new AlertDialog.Builder(getContext());

        storiesBar =view.findViewById(R.id.storiesrecycler);
        postBar =view.findViewById(R.id.postsrecycler);

        storiesBar.setHasFixedSize(true);
        postBar.setHasFixedSize(true);
        posts = new ArrayList<>();



        storiesBar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        stories = new ArrayList<>();
        storiesAdapter = new storiesAdapter(stories, getContext());
        storiesBar.setAdapter(storiesAdapter);
        stories.add(new story(false));
        stories.add(new story(true));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(true));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(false));
        stories.add(new story(true));

        storiesBar.addItemDecoration(new storiesDecoration(10));



        posts = new ArrayList<>();
        postAdapter = new postAdapter(list, getContext());
        postBar.setAdapter(postAdapter);

        postBar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Posts listdata=dataSnapshot1.getValue(Posts.class);
                    list.add(listdata);
                    Toast.makeText(getContext(),"We have found data"+listdata.getUrl(),Toast.LENGTH_SHORT).show();

                }
                postAdapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        imageView.setOnClickListener(view1 -> {
            builder.setMessage("Do you want to sign out") .setTitle("Sign Out");
            builder.setMessage("Do you want to close this application ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            FirebaseAuth.getInstance().signOut();

                            GoogleSignInClient mGoogleSignInClient;
                            GoogleSignIn.getClient(
                                    getContext(),
                                    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                            ).signOut();
                            Toast.makeText(getContext(),"you choose yes action for alertbox",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getContext(), LaunchActivity.class));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                            Toast.makeText(getContext(),"you choose no action for alertbox",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("AlertDialogExample");
            alert.show();
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                builder.setMessage("Do you want to Exit out") .setTitle("Exit application");
                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                startActivity(new Intent(getContext(), LaunchActivity.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getContext(),"you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("AlertDialogExample");
                alert.show();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        return view;

    }




}
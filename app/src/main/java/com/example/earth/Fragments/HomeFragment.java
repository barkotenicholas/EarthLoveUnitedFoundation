package com.example.earth.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.earth.Adapters.PostAdapters;
import com.example.earth.Adapters.storiesAdapter;
import com.example.earth.EditProfiles;
import com.example.earth.R;
import com.example.earth.models.Follo;
import com.example.earth.models.Posts;
import com.example.earth.models.post;
import com.example.earth.storiesDecoration;
import com.example.earth.models.story;
import com.example.earth.ui.CreateProfileFragment;
import com.example.earth.ui.DisplayProfileFragment;
import com.example.earth.ui.LaunchActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
    private PostAdapters postAdapter;
    AlertDialog.Builder builder;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Posts> list =new ArrayList<>();
    List<Follo> lista =new ArrayList<>();

    ImageView icon;
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

        setHasOptionsMenu(true);


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
        postAdapter = new PostAdapters(lista, getContext());
        postBar.setAdapter(postAdapter);

        postBar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        postBar.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        readPosts();

        imageView.setOnClickListener(view1 -> {

            PopupMenu popupMenu = new PopupMenu(getContext(), imageView);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    // Toast message on menu item clicked

                    if(menuItem.getItemId() == R.id.logout){
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
                    }
                    if(menuItem.getItemId() == R.id.profile){
                        startActivity(new Intent(getContext(), EditProfiles.class));
                    }
                    return true;
                }
            });
            // Showing the popup menu
            popupMenu.show();

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



    private void viewFragment(Fragment fragment, String name){


        FragmentTransaction ft = getFragmentManager().beginTransaction();


        ft.add(R.id.fragment_container, new CreateProfileFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }


    private void readPosts() {

        FirebaseDatabase.getInstance().getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Follo post = snapshot.getValue(Follo.class);


                            lista.add(post);

                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
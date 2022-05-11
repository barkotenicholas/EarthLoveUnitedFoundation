package com.example.earth.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.earth.R;
import com.example.earth.models.UploadPost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddFragment extends Fragment {
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLARY = 2;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private DatabaseReference mDatabase;
    StorageReference postrefrance;
    Uri uri;
    private TextInputEditText textInputEditText;
    private Button button;
    private TextView cardView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.addpost, container, false);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_navigation);

        navBar.setVisibility(View.GONE);
        Spinner spino = view.findViewById(R.id.coursesspinner);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        postrefrance= FirebaseStorage.getInstance().getReference("postpics");
        textInputEditText = view.findViewById(R.id.postid);
        button = view.findViewById(R.id.asas);
        cardView = view.findViewById(R.id.AddImage);

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



        cardView.setOnClickListener(view1 -> {
            pickIMage();
        });

        button.setOnClickListener(view12 -> {
            if (uri!=null)
            {
                upload();
            }

        });

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

    void pickIMage()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(Intent.createChooser(intent,"select image"),1002);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1002){
            try {
                uri=data.getData();
                Bitmap bm= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),data.getData());
               // profile_image.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
            }
        }
        //the end  onActivityResult
    }
    public void upload()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();
        final String description= textInputEditText.getText().toString();
        final String id=mDatabase.push().getKey();
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Uploading....");
        progressDialog.show();
        UploadTask uploadTask;
        StorageMetadata metadata=new StorageMetadata.Builder().setContentType("image/jpeg").build();
        uploadTask=postrefrance.child("rjha_"+id).putFile(uri,metadata);


        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                    String url= uri.toString();
                    final UploadPost uploadPost = new UploadPost(id, description,email,url);

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
                    String postId = ref.push().getKey();

                    HashMap<String , Object> map = new HashMap<>();
                    map.put("postid" , postId);
                    map.put("imageurl" , url);
                    map.put("description" , description);
                    map.put("publisher" , FirebaseAuth.getInstance().getCurrentUser().getUid());

                    ref.child(postId).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Post Added ", Toast.LENGTH_SHORT).show();
                        }
                    });
                  /*  mDatabase.child("Posts").child(id).setValue(uploadPost).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    });*/
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
       /* uploadTask.addOnSuccessListener(taskSnapshot -> {
            postrefrance.getDownloadUrl().addOnSuccessListener(uri1 -> {
                String imgurl = uri1.toString();
                final UploadPost uploadPost = new UploadPost(id, description,email,imgurl);
                mDatabase.child("Posts").child(id).setValue(uploadPost).addOnSuccessListener(aVoid -> {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Post Added ", Toast.LENGTH_SHORT).show();
                });
            });

        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
        });*/


    }


}

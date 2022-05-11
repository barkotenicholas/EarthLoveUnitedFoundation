package com.example.earth.ui;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earth.R;
import com.example.earth.databinding.FragmentCreateProfileBinding;
import com.example.earth.models.profile;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import kotlin.Unit;


public class CreateProfileFragment extends Fragment {
FragmentCreateProfileBinding binding;
    Uri sendUri;
ActivityResult activityResult;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
Gson gson;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentCreateProfileBinding.inflate(inflater, container, false);
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ImagePicker.with(getActivity()).compress(1024).maxResultSize(1080,1080).createIntent(intent->{startForProfileImageResult.launch(intent);
               return Unit.INSTANCE;});
            }
        });

        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.nameEditText.getText().toString();
                String birthday=binding.BirthdayEditText.getText().toString();
                gson=new Gson();
                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                mEditor=mSharedPreferences.edit();
                profile newUser=new profile(binding.nameEditText.getText().toString(),binding.BirthdayEditText.getText().toString(),binding.locationEditText.getText().toString(),binding.MyStoryEditText.getText().toString(),binding.pronounsEditText.getText().toString(),binding.WebsiteEditText.getText().toString(),sendUri);
                mEditor.putString("userDetails", gson.toJson(newUser)).commit();
                System.out.println(newUser.getName());
                FragmentTransaction transaction=requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.createProfileFragment,new InterestsFragment());
                transaction.addToBackStack("INTERESTS");
                transaction.commit();



//                Bundle bundle = new Bundle();
////                bundle.putString("Birthday",binding.BirthdayEditText.getText().toString());
////                bundle.putString("location",binding.locationEditText.getText().toString());
////                bundle.putString("MyStory",binding.MyStoryEditText.getText().toString());
////                bundle.putString("name",binding.nameEditText.getText().toString());
////                bundle.putString("pronouns",binding.pronounsEditText.getText().toString());
////                bundle.putString("Website",binding.WebsiteEditText.getText().toString());
////                bundle.putString("imageUri",sendUri);
//                bundle.putParcelable("newUser", Parcels.wrap(newUser));
//                InterestsFragment.setArguments(bundle);

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


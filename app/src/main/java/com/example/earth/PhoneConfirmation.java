package com.example.earth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.earth.models.UserProfile;

public class PhoneConfirmation extends AppCompatActivity {

    public static final String TAG ="TODAY";
    UserProfile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_confirmation);


        Intent intent = getIntent();

        userProfile = (UserProfile) intent.getSerializableExtra("PHONE");

        Log.d(TAG, "onCreate: "+userProfile.getEmail());
    }
}
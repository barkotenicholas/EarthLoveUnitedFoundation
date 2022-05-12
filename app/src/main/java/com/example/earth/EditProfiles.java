package com.example.earth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.earth.Fragments.AddFragment;
import com.example.earth.ui.CreateProfileFragment;

public class EditProfiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profiles);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        editFragment();
    }

    private void editFragment() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();


// Replace the contents of the container with the new fragment
        ft.add(R.id.pr, new CreateProfileFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }
}
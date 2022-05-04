package com.example.earth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.earth.databinding.ActivityPhoneBinding;
import com.example.earth.models.UserProfile;

public class ActivityPhone extends AppCompatActivity {

    ActivityPhoneBinding binding;
    UserProfile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        userProfile = (UserProfile) intent.getSerializableExtra("PROFILE");

        binding.ccp.registerCarrierNumberEditText(binding.phonenumber);

        binding.button2.setOnClickListener(view -> {
            if(valid()){
                String code = binding.ccp.getFullNumber();
                String phone =  binding.phonenumber.getText().toString().trim();
                String num = "+"+code+phone;

                userProfile.setPhone(num);
                Intent intent1 = new Intent(ActivityPhone.this,PhoneConfirmation.class);
                intent1.putExtra("PHONE",userProfile);

                startActivity(intent1);
            }
        });
    }

    private boolean valid() {

        String phonenumber = binding.phonenumber.getText().toString().trim();


        if(phonenumber.isEmpty()){
            binding.phonenumber.setError("please Input a phone number");
            return false;
        }else if(!phonenumber.matches("[0-9]+")){
            binding.phonenumber.setError("please Input a valid number");
            return false;
        }else if(!(phonenumber.length() == 9)){
            binding.phonenumber.setError("please Input a correct length");
            return false;
        }else {
            binding.phonenumber.setError(null);
            return true;
        }
    }
}
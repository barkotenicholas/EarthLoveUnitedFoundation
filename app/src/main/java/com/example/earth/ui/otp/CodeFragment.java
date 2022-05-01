package com.example.earth.ui.otp;

import static android.service.controls.ControlsProviderService.TAG;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.databinding.FragmentCodeBinding;
import com.example.earth.models.UserProfile;
import com.example.earth.ui.signup.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CodeFragment extends Fragment {

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    FragmentCodeBinding binding;
    private String mVerificationId;
    private static final String KEY_VERIFICATION_ID = "key_verification_id";
    ProgressDialog progressDoalog;
    UserProfile userProfile;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public CodeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCodeBinding.inflate(inflater,container,false);
        mAuth = FirebaseAuth.getInstance();

        userProfile = (UserProfile) getArguments().getSerializable("CODE");
        if (mVerificationId == null && savedInstanceState != null) {
            savedInstanceState.getString(KEY_VERIFICATION_ID);
        }
        if(userProfile != null){
            Toast.makeText(getContext(),"Sending sms",Toast.LENGTH_LONG).show();
          //signUp(userProfile);
        }


        binding.otpCodeNext.setOnClickListener(view -> {
            progressDoalog = new ProgressDialog(getContext());
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Its loading....");
            progressDoalog.setTitle("Button clicked");
            progressDoalog.show();
            createAccount();
            /*String code =binding.pinview.getText().toString();
            Log.d("TAG", "onCreateView: "+code+" "+code.length());
            if(!code.isEmpty()){
                if(code.length() == 6){

                    progressDoalog = new ProgressDialog(getContext());
                    progressDoalog.setMax(100);
                    progressDoalog.setMessage("Its loading....");
                    progressDoalog.setTitle("Button clicked");
                    progressDoalog.show();
                    verifyCode(code);
                }
            }*/
        });
        return binding.getRoot();
    }

    private void signUp(UserProfile userProfile) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(userProfile.getPhone())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity((Activity) getContext())                 // Activity (for callback binding)
                        .setCallbacks(getmCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks getmCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String c = phoneAuthCredential.getSmsCode();

            Toast.makeText(getContext(),"Code verified",Toast.LENGTH_LONG).show();
            progressDoalog.cancel();
            Log.d("code", "onVerificationCompleted: " + c);

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getContext(), "Error could not verify phone", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {

            Log.d("TAG", "onCodeSent:" + verificationId);

            mVerificationId  = verificationId;
            mResendToken = token;
        }
    };

    private void verifyCode(String c) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, c);

        signInWithCred(credential);
    }

    private void signInWithCred(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener((Activity) getContext(), task -> {

            if(task.isSuccessful()){
                Toast.makeText(getContext(), "Verified", Toast.LENGTH_SHORT).show();



            }else {
                Toast.makeText(getContext(), "Not Verified", Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void createAccount() {

        mAuth.createUserWithEmailAndPassword(userProfile.getEmail(), userProfile.getPass())
                .addOnCompleteListener((Activity) getContext(), (OnCompleteListener<AuthResult>) task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                        saveUsersInfo();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());


                    }
                });

    }

    private void saveUsersInfo() {
        FirebaseUser user = mAuth.getCurrentUser();

        Map<String, Object> users = new HashMap<>();
        users.put("Name",userProfile.getName());
        users.put("Email", userProfile.getEmail());
        users.put("Phone", userProfile.getPhone());
        myRef = database.getReference("Users").child(user.getUid());

        myRef.setValue(users);

        progressDoalog.cancel();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_VERIFICATION_ID, mVerificationId);
    }


}
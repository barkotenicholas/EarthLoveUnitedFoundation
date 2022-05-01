package com.example.earth.ui.login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.earth.MainActivity;
import com.example.earth.R;
import com.example.earth.ui.signup.ResetPassword;
import com.example.earth.databinding.ActivityLoginBinding;
import com.example.earth.ui.signup.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class ActivityLogin extends AppCompatActivity {

    ActivityLoginBinding binding;
    String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
    private GoogleSignInClient mGoogleSignInClient  ;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        createRequest();
        mAuth = FirebaseAuth.getInstance();


        binding.layLogin.imageView.setOnClickListener(view -> {
            signIn();
        });


        binding.layLogin.forgotpass.setOnClickListener(view -> {
            startActivity(new Intent(ActivityLogin.this, ResetPassword.class));
        });

        binding.signupbtn.setOnClickListener(view -> {
            startActivity(new Intent(ActivityLogin.this, SignUpActivity.class));
        });

        binding.layLogin.signinbutton.setOnClickListener(view -> {
                login();
        });

    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void login() {

        if(valid() && validatePass()){


        }

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();

                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        // Google Sign In was successful, authenticate with Firebase
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                        firebaseAuthWithGoogle(account.getIdToken());
                    } catch (ApiException e) {
                        // Google Sign In failed, update UI appropriately
                        Log.w(TAG, "Google sign in failed", e);
                    }

                }


            });


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ActivityLogin.this,"Google SignIn Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean valid() {

        String email  = binding.layLogin.Name.getText().toString().trim();

        if(email.isEmpty()){
            binding.layLogin.Name.setError("Please input email");
            return false;
        }else if(!email.matches(emailRegex)){
            binding.layLogin.Name.setError("Invalid Email!");
            binding.layLogin.Name.getText().clear();
            return false;
        }else {
            binding.layLogin.Name.setError(null);
            return true;
        }

    }

    public boolean validatePass(){
        String pass   = binding.layLogin.password.getText().toString().trim();
        if(pass.isEmpty()){
            binding.layLogin.password.setError("Please input password");
            binding.layLogin.password.getText().clear();
            return false;
        }else {
            binding.layLogin.password.setError(null);
            return true;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
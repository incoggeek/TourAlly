package com.app.tour_ally;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/*
Code to authenticate users
*/

public class LoginActivity extends AppCompatActivity {
    private Button mGoogleSingInBtn;
    private FirebaseAuth mAuth;
    private GoogleSignInOptions mGoogleSignInOptions;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);


        mGoogleSingInBtn = findViewById(R.id.google_signIn);
        mAuth = FirebaseAuth.getInstance();

        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                        .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions);


        mGoogleSingInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editorPref = sharedPreferences.edit();
                editorPref.putBoolean("flag", true);
                editorPref.apply();

                signIn();
            }
        });
    }

    private void signIn(){
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                funMainActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Navigating to MainActivity if user authenticates successfully
    private void funMainActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}

package com.app.tour_ally;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class ProfileActivity extends AppCompatActivity {
    GoogleSignInOptions mGoogleSignInOptions;
    GoogleSignInClient mGoogleSignInClient;
    TextView name, email;
    ImageView profileImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_drawer);


        name = findViewById(R.id.pName);
        email = findViewById(R.id.pEmail);
        profileImg = findViewById(R.id.pImage);

        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions);

        //To check for null account

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String pName = account.getDisplayName();
            String pEmail = account.getEmail();
            Uri pImage = account.getPhotoUrl();
            name.setText(pName);
            Log.d("prem", pName);

        }
    }
}

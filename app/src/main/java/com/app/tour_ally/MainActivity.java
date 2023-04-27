package com.app.tour_ally;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

/*
Code to navigate among different fragments when user clicks
*/
public class MainActivity extends AppCompatActivity {
    GoogleSignInOptions mGoogleSignInOptions;
    GoogleSignInClient mGoogleSignInClient;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar mToolbar;
    AddLocFragment mAddLocFragment;
    DefaultFragment mDefaultFragment;
    AboutFragment mAboutFragment;
    EssentialFragment mEssentialFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);

        //Toolbar set up
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        mDrawerLayout.setDrawerElevation(4.0f);
        toggle.syncState();

        //For default fragment to load
        loadFragment(new DefaultFragment(), 0);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    if(item.getItemId()==R.id.home) {
                        mDefaultFragment = new DefaultFragment();
                        loadFragment(mDefaultFragment, 1);
                    }

                    if (item.getItemId()==R.id.add_location) {
                        mAddLocFragment = new AddLocFragment();
                        loadFragment(mAddLocFragment, 1);
                    }

                    if (item.getItemId()==R.id.essential) {
                        mEssentialFragment = new EssentialFragment();
                        loadFragment(mEssentialFragment, 1);
                    }

                    if (item.getItemId()==R.id.about) {
                        mAboutFragment = new AboutFragment();
                        loadFragment(mAboutFragment, 1);
                    }

                    if (item.getItemId()==R.id.log_out) {
                        signOut();
                    }
                    
                return false;

            }
        });

        //To be done
        profile();
    }


    //Method to load different fragments at a time in fragment container
    private void loadFragment(Fragment fragment, int flag) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (flag == 0) {
            ft.add(R.id.fragment_container_id, fragment);
        } else {
            ft.replace(R.id.fragment_container_id, fragment);
        }
        ft.commit();

    }

    //Method to sign out
    private void signOut() {
        mGoogleSignInClient.signOut()

                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editorPref = sharedPreferences.edit();
                        editorPref.putBoolean("flag", false);
                        editorPref.apply();

                        Toast.makeText(MainActivity.this, "Signing out", Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
    }

    //To be done
    private void profile() {
        // Build the Google Sign-In options
        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions);

        // Find the header view in the navigation drawer
        View headerView = mNavigationView.getHeaderView(0);
        TextView name = headerView.findViewById(R.id.pName);
        TextView email = headerView.findViewById(R.id.pEmail);
        ImageView profileImg = headerView.findViewById(R.id.pImage);

        // Check if a user is signed in
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            // Set the user's name and email in the navigation drawer header
            name.setText(account.getDisplayName());
            email.setText(account.getEmail());

            // Set the user's profile image in the navigation drawer header using Picasso
            if (account.getPhotoUrl() != null) {
                String photoUrl = account.getPhotoUrl().toString();
                Picasso.get().load(photoUrl).resize(200,200).centerCrop().transform(new ProfileCircleTransformation()).error(R.drawable.default_avtar).into(profileImg, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("PROFILE", "Successfully loaded profile image");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("PROFILE", "Error loading profile image", e);
                    }
                });
            } else {
                Log.d("PROFILE", "User has no profile image");
            }
        } else {
            Log.d("PROFILE", "No user signed in");
        }
    }
}

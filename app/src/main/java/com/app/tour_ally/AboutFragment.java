package com.app.tour_ally;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/*
Code for 'About Us' section in drawer menu
*/
public class AboutFragment extends Fragment {
    private View mView;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.about_fragment_layout, container, false);

        socialDev();
        socialDesigner();

        return mView;
    }

    //Method for UI/UX designer
    private void socialDesigner() {

        ImageView linkedIn, dribble, insta, gmail;

        linkedIn = mView.findViewById(R.id.linkedin_gh);
        dribble = mView.findViewById(R.id.dribble);
        insta = mView.findViewById(R.id.instagram);
        gmail = mView.findViewById(R.id.gmail_gh);


        //LinkedIn
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/ghania-khan-826406195/"));
                intent.setPackage("com.linkedin.android");

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instagram not installed, open profile in default browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ghania-khan-826406195/")));
                }
            }
        });

        //Dribble

        dribble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(""));
                intent.setPackage("com.android.chrome");

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instagram not installed, open profile in default browser
                    //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse()));
                }
            }
        });

        //Instagram
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/" + "flaxen_gk");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.instagram.android");

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instagram not installed, open profile in default browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/" + "flaxen_gk")));
                }

            }
        });

        //Gmail
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + "khanghania@gmail.com"));
                intent.setPackage("com.google.android.gm");
                startActivity(intent);
            }
        });


    }

    //Method for App dev
    private void socialDev() {
        ImageView linkedIn, medium, github,gmail;

        linkedIn = mView.findViewById(R.id.linkedin_sh);
        medium = mView.findViewById(R.id.medium);
        github = mView.findViewById(R.id.github_sh);
        gmail = mView.findViewById(R.id.gmail_sh);

        //LinkedIn
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/mohd-shamim"));
                intent.setPackage("com.linkedin.android");


                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instagram not installed, open profile in default browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/mohd-shamim")));
                }
            }
        });

        //Medium

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://medium.com/@incog-geek"));
                intent.setPackage("com.medium.reader");

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instagram not installed, open profile in default browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://medium.com/@incog-geek")));
                }
            }
        });

        //Github
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/incoggeek"));
                intent.setPackage("com.github.android");

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instagram not installed, open profile in default browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/incoggeek")));
                }
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + "mohdshamim4312@gmail.com"));
                intent.setPackage("com.google.android.gm");
                startActivity(intent);
            }
        });

    }
}
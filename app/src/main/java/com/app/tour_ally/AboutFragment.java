package com.app.tour_ally;

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

        ImageView linkedIn, dribble, github;

        linkedIn = mView.findViewById(R.id.linkedin_gh);
        dribble = mView.findViewById(R.id.dribble);
        github = mView.findViewById(R.id.github_gh);

        //LinkedIn
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/ghania-khan-826406195/"));
                intent.setPackage("com.linkedin.android");
                startActivity(intent);
            }
        });

        //Dribble

        dribble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(""));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });

        //Github
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/incoggeek"));
                intent.setPackage("com.github.android");
                startActivity(intent);
            }
        });

    }

    //Method for App dev
    private void socialDev() {
        ImageView linkedIn, medium, github;

        linkedIn = mView.findViewById(R.id.linkedin_sh);
        medium = mView.findViewById(R.id.medium);
        github = mView.findViewById(R.id.github_sh);

        //LinkedIn
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/mohd-shamim"));
                intent.setPackage("com.linkedin.android");
                startActivity(intent);
            }
        });

        //Medium

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://medium.com/@incog-geek"));
                intent.setPackage("com.medium.reader");
                startActivity(intent);
            }
        });

        //Github
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/incoggeek"));
                intent.setPackage("com.github.android");
                startActivity(intent);
            }
        });

    }
}
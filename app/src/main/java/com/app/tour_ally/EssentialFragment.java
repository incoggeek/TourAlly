package com.app.tour_ally;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class EssentialFragment extends Fragment {
    private View mView;

    public EssentialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.essentials_layout, container, false);

        myPlanner();
        translator();
        compass();
        calendar();
        currencyConverter();
        weatherForecast();
        hireGuide();
        bookCab();

        return mView;
    }

    private void bookCab() {
        CardView mCardView;
        mCardView = mView.findViewById(R.id.travel_guide);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hireGuide() {
        CardView mCardView;
        mCardView = mView.findViewById(R.id.card_hire_guide);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void weatherForecast() {
        CardView mCardView;
        mCardView = mView.findViewById(R.id.card_weather_forecast);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void currencyConverter() {
        CardView mCardView;
        mCardView = mView.findViewById(R.id.card_curr_conv);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calendar() {
        CardView mCardView;
        mCardView = mView.findViewById(R.id.card_calendar);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void compass() {
        CardView mCardView;
        mCardView = mView.findViewById(R.id.card_compass);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void translator() {
        CardView mCardView;
        mCardView = mView.findViewById(R.id.card_translator);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void myPlanner() {

        CardView mCardView;
        mCardView = mView.findViewById(R.id.card_myplanner);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent MyPlanner
                Intent intentMyPlanner = new Intent(getContext(), MyPlannerActivity.class);
                startActivity(intentMyPlanner);
            }
        });

    }
}
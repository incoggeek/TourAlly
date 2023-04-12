package com.app.tour_ally;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyLocationAdapter extends ArrayAdapter<MyLocation> {

    public MyLocationAdapter(Activity context, ArrayList<MyLocation> locationArrayList) {
        super(context,0,locationArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView;

        if(convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.location_layout, parent, false);
        }
        else {
            listItemView = convertView;
        }

        MyLocation currentPlace = getItem(position);

        //Place Image
        ImageView imageView = listItemView.findViewById(R.id.location_img);
        imageView.setImageResource(currentPlace.getmLocationImageId());

        //Place Name
        TextView placeNameTextView = listItemView.findViewById(R.id.location_name);
        placeNameTextView.setText(currentPlace.getmLocationName());

        //Place Address
        TextView placeAddressTextView = listItemView.findViewById(R.id.location_address);
        placeAddressTextView.setText(currentPlace.getmAddress());
        
        return listItemView;

    }
}

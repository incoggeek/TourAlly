package com.app.tour_ally;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantsAdapter extends ArrayAdapter <MyLocation> {
    public RestaurantsAdapter(Activity context, ArrayList<MyLocation> citiesArrayList) {
        super(context,0, citiesArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView;

        if(convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.location_details_layout, parent, false);
        }
        else {
            listItemView = convertView;
        }

        MyLocation currentRestaurant = getItem(position);

        //Restaurant Image
        ImageView imageView = listItemView.findViewById(R.id.location_img);
        imageView.setImageResource(currentRestaurant.getmLocationImageId());

        //Restaurant Name
        TextView placeNameTextView = listItemView.findViewById(R.id.location_name);
        placeNameTextView.setText(currentRestaurant.getmLocationName());

        //Restaurant address
        TextView placeAddressTextView = listItemView.findViewById(R.id.location_address);
        placeAddressTextView.setText(currentRestaurant.getmAddress());

        return listItemView;

    }
}

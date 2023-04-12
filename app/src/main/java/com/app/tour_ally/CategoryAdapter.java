package com.app.tour_ally;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<MyLocation>  {

    public CategoryAdapter(Activity context, ArrayList<MyLocation> categoriesArrayList) {
        super(context,0, categoriesArrayList);
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

        MyLocation currentCategory = getItem(position);

        //Category Image
        ImageView imageView = listItemView.findViewById(R.id.location_img);
        imageView.setImageResource(currentCategory.getmLocationImageId());

        //Category Name
        TextView placeNameTextView = listItemView.findViewById(R.id.location_name);
        placeNameTextView.setText(currentCategory.getmLocationName());

        return listItemView;

    }
}

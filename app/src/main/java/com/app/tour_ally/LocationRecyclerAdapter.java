package com.app.tour_ally;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<String> myPlaceNameList;
    ArrayList<String> myPlaceAddressList;
    ArrayList<String> geoCodesList;


    public LocationRecyclerAdapter(Context context, ArrayList<String> myPlaceNameList,
                                   ArrayList<String> formattedAddresses, ArrayList<String> geoCodes) {
        this.context = context;
        this.myPlaceNameList = myPlaceNameList;
        this.myPlaceAddressList = formattedAddresses;
        this.geoCodesList = geoCodes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.location_details_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int temp_position = holder.getAdapterPosition();
        //holder.location_image.setImageResource(myLocationArrayList.get(position).);
        holder.location_name.setText(myPlaceNameList.get(temp_position));
        holder.location_address.setText(myPlaceAddressList.get(temp_position));

        //Navigate to google maps
        holder.direction_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String geocode = geoCodesList.get(temp_position);
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + geocode);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                Toast.makeText(context, "Here you go!", Toast.LENGTH_SHORT).show();
                try {
                    context.startActivity(mapIntent);
                } catch (ActivityNotFoundException e) {
                    // Google maps not installed, open profile in default browser
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query="+geocode)));
                }


            }
        });

        //Navigate to cab apps
        holder.cab_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                String pickUp = "";
                String geocode = geoCodesList.get(temp_position);
                Uri uberUri = Uri.parse("uber://?action=setPickup&pickup="+pickUp+"&dropoff[formatted_address]=" + geocode);
                Intent uberIntent = new Intent(Intent.ACTION_VIEW, uberUri);
                uberIntent.setPackage("com.ubercab");

                Toast.makeText(context, "Here you go!", Toast.LENGTH_SHORT).show();
                try {
                    context.startActivity(uberIntent);
                } catch (ActivityNotFoundException e) {
                    // Google maps not installed, open profile in default browser
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("uber://?action=setPickup&pickup="+pickUp+"&dropoff[formatted_address]=" + geocode)));
                }

                 */

                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return myPlaceNameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView location_image,direction_icon, cab_icon;
        TextView location_name, location_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location_image = itemView.findViewById(R.id.location_img);
            location_name = itemView.findViewById(R.id.location_name);
            location_address = itemView.findViewById(R.id.location_address);
            direction_icon = itemView.findViewById(R.id.direction_icon);
            cab_icon = itemView.findViewById(R.id.cab_icon);

        }
    }

}


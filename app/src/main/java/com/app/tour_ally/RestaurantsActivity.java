package com.app.tour_ally;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.catergory_layout);

        restaurantFunction();
    }

    private void restaurantFunction(){

        ArrayList<MyLocation> locationArrayList = new ArrayList<>();

        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 1","Jama Masjid"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 2","Tughlakabad"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 3","Okhla"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 4","Hamdard Nagar"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 5","Sarita Vihar"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 6","Tughlakabad"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 7","Jamia Nagar"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 8","Jashola Vihar"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 9","Tughlakabad"));
        locationArrayList.add(new MyLocation(R.drawable.restaurant_img,"Restaurants 10","Tughlakabad"));


        //RestaurantsAdapter

        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(this,locationArrayList);
        ListView locationListView = findViewById(R.id.list);
        locationListView.setAdapter(restaurantsAdapter);

    }

}

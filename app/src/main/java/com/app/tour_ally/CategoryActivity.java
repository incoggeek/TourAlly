package com.app.tour_ally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.catergory_layout);
        categoryFunction();
    }

    private void categoryFunction(){
        ArrayList<MyLocation> categoriesArrayList = new ArrayList<>();

        categoriesArrayList.add(new MyLocation(R.drawable.restaurant_img, "Hotels"));
        categoriesArrayList.add(new MyLocation(R.drawable.restaurant_img, "Restaurants"));
        categoriesArrayList.add(new MyLocation(R.drawable.restaurant_img, "Monuments"));
        categoriesArrayList.add(new MyLocation(R.drawable.restaurant_img, "Sightseeing"));
        categoriesArrayList.add(new MyLocation(R.drawable.restaurant_img, "Shopping"));
        categoriesArrayList.add(new MyLocation(R.drawable.restaurant_img, "Parks"));


        //StateActivityAdapter

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categoriesArrayList);
        ListView categoryListView = findViewById(R.id.list);
        categoryListView.setAdapter(categoryAdapter);

        //Setting OnItemClickListener to start Category activity

        ListView catergoryListView = findViewById(R.id.list);
        catergoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent catergoryIntent = new Intent(CategoryActivity.this, RestaurantsActivity.class);
                startActivity(catergoryIntent);

            }
        });

    }

}

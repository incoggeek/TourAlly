package com.app.tour_ally;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_recycler_layout);

        Intent intent = getIntent();
        String param1 = intent.getStringExtra("query");
        String param2 = intent.getStringExtra("near");


        RequestApi requestApi = new RequestApi(this);
        requestApi.execute(param1,param2);



    }


}

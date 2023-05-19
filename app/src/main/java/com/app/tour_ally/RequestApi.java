package com.app.tour_ally;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestApi extends AsyncTask<String, Void, JSONObject> {

    @SuppressLint("StaticFieldLeak")
    private final SearchActivity mActivity;

    public RequestApi(SearchActivity activity) {
        this.mActivity = activity;

    }


    @Override
    protected JSONObject doInBackground(String... params) {

        Request mRequest;
        Response mResponse;

        mRequest = new Request.Builder()
                .url("https://api.foursquare.com/v3/places/search?=&query=" + params[0] + "&near=" + params[1] + "&open_now=true&sort=DISTANCE"+"&limit=20")
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", mActivity.getString(R.string.api_key))
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        try {
            mResponse = client.newCall(mRequest).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseBody;
        try {
            assert mResponse.body() != null;
            responseBody = mResponse.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return new JSONObject(responseBody);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onPostExecute(JSONObject result) {
        String name;
        if (result != null) {
            try {

                JSONArray jsonArray = result.getJSONArray("results");

                ArrayList<String> names = new ArrayList<>();
                ArrayList<String> formattedAddresses = new ArrayList<>();
                ArrayList<String> geoCodes = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    name = jsonObject.getString("name");
                    names.add(name);

                    jsonObject.getJSONObject("location").optString("address", "");
                    String formattedAddress = jsonObject.getJSONObject("location").getString("formatted_address");
                    formattedAddresses.add(formattedAddress);

                    String latitude = jsonObject.getJSONObject("geocodes").getJSONObject("main").getString("latitude");
                    String longitude = jsonObject.getJSONObject("geocodes").getJSONObject("main").getString("longitude");

                    //Concatenation of geocodes
                    String geoCode = latitude + "," + longitude;
                    geoCodes.add(geoCode);
                    Log.d("prem", geoCodes.toString());


                    //Setting up recycler view to list search results
                    RecyclerView recyclerView = mActivity.findViewById(R.id.location_recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                    recyclerView.setAdapter(new LocationRecyclerAdapter(mActivity, names, formattedAddresses,geoCodes));

                }


            } catch (JSONException e) {

            }
        }
    }
}



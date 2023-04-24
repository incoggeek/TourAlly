package com.app.tour_ally;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
Code for default fragment.
It will load in the first place where user authenticates
*/
public class DefaultFragment extends Fragment {

    public DefaultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.default_fragment_layout, container, false);

        AutoCompleteTextView query = view.findViewById(R.id.query);
        ArrayAdapter<String> adapterQuery = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.auto_complete_query)
        );
        query.setAdapter(adapterQuery);


        AutoCompleteTextView near = view.findViewById(R.id.near);
        ArrayAdapter<String> adapterNear = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.array_near)
        );
        near.setAdapter(adapterNear);



        Button btn = view.findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String param1 = query.getText().toString();
                String param2 = near.getText().toString();

                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("query",param1);
                intent.putExtra("near",param2);
                startActivity(intent);
            }
        });

        return view;
    }

}
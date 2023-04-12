package com.app.tour_ally;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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

        // States spinner
        Spinner states_spinner = view.findViewById(R.id.states_spinner);
        // District spinner
        Spinner district_spinner = view.findViewById(R.id.district_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(getContext(), R.array.array_indian_states, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        states_spinner.setAdapter(statesAdapter);
        // Setting up listener
        states_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<CharSequence> districtAdapter = null;

                // Selected state value
                String selectedState = states_spinner.getSelectedItem().toString();

                int parentId = parent.getId();
                if (parentId == R.id.states_spinner) {

                    switch (selectedState) {
                        case "Select Your State":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_default_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Andhra Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_andhra_pradesh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Arunachal Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_arunachal_pradesh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Assam":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_assam_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Bihar":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_bihar_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Chhattisgarh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_chhattisgarh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Goa":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_goa_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Gujarat":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_gujarat_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Haryana":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_haryana_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Himachal Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_himachal_pradesh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Jharkhand":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_jharkhand_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Karnataka":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_karnataka_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Kerala":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_kerala_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Madhya Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_madhya_pradesh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Maharashtra":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_maharashtra_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Manipur":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_manipur_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Meghalaya":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_meghalaya_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Mizoram":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_mizoram_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Nagaland":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_nagaland_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Odisha":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_odisha_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Punjab":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_punjab_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Rajasthan":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_rajasthan_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Sikkim":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_sikkim_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Tamil Nadu":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_tamil_nadu_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Telangana":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_telangana_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Tripura":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_tripura_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Uttar Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_uttar_pradesh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Uttarakhand":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_uttarakhand_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "West Bengal":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_west_bengal_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Andaman and Nicobar Islands":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_andaman_nicobar_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Chandigarh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_chandigarh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Dadra and Nagar Haveli":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_dadra_nagar_haveli_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Daman and Diu":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_daman_diu_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Delhi":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_delhi_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Jammu and Kashmir":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_jammu_kashmir_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Lakshadweep":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_lakshadweep_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Ladakh":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_ladakh_districts, android.R.layout.simple_spinner_item);
                            break;
                        case "Puducherry":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_puducherry_districts, android.R.layout.simple_spinner_item);
                            break;
                        default:
                            break;
                    }
                }

                // Specify the layout to use when the list of choices appears
                if (districtAdapter != null) {
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }
                // Apply the adapter to the spinner
                district_spinner.setAdapter(districtAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // To search places, when user selects state and district

        Button btn = view.findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
package com.android.activelife.tampa.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.SelectBranchListAdapter;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.ui.SelectBranchActivity;
import com.android.activelife.tampa.util.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView mSelectBranchListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public LocationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationsFragment newInstance(String param1, String param2) {
        LocationsFragment fragment = new LocationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locations, container, false);
        mSelectBranchListView = (ListView) view.findViewById(R.id.select_branch_list);
        mSelectBranchListView.setAdapter(new SelectBranchListAdapter(getActivity()));
        mSelectBranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mainIntent.putExtra("title", "Location " + (i + 1));
                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getActivity().getApplicationContext(),Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME,"Location "+(i+1));
                startActivity(mainIntent);
                getActivity().finish();
            }
        });
        return view;
    }

}

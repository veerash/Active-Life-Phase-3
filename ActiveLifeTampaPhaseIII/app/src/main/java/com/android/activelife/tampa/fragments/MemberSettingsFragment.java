package com.android.activelife.tampa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.LocationsListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.DefaultLocationData;
import com.android.activelife.tampa.db.LocationsData;
import com.android.activelife.tampa.services.request.ApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberSettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner mLocationSpinner;
    private ApiRequest mApiInterface;
    private List<LocationsData> mLocationDataResponsesList;

    public MemberSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemberSettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberSettingsFragment newInstance(String param1, String param2) {
        MemberSettingsFragment fragment = new MemberSettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_member_settings, container, false);
        mLocationSpinner = (Spinner) view.findViewById(R.id.default_location_spinner);
        mLocationDataResponsesList=new ArrayList<>();
        mLocationDataResponsesList = ActiveLifeApplication.getInstance().setUpDb().getLocations();
        mLocationSpinner.setAdapter(new LocationsListAdapter(getActivity(), mLocationDataResponsesList));
        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                ActiveLifeApplication.getInstance().setUpDb().deleteDefaultLocations();
                ActiveLifeApplication.getInstance().setUpDb().insertDefaultLocation(i, "" + mLocationDataResponsesList.get(i).getId(), mLocationDataResponsesList.get(i).getLocation_name(), mLocationDataResponsesList.get(i).getLocation_address(), mLocationDataResponsesList.get(i).getLocation_city(), mLocationDataResponsesList.get(i).getLocation_state(), mLocationDataResponsesList.get(i).getLocation_zip(), mLocationDataResponsesList.get(i).getLocation_phone(), mLocationDataResponsesList.get(i).getLocation_email(), mLocationDataResponsesList.get(i).getLocation_program_link(), mLocationDataResponsesList.get(i).getLocation_donate_link());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<DefaultLocationData> date = ActiveLifeApplication.getInstance().setUpDb().getDefaultLocations();
        if (date != null && date.size() > 0) {
            mLocationSpinner.setSelection(date.get(0).getPostion());
        }
        return view;
    }


}

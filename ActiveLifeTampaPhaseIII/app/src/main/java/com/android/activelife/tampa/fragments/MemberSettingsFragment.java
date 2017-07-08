package com.android.activelife.tampa.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.LocationsListAdapter;
import com.android.activelife.tampa.adpater.SelectBranchListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.DefaultLocationData;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.LocationData.Hour;
import com.android.activelife.tampa.services.response.LocationData.LocationDataResponse;
import com.android.activelife.tampa.services.response.LocationData.Times;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.util.Utilities;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private List<LocationDataResponse> mLocationDataResponsesList;

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
        View view=inflater.inflate(R.layout.fragment_member_settings, container, false);
        mLocationSpinner = (Spinner) view.findViewById(R.id.default_location_spinner);
        getAllLocationsData();
        return view;
    }


    public void getAllLocationsData() {
        if (((MainActivity)getActivity()).checkIfInternet(getActivity())) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<LocationDataResponse>> call = mApiInterface.getLocations();
            call.enqueue(new Callback<List<LocationDataResponse>>() {
                @Override
                public void onResponse(Call<List<LocationDataResponse>> call, Response<List<LocationDataResponse>> response) {
                    ((MainActivity)getActivity()).hideProgressDialog(getActivity());
                    if (response.isSuccessful()) {
                        mLocationDataResponsesList = response.body();
                        mLocationSpinner.setAdapter(new LocationsListAdapter(getActivity(),mLocationDataResponsesList));
                        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                                ActiveLifeApplication.getInstance().setUpDb().deleteDefaultLocations();
                                ActiveLifeApplication.getInstance().setUpDb().insertDefaultLocation(i,""+mLocationDataResponsesList.get(i).getId(),mLocationDataResponsesList.get(i).getName(), mLocationDataResponsesList.get(i).getAddress(),mLocationDataResponsesList.get(i).getCity(),mLocationDataResponsesList.get(i).getState(),mLocationDataResponsesList.get(i).getZipCode(),mLocationDataResponsesList.get(i).getPhone(),mLocationDataResponsesList.get(i).getEmail(),mLocationDataResponsesList.get(i).getProgramLink(),mLocationDataResponsesList.get(i).getDonationLink());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        List<DefaultLocationData> date=ActiveLifeApplication.getInstance().setUpDb().getDefaultLocations();
                        if(date!=null&&date.size()>0){
                            mLocationSpinner.setSelection(date.get(0).getPostion());
                        }
                    } else {
                        if (response.errorBody() != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Utilities.showToast(getActivity(), "" + jsonObject.getString("message"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }


                    }

                }

                @Override
                public void onFailure(Call<List<LocationDataResponse>> call, Throwable t) {
                    ((MainActivity)getActivity()).hideProgressDialog(getActivity());
                }
            });
            ((MainActivity)getActivity()).showProgressDialog(getActivity());
        }
    }

}

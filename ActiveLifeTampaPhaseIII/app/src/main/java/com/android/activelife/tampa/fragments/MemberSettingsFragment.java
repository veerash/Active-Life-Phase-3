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
import com.android.activelife.tampa.adpater.LocationSelectAdapter;
import com.android.activelife.tampa.adpater.LocationsListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.DefaultLocationData;
import com.android.activelife.tampa.db.LocationsData;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.LocationData.Hour;
import com.android.activelife.tampa.services.response.LocationData.LocationDataResponse;
import com.android.activelife.tampa.services.response.LocationData.Times;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.util.Utilities;

import org.json.JSONObject;

import java.util.ArrayList;
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
        View view = inflater.inflate(R.layout.fragment_member_settings, container, false);
        mLocationSpinner = (Spinner) view.findViewById(R.id.default_location_spinner);
        mLocationDataResponsesList = new ArrayList<>();
        getAllLocationsData();

        return view;
    }

    public void getAllLocationsData() {
        if (((MainActivity) getActivity()).checkIfInternet(getActivity())) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<LocationDataResponse>> call = mApiInterface.getLocations();
            call.enqueue(new Callback<List<LocationDataResponse>>() {
                @Override
                public void onResponse(Call<List<LocationDataResponse>> call, Response<List<LocationDataResponse>> response) {
                    ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                    final List<DefaultLocationData> date = ActiveLifeApplication.getInstance().setUpDb().getDefaultLocations();
                    if (response.isSuccessful()) {
                        mLocationDataResponsesList = response.body();
                        mLocationSpinner.setAdapter(new LocationsListAdapter(getActivity(), mLocationDataResponsesList));
                        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (date.get(0).getPostion() != position) {
                                    Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                                    mainIntent.putExtra("title", mLocationDataResponsesList.get(position).getName());
                                    ActiveLifeApplication.getInstance().setUpDb().deleteHours();
                                    for (int j = 0; j < mLocationDataResponsesList.get(position).getHours().size(); j++) {
                                        Hour object = mLocationDataResponsesList.get(position).getHours().get(j);
                                        String name = object.getName();
                                        Times times = object.getTimes();
                                        String monStartTime = times.get1().get(0).getStartTime();
                                        String monEndTime = times.get1().get(0).getEndTime();
                                        String tueStartTime = times.get2().get(0).getStartTime();
                                        String tueEndTime = times.get2().get(0).getEndTime();
                                        String wedStartTime = times.get3().get(0).getStartTime();
                                        String wedEndTime = times.get3().get(0).getEndTime();
                                        String thuStartTime = times.get4().get(0).getStartTime();
                                        String thuEndTime = times.get4().get(0).getEndTime();
                                        String friStartTime = times.get5().get(0).getStartTime();
                                        String friEndTime = times.get5().get(0).getEndTime();
                                        String satStartTime = times.get6().get(0).getStartTime();
                                        String satEndTime = times.get6().get(0).getEndTime();
                                        String sunStartTime = times.get7().get(0).getStartTime();
                                        String sunEndTime = times.get7().get(0).getEndTime();
                                        ActiveLifeApplication.getInstance().setUpDb().insertHoursDao(name, monStartTime, monEndTime, tueStartTime, tueEndTime, wedStartTime, wedEndTime, thuStartTime, thuEndTime, friStartTime, friEndTime, satStartTime, satEndTime, sunStartTime, sunEndTime);
                                    }
                                    getActivity().startActivity(mainIntent);
                                    ActiveLifeApplication.getInstance().setUpDb().deleteDefaultLocations();
                                    ActiveLifeApplication.getInstance().setUpDb().insertDefaultLocation(position, "" + mLocationDataResponsesList.get(position).getId(), mLocationDataResponsesList.get(position).getName(), mLocationDataResponsesList.get(position).getAddress(), mLocationDataResponsesList.get(position).getCity(), mLocationDataResponsesList.get(position).getState(), mLocationDataResponsesList.get(position).getZipCode(), mLocationDataResponsesList.get(position).getPhone(), mLocationDataResponsesList.get(position).getEmail(), mLocationDataResponsesList.get(position).getProgramLink(), mLocationDataResponsesList.get(position).getDonationLink());
                                    ActiveLifeApplication.getInstance().setUpDb().deleteLocation();
                                    ActiveLifeApplication.getInstance().setUpDb().insertLocation(position, "" + mLocationDataResponsesList.get(position).getId(), mLocationDataResponsesList.get(position).getName(), mLocationDataResponsesList.get(position).getAddress(), mLocationDataResponsesList.get(position).getCity(), mLocationDataResponsesList.get(position).getState(), mLocationDataResponsesList.get(position).getZipCode(), mLocationDataResponsesList.get(position).getPhone(), mLocationDataResponsesList.get(position).getEmail(), mLocationDataResponsesList.get(position).getProgramLink(), mLocationDataResponsesList.get(position).getDonationLink());
                                    Utilities.getSharedPrefernceData().storeIntValueIntoSharedPreference(getActivity().getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_ID, mLocationDataResponsesList.get(position).getId());
                                    Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getActivity().getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME, mLocationDataResponsesList.get(position).getName());
                                    Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getActivity().getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_PROGRAM_LINK, mLocationDataResponsesList.get(position).getProgramLink());
                                    Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getActivity().getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_DONATE_LINK, mLocationDataResponsesList.get(position).getDonationLink());

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        if (date != null && date.size() > 0) {
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
                    ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                }
            });
            ((MainActivity) getActivity()).showProgressDialog(getActivity());
        }
    }

}

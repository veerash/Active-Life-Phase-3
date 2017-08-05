package com.android.activelife.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.activelife.R;
import com.android.activelife.adpater.LocationSelectAdapter;
import com.android.activelife.adpater.LocationsListAdapter;
import com.android.activelife.appcontroller.ActiveLifeApplication;
import com.android.activelife.db.DefaultLocationData;
import com.android.activelife.db.LocationsData;
import com.android.activelife.services.request.ApiRequest;
import com.android.activelife.services.response.LocationData.Hour;
import com.android.activelife.services.response.LocationData.LocationDataResponse;
import com.android.activelife.services.response.LocationData.Times;
import com.android.activelife.ui.MainActivity;
import com.android.activelife.util.Utilities;

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
                                        String monStartTime = null;
                                        List<com.android.activelife.services.response.LocationData._1> mondayList = times.get1();
                                        for (int mon = 0; mon < mondayList.size(); mon++) {
                                            if (monStartTime != null && monStartTime.length() > 0)
                                                monStartTime = monStartTime+"\n"+mondayList.get(mon).getStartTime() + " - " + mondayList.get(mon).getEndTime();
                                            else
                                                monStartTime = mondayList.get(mon).getStartTime() + " - " + mondayList.get(mon).getEndTime();
                                        }
                                        String tueStartTime = null;
                                        List<com.android.activelife.services.response.LocationData._2> tuesdayList = times.get2();
                                        for (int tue = 0; tue < tuesdayList.size(); tue++) {
                                            if (tueStartTime != null && tueStartTime.length() > 0)
                                                tueStartTime = tueStartTime+"\n"+tuesdayList.get(tue).getStartTime() + " - " + tuesdayList.get(tue).getEndTime();
                                            else
                                                tueStartTime = tuesdayList.get(tue).getStartTime() + " - " + tuesdayList.get(tue).getEndTime();
                                        }
                                        String wedStartTime = null;
                                        List<com.android.activelife.services.response.LocationData._3> wednesdayList = times.get3();
                                        for (int wed = 0; wed < wednesdayList.size(); wed++) {
                                            if (wedStartTime != null && wedStartTime.length() > 0)
                                                wedStartTime = wedStartTime+"\n"+wednesdayList.get(wed).getStartTime() + " - " + wednesdayList.get(wed).getEndTime();
                                            else
                                                wedStartTime = wednesdayList.get(wed).getStartTime() + " - " + wednesdayList.get(wed).getEndTime();
                                        }
                                        String thuStartTime = null;
                                        List<com.android.activelife.services.response.LocationData._4> thursdayList = times.get4();
                                        for (int thu = 0; thu < thursdayList.size(); thu++) {
                                            if (thuStartTime != null && thuStartTime.length() > 0)
                                                thuStartTime = thuStartTime+"\n"+thursdayList.get(thu).getStartTime() + " - " + thursdayList.get(thu).getEndTime();
                                            else
                                                thuStartTime = thursdayList.get(thu).getStartTime() + " - " + thursdayList.get(thu).getEndTime();
                                        }
                                        String friStartTime = null;
                                        List<com.android.activelife.services.response.LocationData._5> fridayList = times.get5();
                                        for (int fri = 0; fri < fridayList.size(); fri++) {
                                            if (friStartTime != null && friStartTime.length() > 0)
                                                friStartTime = friStartTime+"\n"+fridayList.get(fri).getStartTime() + " - " + fridayList.get(fri).getEndTime();
                                            else
                                                friStartTime = fridayList.get(fri).getStartTime() + " - " + fridayList.get(fri).getEndTime();
                                        }
                                        String satStartTime = null;
                                        List<com.android.activelife.services.response.LocationData._6> saturdayList = times.get6();
                                        for (int sat = 0; sat < saturdayList.size(); sat++) {
                                            if (satStartTime != null && satStartTime.length() > 0)
                                                satStartTime = satStartTime+"\n"+saturdayList.get(sat).getStartTime() + " - " + saturdayList.get(sat).getEndTime();
                                            else
                                                satStartTime = saturdayList.get(sat).getStartTime() + " - " + saturdayList.get(sat).getEndTime();
                                        }
                                        String sunStartTime = null;
                                        List<com.android.activelife.services.response.LocationData._7> sundayList = times.get7();
                                        for (int sun = 0; sun < sundayList.size(); sun++) {
                                            if (sunStartTime != null && sunStartTime.length() > 0)
                                                sunStartTime = sunStartTime+"\n"+sundayList.get(sun).getStartTime() + " - " + sundayList.get(sun).getEndTime();
                                            else
                                                sunStartTime = sundayList.get(sun).getStartTime() + " - " + sundayList.get(sun).getEndTime();
                                        }
                                        ActiveLifeApplication.getInstance().setUpDb().insertHoursDao(name, monStartTime, null, tueStartTime, null, wedStartTime, null, thuStartTime, null, friStartTime, null, satStartTime, null, sunStartTime, null);

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

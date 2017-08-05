package com.android.activelife.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.activelife.R;
import com.android.activelife.adpater.SelectBranchListAdapter;
import com.android.activelife.appcontroller.ActiveLifeApplication;
import com.android.activelife.db.LocationsData;
import com.android.activelife.services.request.ApiRequest;
import com.android.activelife.services.response.LocationData.Hour;
import com.android.activelife.services.response.LocationData.LocationDataResponse;
import com.android.activelife.services.response.LocationData.Times;
import com.android.activelife.util.TypefaceSpan;
import com.android.activelife.util.Utilities;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectBranchActivity extends BaseActivity {

    private ListView mSelectBranchListView;
    private ApiRequest mApiInterface;
    private List<LocationDataResponse> mLocationDataResponsesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_branch);
        mSelectBranchListView = (ListView) findViewById(R.id.select_branch_list);
        SpannableString s = new SpannableString("Find Your Y");
        s.setSpan(new TypefaceSpan(this, "Verdana.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setTitle(s);
        getAllLocationsData();

    }

    public void getAllLocationsData() {
        if (checkIfInternet(SelectBranchActivity.this)) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<LocationDataResponse>> call = mApiInterface.getLocations();
            call.enqueue(new Callback<List<LocationDataResponse>>() {
                @Override
                public void onResponse(Call<List<LocationDataResponse>> call, Response<List<LocationDataResponse>> response) {
                    hideProgressDialog(SelectBranchActivity.this);
                    if (response.isSuccessful()) {
                        mLocationDataResponsesList = response.body();
                        ArrayList<LocationsData> datas = new ArrayList<LocationsData>();
                        for (int k = 0; k < mLocationDataResponsesList.size(); k++) {
                            LocationsData ld = new LocationsData();
                            ld.setLocation_id("" + mLocationDataResponsesList.get(k).getId());
                            ld.setLocation_address("" + mLocationDataResponsesList.get(k).getAddress());
                            ld.setLocation_city("" + mLocationDataResponsesList.get(k).getCity());
                            ld.setLocation_donate_link("" + mLocationDataResponsesList.get(k).getDonationLink());
                            ld.setLocation_email("" + mLocationDataResponsesList.get(k).getEmail());
                            ld.setLocation_name("" + mLocationDataResponsesList.get(k).getName());
                            ld.setLocation_phone("" + mLocationDataResponsesList.get(k).getPhone());
                            ld.setLocation_program_link("" + mLocationDataResponsesList.get(k).getProgramLink());
                            ld.setLocation_state("" + mLocationDataResponsesList.get(k).getState());
                            ld.setLocation_zip("" + mLocationDataResponsesList.get(k).getZipCode());
                            datas.add(ld);

                        }
                        ActiveLifeApplication.getInstance().setUpDb().deleteLocations();
                        ActiveLifeApplication.getInstance().setUpDb().insertLocations(datas);
                        mSelectBranchListView.setAdapter(new SelectBranchListAdapter(SelectBranchActivity.this, mLocationDataResponsesList));
                        mSelectBranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent mainIntent = new Intent(SelectBranchActivity.this, MainActivity.class);
                                mainIntent.putExtra("title", mLocationDataResponsesList.get(i).getName());
                                ActiveLifeApplication.getInstance().setUpDb().deleteHours();
                                for (int j = 0; j < mLocationDataResponsesList.get(i).getHours().size(); j++) {
                                    Hour object = mLocationDataResponsesList.get(i).getHours().get(j);
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

                                startActivity(mainIntent);
                                ActiveLifeApplication.getInstance().setUpDb().deleteDefaultLocations();
                                ActiveLifeApplication.getInstance().setUpDb().insertDefaultLocation(i, "" + mLocationDataResponsesList.get(i).getId(), mLocationDataResponsesList.get(i).getName(), mLocationDataResponsesList.get(i).getAddress(), mLocationDataResponsesList.get(i).getCity(), mLocationDataResponsesList.get(i).getState(), mLocationDataResponsesList.get(i).getZipCode(), mLocationDataResponsesList.get(i).getPhone(), mLocationDataResponsesList.get(i).getEmail(), mLocationDataResponsesList.get(i).getProgramLink(), mLocationDataResponsesList.get(i).getDonationLink());
                                ActiveLifeApplication.getInstance().setUpDb().deleteLocation();
                                ActiveLifeApplication.getInstance().setUpDb().insertLocation(i, "" + mLocationDataResponsesList.get(i).getId(), mLocationDataResponsesList.get(i).getName(), mLocationDataResponsesList.get(i).getAddress(), mLocationDataResponsesList.get(i).getCity(), mLocationDataResponsesList.get(i).getState(), mLocationDataResponsesList.get(i).getZipCode(), mLocationDataResponsesList.get(i).getPhone(), mLocationDataResponsesList.get(i).getEmail(), mLocationDataResponsesList.get(i).getProgramLink(), mLocationDataResponsesList.get(i).getDonationLink());
                                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME, mLocationDataResponsesList.get(i).getName());
                                Utilities.getSharedPrefernceData().storeIntValueIntoSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_ID, mLocationDataResponsesList.get(i).getId());
                                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_PROGRAM_LINK, mLocationDataResponsesList.get(i).getProgramLink());
                                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_DONATE_LINK, mLocationDataResponsesList.get(i).getDonationLink());

                                finish();
                            }
                        });
                    } else {
                        if (response.errorBody() != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Utilities.showToast(SelectBranchActivity.this, "" + jsonObject.getString("message"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }


                    }

                }

                @Override
                public void onFailure(Call<List<LocationDataResponse>> call, Throwable t) {
                    hideProgressDialog(SelectBranchActivity.this);
                }
            });
            showProgressDialog(SelectBranchActivity.this);
        }
    }
}

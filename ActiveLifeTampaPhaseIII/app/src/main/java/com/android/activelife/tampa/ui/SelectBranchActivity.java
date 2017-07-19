package com.android.activelife.tampa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.SelectBranchListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.LocationsData;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.LocationData.Hour;
import com.android.activelife.tampa.services.response.LocationData.LocationDataResponse;
import com.android.activelife.tampa.services.response.LocationData.Times;
import com.android.activelife.tampa.util.TypefaceSpan;
import com.android.activelife.tampa.util.Utilities;

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

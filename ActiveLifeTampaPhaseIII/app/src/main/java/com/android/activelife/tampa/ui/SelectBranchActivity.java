package com.android.activelife.tampa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.SelectBranchListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.LocationData.Hour;
import com.android.activelife.tampa.services.response.LocationData.LocationDataResponse;
import com.android.activelife.tampa.services.response.LocationData.Times;
import com.android.activelife.tampa.util.Utilities;

import org.json.JSONObject;

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
                        mSelectBranchListView.setAdapter(new SelectBranchListAdapter(SelectBranchActivity.this, mLocationDataResponsesList));
                        mSelectBranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent mainIntent = new Intent(SelectBranchActivity.this, MainActivity.class);
                                mainIntent.putExtra("title", mLocationDataResponsesList.get(i).getName());
                                ActiveLifeApplication.getInstance().setUpDb().deleteHours();
                                for (int j = 0; i < mLocationDataResponsesList.get(i).getHours().size(); j++) {
                                    Hour object = mLocationDataResponsesList.get(i).getHours().get(j);
                                    String name = object.getName();
                                    Times times = object.getTimes();
                                    String monStartTime = times.get1().getStartTime();
                                    String monEndTime = times.get1().getEndTime();
                                    String tueStartTime = times.get2().getStartTime();
                                    String tueEndTime = times.get2().getEndTime();
                                    String wedStartTime = times.get3().getStartTime();
                                    String wedEndTime = times.get3().getEndTime();
                                    String thuStartTime = times.get4().getStartTime();
                                    String thuEndTime = times.get4().getEndTime();
                                    String friStartTime = times.get5().getStartTime();
                                    String friEndTime = times.get5().getEndTime();
                                    String satStartTime = times.get6().getStartTime();
                                    String satEndTime = times.get6().getEndTime();
                                    String sunStartTime = times.get7().getStartTime();
                                    String sunEndTime = times.get7().getEndTime();
                                    ActiveLifeApplication.getInstance().setUpDb().insertHoursDao(name, monStartTime, monEndTime, tueStartTime, tueEndTime, wedStartTime, wedEndTime, thuStartTime, thuEndTime, friStartTime, friEndTime, satStartTime, satEndTime, sunStartTime, sunEndTime);
                                }

                                startActivity(mainIntent);
                                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME, mLocationDataResponsesList.get(i).getName());
                                Utilities.getSharedPrefernceData().storeIntValueIntoSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_ID, mLocationDataResponsesList.get(i).getId());
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

                }
            });
            showProgressDialog(SelectBranchActivity.this);
        }
    }
}

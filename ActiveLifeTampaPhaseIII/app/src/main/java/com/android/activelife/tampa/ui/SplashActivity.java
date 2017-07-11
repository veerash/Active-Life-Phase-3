package com.android.activelife.tampa.ui;

import android.content.Intent;
import android.os.Bundle;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.LocationsData;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.LocationData.LocationDataResponse;
import com.android.activelife.tampa.services.response.classdata.ClassDataResponse;
import com.android.activelife.tampa.services.response.instructordata.InstructorDataResponse;
import com.android.activelife.tampa.services.response.schedulesdata.SchedulesDataResponse;
import com.android.activelife.tampa.util.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private ApiRequest mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Select Branch Activity. */
//
//            }
//        }, SPLASH_DISPLAY_LENGTH);

        getAllClasses();
    }


    public void getAllClasses() {
        if (checkIfInternet(SplashActivity.this)) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<ClassDataResponse>> call = mApiInterface.getClasses();
            call.enqueue(new Callback<List<ClassDataResponse>>() {
                @Override
                public void onResponse(Call<List<ClassDataResponse>> call, Response<List<ClassDataResponse>> response) {
                    getAllSchedules();
                    if (response.isSuccessful()) {
                        ActiveLifeApplication.getInstance().setUpDb().deleteClass();
                        List<ClassDataResponse> cdr = response.body();
                        for (int i = 0; i < cdr.size(); i++) {
                            ActiveLifeApplication.getInstance().setUpDb().insertClasses("" + cdr.get(i).getId(), cdr.get(i).getName(), cdr.get(i).getDescription());
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<ClassDataResponse>> call, Throwable t) {

                }
            });
        }
    }

    public void getAllSchedules() {
        if (checkIfInternet(SplashActivity.this)) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<SchedulesDataResponse>> call = mApiInterface.getSchedules();
            call.enqueue(new Callback<List<SchedulesDataResponse>>() {
                @Override
                public void onResponse(Call<List<SchedulesDataResponse>> call, Response<List<SchedulesDataResponse>> response) {
                    getAllInstructors();
                    if (response.isSuccessful()) {
                        ActiveLifeApplication.getInstance().setUpDb().deleteSchedules();
                        List<SchedulesDataResponse> cdr = response.body();
                        for (int i = 0; i < cdr.size(); i++) {
                            ActiveLifeApplication.getInstance().setUpDb().insertSchedules("" + cdr.get(i).getId(), cdr.get(i).getName());
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<SchedulesDataResponse>> call, Throwable t) {

                }
            });
        }
    }

    public void getAllInstructors() {
        if (checkIfInternet(SplashActivity.this)) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<InstructorDataResponse>> call = mApiInterface.getInstructors();
            call.enqueue(new Callback<List<InstructorDataResponse>>() {
                @Override
                public void onResponse(Call<List<InstructorDataResponse>> call, Response<List<InstructorDataResponse>> response) {
                    if (response.isSuccessful()) {
                        ActiveLifeApplication.getInstance().setUpDb().deleteInstructors();
                        List<InstructorDataResponse> cdr = response.body();
                        for (int i = 0; i < cdr.size(); i++) {
                            ActiveLifeApplication.getInstance().setUpDb().insertInstructors("" + cdr.get(i).getId(), cdr.get(i).getName());
                        }

                    }
                    if (Utilities.getSharedPrefernceData().retreiveValueFromSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME) != null && Utilities.getSharedPrefernceData().retreiveValueFromSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME).length() > 0) {
                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        Intent mainIntent = new Intent(SplashActivity.this, SelectBranchActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<List<InstructorDataResponse>> call, Throwable t) {

                }
            });
        }
    }
}

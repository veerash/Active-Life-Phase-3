package com.android.activelife.tampa.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.util.Utilities;

public class SplashActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Select Branch Activity. */
                if(Utilities.getSharedPrefernceData().retreiveValueFromSharedPreference(getApplicationContext(),Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME)!=null&&Utilities.getSharedPrefernceData().retreiveValueFromSharedPreference(getApplicationContext(),Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME).length()>0){
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else {
                    Intent mainIntent = new Intent(SplashActivity.this, SelectBranchActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

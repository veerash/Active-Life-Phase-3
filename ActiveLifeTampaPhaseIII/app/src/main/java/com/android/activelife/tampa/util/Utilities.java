package com.android.activelife.tampa.util;

import com.android.activelife.tampa.prefs.AppSharedPref;

/**
 * Created by ADMIN on 21-03-2017.
 */

public class Utilities {

    public static AppSharedPref sharedPreferencesData;

    public static AppSharedPref getSharedPrefernceData(){
        if(sharedPreferencesData==null){
            sharedPreferencesData= new AppSharedPref();
        }

        return sharedPreferencesData;
    }
}

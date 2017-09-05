package com.android.activelife.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.activelife.R;
import com.android.activelife.util.InternetConnectivity;
import com.android.activelife.util.Utilities;

import java.util.Date;

/**
 * Created by vsatrasala on 6/29/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#575759"));
        }

    }

    public ProgressDialog getProgressDialog(Context ctx) {
        if (mProgressDialog == null&&ctx!=null) {
            mProgressDialog = new ProgressDialog(ctx);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(getResources().getString(R.string.please_wait));
        }
        return mProgressDialog;
    }

    public void showProgressDialog(Context ctx) {
        try {
            if (!getProgressDialog(ctx).isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgressDialog(Context ctx) {
        try {
            if (ctx!=null&&getProgressDialog(ctx).isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfInternet(Context ctx) {
        if (new InternetConnectivity(ctx).isNetworkAvailable()) {
            return true;
        } else {
            Utilities.showToast(getApplicationContext(), getResources().getString(R.string.internet_connection_message));
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    public void addEvent(String remainderTitle, String remainderInfo,
                         Date startTime, long duration, String scheduleId, String locationName, String frequency) {
//        ContentValues values = new ContentValues();
//        values.put(Events._ID, scheduleId);
//        values.put(Events.DTSTART, startTime.getTime());
//        values.put(Events.DURATION, duration);
//        values.put(Events.TITLE, remainderTitle);
//        values.put(Events.DESCRIPTION, remainderInfo);
//        values.put(Events.CALENDAR_ID, 1);
//        values.put(Events.EVENT_LOCATION, locationName);
//        values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
//        values.put(Events.RRULE, "FREQ="+frequency);
//        ContentResolver cr = getContentResolver();
//        try {
//            cr.insert(CalendarContract.Events.CONTENT_URI, values);
//
//            alertEventAlarmSuccess();
//        } catch (Exception e) {
//            alertEventAlarm();
//            e.printStackTrace();
//        }
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        long startTim = startTime.getTime();
        long endTime = startTim + duration;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTim);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
        intent.putExtra(Events._ID, scheduleId);
        intent.putExtra(Events.TITLE, remainderTitle);
        intent.putExtra(Events.DESCRIPTION, remainderInfo);
        intent.putExtra(Events.EVENT_LOCATION, locationName);
        intent.putExtra(Events.RRULE, "FREQ=" + frequency);

        startActivity(intent);
    }

    public void alertEventAlarm() {
        AlertDialog.Builder alert = new AlertDialog.Builder(BaseActivity.this);
        alert.setMessage("Set google calendar as default");
        alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // finish();
            }
        });
        alert.show();
    }

    public void alertEventAlarmSuccess() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(BaseActivity.this);
        alert.setMessage("Class Schedule added to calendar successfully");
        alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }


}

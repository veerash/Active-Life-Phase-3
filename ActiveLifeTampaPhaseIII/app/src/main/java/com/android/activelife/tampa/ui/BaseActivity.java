package com.android.activelife.tampa.ui;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.CalendarContract.Events;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.util.InternetConnectivity;
import com.android.activelife.tampa.util.Utilities;
import com.android.activelife.tampa.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by vsatrasala on 6/29/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ProgressDialog getProgressDialog(Context ctx) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(ctx);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(getResources().getString(R.string.please_wait));
        }
        return mProgressDialog;
    }

    public void showProgressDialog(Context ctx) {
        if (!getProgressDialog(ctx).isShowing()) {
            mProgressDialog.show();
        }
    }

    public void hideProgressDialog(Context ctx) {
        if (getProgressDialog(ctx).isShowing()) {
            mProgressDialog.dismiss();
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

    public void eventsAddedDaily(String remainderTitle, String remainderInfo,
                                 String date, String startTime, String endTime, String recSubType) {
        String endtime = endTime + " " + date;
        String starttime = startTime + " " + date;
        Date d1 = Utils.getDatescheduleDetailsFromString(endtime);
        Date d = Utils.getDatescheduleDetailsFromString(starttime);

        Uri EVENTS_URI = Uri.parse(Utils.getCalendarUriBase(BaseActivity.this)
                + "events");
        ContentResolver cr = BaseActivity.this.getContentResolver();

        // event insert
        ContentValues values = new ContentValues();

        if (ActiveLifeApplication.getInstance().SDK_INT <= 10) {
            values.put("calendar_id", 1);
            values.put("title", remainderTitle);
            values.put("allDay", 0);
            values.put("dtstart", d.getTime()); // event starts at 11
            // minutes
            // from
            values.put("description", remainderInfo);
            values.put("visibility", 0);
            values.put("hasAlarm", 1);// now
            try {
                cr.insert(EVENTS_URI, values);

                // reminder insert
                Uri REMINDERS_URI = Uri.parse((Utils.getCalendarUriBase(BaseActivity.this) + "reminders"));
                values = new ContentValues();
                values.put("event_id", 1);
                values.put("method", 1);
                values.put("minutes", 10);
                cr.insert(REMINDERS_URI, values);
                alertEventAlarmSuccess();
                // AppStorage.eventsAdded.add(Str);
            } catch (Exception e) {
                alertEventAlarm();
                e.printStackTrace();
            }
        } else {

            values.put(Events.CALENDAR_ID, 1);
            values.put(Events.TITLE, remainderTitle);
            values.put(Events.ALL_DAY, 0);
            values.put(Events.DTSTART, d.getTime());
            // at
            // 11
            // minutes
            // from
            values.put(Events.DTEND, d1.getTime());
            values.put(Events.DESCRIPTION, remainderInfo);
            values.put(Events.HAS_ALARM, 1);
            values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
            try {
                cr.insert(EVENTS_URI, values);

                // reminder insert
                // Uri REMINDERS_URI = Uri.parse((AppStorage
                // .getCalendarUriBase(BaseActivity.this) + "reminders"));
                // values = new ContentValues();
                // values.put(Reminders.EVENT_ID, 1);
                // values.put(Reminders.METHOD, 1);
                // values.put(Reminders.MINUTES, 10);
                // cr.insert(REMINDERS_URI, values);
                alertEventAlarmSuccess();
                // AppStorage.eventsAdded.add(Str);
            } catch (Exception e) {
                alertEventAlarm();
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("deprecation")
    public void eventsAddedWeekly(String remainderTitle, String remainderInfo,
                                  String date, String startTime, String endTime, String recSubType) {
        String endtime = endTime + " " + date;
        String starttime = startTime + " " + date;
        String recSubWeek = null;
        int k = 0;
        Date d1 = Utils.getDatescheduleDetailsFromString(endtime);
        Date d = Utils.getDatescheduleDetailsFromString(starttime);
        // Date d1 =AppStorage.getDateDetailsFromString(endTime);
        Uri EVENTS_URI = Uri.parse(Utils.getCalendarUriBase(BaseActivity.this)
                + "events");
        ContentResolver cr = BaseActivity.this.getContentResolver();
        // Log.d("Months", "" + d.getMonth());
        int j = 0;
        if (d.getMonth() == 0 || d.getMonth() == 2 || d.getMonth() == 4
                || d.getMonth() == 5 || d.getMonth() == 7 || d.getMonth() == 9
                || d.getMonth() == 11) {
            j = 31;
        } else if (d.getMonth() == 1) {
            j = 28;
        } else {
            j = 30;
        }
        // event insert
        ContentValues values = new ContentValues();
        if (ActiveLifeApplication.SDK_INT <= 10) {
            values.put("calendar_id", 1);
            values.put("title", remainderTitle);
            values.put("allDay", 0);

            if (recSubType.length() > 1) {

                if (recSubType.contains("1") || recSubType.contains("2")) {
                    recSubWeek = recSubType.substring(2, recSubType.length());
                } else {
                    recSubWeek = recSubType.substring(1, recSubType.length());
                }

                if (recSubWeek.contains("Mon")) {
                    recSubWeek = recSubWeek.replaceAll("Mon", "MO");
                }
                if (recSubWeek.contains("Tue")) {
                    recSubWeek = recSubWeek.replaceAll("Tue", "TU");
                }
                if (recSubWeek.contains("Wed"))
                    recSubWeek = recSubWeek.replaceAll("Wed", "WE");
                if (recSubWeek.contains("Thu"))
                    recSubWeek = recSubWeek.replaceAll("Thu", "TH");
                if (recSubWeek.contains("Fri"))
                    recSubWeek = recSubWeek.replaceAll("Fri", "FR");
                if (recSubWeek.contains("Sat")) {
                    recSubWeek = recSubWeek.replaceAll("Sat", "SA");
                }
                if (recSubWeek.contains("Sun")) {
                    recSubWeek = recSubWeek.replaceAll("Sun", "SU");
                }
                k = (((j - d.getDate()) / 7) * (recSubWeek.length() + 1) / 3)
                        + (j - d.getDate()) % 7;
                values.put("rrule", "FREQ=DAILY;WKST=MO;COUNT=" + k + ";BYDAY="
                        + recSubWeek);

            } else {
                k = (j - d.getDate());
                if (recSubType.equals("1")) {
                    values.put("rrule", "FREQ=DAILY;COUNT=" + k);
                } else if (recSubType.equals("2")) {
                    values.put("rrule", "FREQ=DAILY;Interval=1;COUNT=" + k);
                }
            }

            values.put("dtstart", d.getTime()); // event starts at 11
            values.put("description", remainderInfo);
            values.put("hasAlarm", 1);// now
            try {
                cr.insert(EVENTS_URI, values);

                // reminder insert
                Uri REMINDERS_URI = Uri.parse((Utils.getCalendarUriBase(BaseActivity.this) + "reminders"));
                values = new ContentValues();
                values.put("event_id", 1);
                values.put("method", 1);
                values.put("minutes", 10);
                cr.insert(REMINDERS_URI, values);
                alertEventAlarmSuccess();
                // AppStorage.eventsAdded.add(Str);
            } catch (Exception e) {
                alertEventAlarm();
                e.printStackTrace();
            }
        } else {
            values.put(Events.CALENDAR_ID, 1);
            values.put(Events.TITLE, remainderTitle);
            values.put(Events.ALL_DAY, 0);
            values.put(Events.DTSTART, d.getTime()); // event
            if (recSubType.length() > 1) {
                if (recSubType.contains("1") || recSubType.contains("2")) {
                    recSubWeek = recSubType.substring(2, recSubType.length());
                } else {
                    recSubWeek = recSubType.substring(1, recSubType.length());
                }

                if (recSubWeek.contains("Mon")) {
                    recSubWeek = recSubWeek.replaceAll("Mon", "MO");
                }
                if (recSubWeek.contains("Tue")) {
                    recSubWeek = recSubWeek.replaceAll("Tue", "TU");
                }
                if (recSubWeek.contains("Wed"))
                    recSubWeek = recSubWeek.replaceAll("Wed", "WE");
                if (recSubWeek.contains("Thu"))
                    recSubWeek = recSubWeek.replaceAll("Thu", "TH");
                if (recSubWeek.contains("Fri"))
                    recSubWeek = recSubWeek.replaceAll("Fri", "FR");
                if (recSubWeek.contains("Sat")) {
                    recSubWeek = recSubWeek.replaceAll("Sat", "SA");
                }
                if (recSubWeek.contains("Sun")) {
                    recSubWeek = recSubWeek.replaceAll("Sun", "SU");
                }
                k = (((j - d.getDate()) / 7) * (recSubWeek.length() + 1) / 3)
                        + (j - d.getDate()) % 7;
                values.put(Events.RRULE, "FREQ=DAILY;WKST=MO;COUNT=" + k
                        + ";BYDAY=" + recSubWeek);
            } else {
                k = (j - d.getDate());
                if (recSubType.equals("1")) {
                    values.put(Events.RRULE, "FREQ=DAILY;COUNT=" + k);
                } else if (recSubType.equals("2")) {
                    values.put(Events.RRULE, "FREQ=DAILY;Interval=1;COUNT=" + k);
                }
            }// starts
            // at
            // 11
            // minutes
            // from

            values.put(Events.DTEND, d1.getTime());
            values.put(Events.DESCRIPTION, remainderInfo);
            values.put(Events.HAS_ALARM, 1);
            values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
            try {
                cr.insert(EVENTS_URI, values);

                // reminder insert
                // Uri REMINDERS_URI = Uri.parse((AppStorage
                // .getCalendarUriBase(BaseActivity.this) + "reminders"));
                // values = new ContentValues();
                // values.put(Reminders.EVENT_ID, 1);
                // values.put(Reminders.METHOD, 1);
                // values.put(Reminders.MINUTES, 10);
                // cr.insert(REMINDERS_URI, values);
                alertEventAlarmSuccess();
                // AppStorage.eventsAdded.add(Str);
            } catch (Exception e) {
                alertEventAlarm();
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("deprecation")
    public void eventsAddedMonthly(String remainderTitle, String remainderInfo,
                                   String date, String startTime, String endTime, String recSubType) {
        String endtime = endTime + " " + date;
        String starttime = startTime + " " + date;
        String recSubWeek = null;
        int k = 0;
        Date d1 = Utils.getDatescheduleDetailsFromString(endtime);
        Date d = Utils.getDatescheduleDetailsFromString(starttime);
        // Date d1 =AppStorage.getDateDetailsFromString(endTime);
        Uri EVENTS_URI = Uri.parse(Utils.getCalendarUriBase(BaseActivity.this)
                + "events");
        ContentResolver cr = BaseActivity.this.getContentResolver();
        int noOfDaysInYear = 365;
        int daysOverInYear = 0;
        int j = 0;
        for (int i = 0; i <= d.getMonth(); i++) {
            if (i == 0 || i == 2 || i == 4 || i == 5 || i == 7 || i == 9
                    || i == 11) {
                j = 31;
            } else if (i == 1) {
                j = 28;
            } else {
                j = 30;
            }
            if (i == d.getMonth()) {
                daysOverInYear = daysOverInYear + d.getDate();
            } else {
                daysOverInYear = daysOverInYear + j;
            }

        }
        // event insert
        ContentValues values = new ContentValues();
        if (ActiveLifeApplication.SDK_INT <= 10) {
            values.put("calendar_id", 1);
            values.put("title", remainderTitle);
            values.put("allDay", 0);
            values.put("dtstart", d.getTime());
            if (recSubType.length() > 1) {
                if (recSubType.contains("1") || recSubType.contains("2")) {
                    recSubWeek = recSubType.substring(2, recSubType.length());
                } else {
                    recSubWeek = recSubType.substring(1, recSubType.length());
                }

                if (recSubWeek.contains("Mon")) {
                    recSubWeek = recSubWeek.replaceAll("Mon", "MO");
                }
                if (recSubWeek.contains("Tue")) {
                    recSubWeek = recSubWeek.replaceAll("Tue", "TU");
                }
                if (recSubWeek.contains("Wed"))
                    recSubWeek = recSubWeek.replaceAll("Wed", "WE");
                if (recSubWeek.contains("Thu"))
                    recSubWeek = recSubWeek.replaceAll("Thu", "TH");
                if (recSubWeek.contains("Fri"))
                    recSubWeek = recSubWeek.replaceAll("Fri", "FR");
                if (recSubWeek.contains("Sat")) {
                    recSubWeek = recSubWeek.replaceAll("Sat", "SA");
                }
                if (recSubWeek.contains("Sun")) {
                    recSubWeek = recSubWeek.replaceAll("Sun", "SU");
                }
                k = (((noOfDaysInYear - daysOverInYear) / 7)
                        * (recSubWeek.length() + 1) / 3)
                        + (noOfDaysInYear - daysOverInYear) % 7;
                values.put("rrule", "FREQ=DAILY;WKST=MO;COUNT=" + k + ";BYDAY="
                        + recSubWeek);

            } else {
                k = (noOfDaysInYear - daysOverInYear);
                if (recSubType.equals("1")) {
                    values.put("rrule", "FREQ=DAILY;COUNT=" + k);
                } else if (recSubType.equals("2")) {
                    values.put("rrule", "FREQ=DAILY;INTERVAL=1;COUNT=" + k);
                }
            }
            // event starts at 11
            // minutes
            // from
            values.put("description", remainderInfo);
            // values.put("visibility", 1);
            values.put("hasAlarm", 1);// now
            try {
                cr.insert(EVENTS_URI, values);

                // reminder insert
                Uri REMINDERS_URI = Uri.parse((Utils.getCalendarUriBase(BaseActivity.this) + "reminders"));
                values = new ContentValues();
                values.put("event_id", 1);
                values.put("method", 1);
                values.put("minutes", 10);
                cr.insert(REMINDERS_URI, values);
                alertEventAlarmSuccess();
                // AppStorage.eventsAdded.add(Str);
            } catch (Exception e) {
                alertEventAlarm();
                e.printStackTrace();
            }
        } else {
            values.put(Events.CALENDAR_ID, 1);
            values.put(Events.TITLE, remainderTitle);
            values.put(Events.ALL_DAY, 0);
            values.put(Events.DTSTART, d.getTime());
            if (recSubType.length() > 1) {
                if (recSubType.contains("1") || recSubType.contains("2")) {
                    recSubWeek = recSubType.substring(2, recSubType.length());
                } else {
                    recSubWeek = recSubType.substring(1, recSubType.length());
                }

                if (recSubWeek.contains("Mon")) {
                    recSubWeek = recSubWeek.replaceAll("Mon", "MO");
                }
                if (recSubWeek.contains("Tue")) {
                    recSubWeek = recSubWeek.replaceAll("Tue", "TU");
                }
                if (recSubWeek.contains("Wed"))
                    recSubWeek = recSubWeek.replaceAll("Wed", "WE");
                if (recSubWeek.contains("Thu"))
                    recSubWeek = recSubWeek.replaceAll("Thu", "TH");
                if (recSubWeek.contains("Fri"))
                    recSubWeek = recSubWeek.replaceAll("Fri", "FR");
                if (recSubWeek.contains("Sat")) {
                    recSubWeek = recSubWeek.replaceAll("Sat", "SA");
                }
                if (recSubWeek.contains("Sun")) {
                    recSubWeek = recSubWeek.replaceAll("Sun", "SU");
                }
                k = (((noOfDaysInYear - daysOverInYear) / 7)
                        * (recSubWeek.length() + 1) / 3)
                        + (noOfDaysInYear - daysOverInYear) % 7;
                values.put(Events.RRULE, "FREQ=DAILY;WKST=MO;COUNT=" + k
                        + ";BYDAY=" + recSubWeek);

            } else {
                k = (noOfDaysInYear - daysOverInYear);
                if (recSubType.equals("1")) {
                    values.put(Events.RRULE, "FREQ=DAILY;COUNT=" + k);
                } else if (recSubType.equals("2")) {
                    values.put(Events.RRULE, "FREQ=DAILY;INTERVAL=1;COUNT=" + k);
                }
            }// event
            // at
            // 11
            // minutes
            // from

            values.put(Events.DTEND, d1.getTime());
            values.put(Events.DESCRIPTION, remainderInfo);
            values.put(Events.HAS_ALARM, 1);
            values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
            try {
                cr.insert(EVENTS_URI, values);

                // reminder insert
                // Uri REMINDERS_URI = Uri.parse((AppStorage
                // .getCalendarUriBase(BaseActivity.this) + "reminders"));
                // values = new ContentValues();
                // values.put(Reminders.EVENT_ID, 1);
                // values.put(Reminders.METHOD, 1);
                // values.put(Reminders.MINUTES, 10);
                // cr.insert(REMINDERS_URI, values);
                alertEventAlarmSuccess();
                // AppStorage.eventsAdded.add(Str);
            } catch (Exception e) {
                alertEventAlarm();
                e.printStackTrace();
            }
        }

    }

    public void eventsAdded(String remainderTitle, String remainderInfo,
                            String eventdate) {
        ArrayList<String> eventsAdded = Utils.readCalendarEvents(BaseActivity.this,
                BaseActivity.this.getApplicationContext());
        String eventDate = "00:00 AM " + eventdate;
        String endtime = "11:59 PM"
                + eventDate.substring(8, eventDate.length());
        boolean isAdded = false;
        String Str = remainderTitle;// +"-"+remainderInfo+"-"+startTime
        for (int i = 0; i < eventsAdded.size(); i++) {

            if (eventsAdded.size() > 0
                    && eventsAdded.get(i).toString().contains(Str)) {
                isAdded = true;
                // //Log.d("Is Added in", "" + isAdded);
            } else {
            }
        }
        // //Log.d("Is Added", "" + isAdded);
        if (!isAdded) {
            Date d = Utils.getDateDetailsFromString(eventDate);
            Date d1 = Utils.getDateDetailsFromString(endtime);
            Uri EVENTS_URI = Uri.parse(Utils.getCalendarUriBase(BaseActivity.this) + "events");
            ContentResolver cr = BaseActivity.this.getContentResolver();

            // event insert
            ContentValues values = new ContentValues();
            if (ActiveLifeApplication.SDK_INT <= 10) {
                values.put("calendar_id", 1);
                values.put("title", remainderTitle);
                values.put("allDay", 0);

                values.put("dtstart", d.getTime()); // event starts at 11
                // minutes
                // from
                values.put("dtend", d1.getTime());
                values.put("description", remainderInfo);
                //values.put("visibility", 1);
                values.put("hasAlarm", 1);// now
                try {
                    cr.insert(EVENTS_URI, values);

                    // reminder insert
                    Uri REMINDERS_URI = Uri.parse((Utils.getCalendarUriBase(BaseActivity.this) + "reminders"));
                    values = new ContentValues();
                    values.put("event_id", 1);
                    values.put("method", 1);
                    values.put("minutes", 10);
                    cr.insert(REMINDERS_URI, values);
                    alertEventAlarmSuccess();
                    // AppStorage.eventsAdded.add(Str);
                } catch (Exception e) {
                    alertEventAlarm();
                    e.printStackTrace();
                }
            } else {
                values.put(Events.CALENDAR_ID, 1);
                values.put(Events.TITLE, remainderTitle);
                values.put(Events.ALL_DAY, 0);
                values.put(Events.DTSTART, d.getTime()); // event
                // starts
                // at
                // 11
                // minutes
                // from
                values.put(Events.DTEND, d1.getTime());
                values.put(Events.DESCRIPTION, remainderInfo);
                values.put(Events.HAS_ALARM, 1);
                values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
                try {
                    cr.insert(EVENTS_URI, values);

                    // reminder insert
                    // Uri REMINDERS_URI = Uri.parse((AppStorage
                    // .getCalendarUriBase(BaseActivity.this) + "reminders"));
                    // values = new ContentValues();
                    // values.put(Reminders.EVENT_ID,
                    // 1);
                    // values.put(Reminders.METHOD, 1);
                    // values.put(Reminders.MINUTES, 10);
                    // cr.insert(REMINDERS_URI, values);
                    alertEventAlarmSuccess();
                    // AppStorage.eventsAdded.add(Str);
                } catch (Exception e) {
                    alertEventAlarm();
                    e.printStackTrace();
                }
            }

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(BaseActivity.this);
            alert.setMessage("Event already Added To Calendar");
            alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // finish();
                }
            });
            alert.show();
        }
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
        AlertDialog.Builder alert = new AlertDialog.Builder(BaseActivity.this);
        alert.setMessage("Class Schedule added to calendar successfully");
        alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // finish();
            }
        });
        alert.show();
    }


}

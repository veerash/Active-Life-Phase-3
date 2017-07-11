package com.android.activelife.tampa.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Events;

import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bruce on 14-11-6.
 */
public final class Utils {

    private Utils() {
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static String getStartOFWeek(int enterWeek, int enterYear) {
//enterWeek is week number
//enterYear is year
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, enterWeek);
        calendar.set(Calendar.YEAR, enterYear);

        SimpleDateFormat formatter = new SimpleDateFormat("dd"); // PST`
        Date startDate = calendar.getTime();
        String startDateInStr = formatter.format(startDate);
        System.out.println("...date..." + startDateInStr);

//        calendar.add(Calendar.DATE, 6);
//        Date enddate = calendar.getTime();
//        String endDaString = formatter.format(enddate);
//        System.out.println("...date..."+endDaString);
        return startDateInStr;
    }

    public static String getEndOFWeek(int enterWeek, int enterYear) {
//enterWeek is week number
//enterYear is year
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, enterWeek);
        calendar.set(Calendar.YEAR, enterYear);

        SimpleDateFormat formatter = new SimpleDateFormat("dd"); // PST`
//        Date startDate = calendar.getTime();
//        String startDateInStr = formatter.format(startDate);
//        System.out.println("...date..."+startDateInStr);

        calendar.add(Calendar.DATE, 6);
        Date enddate = calendar.getTime();
        String endDaString = formatter.format(enddate);
        System.out.println("...date..." + endDaString);
        return endDaString;
    }

    public static String getApplyiedDateType(String millis, String originalformat, String requiredFormat) {
//        SimpleDateFormat df = new SimpleDateFormat("dd MMM YYYY, hh:mm a");
//        String formattedDate = df.format(millis);
        DateFormat originalFormat = new SimpleDateFormat(originalformat, Locale.ENGLISH); //"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        DateFormat targetFormat = new SimpleDateFormat(requiredFormat); //"dd MMM yyyy"
        String formattedDate = null;
        try {
            Date date = originalFormat.parse(millis);
            formattedDate = targetFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
    public static Date getDateDetailsFromString(String st) {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm a MMMM dd, yyyy");
        Date d;
        try {
            d = fmt.parse(st);
        } catch (ParseException e) {

            e.printStackTrace();
            return null;
        }
        return d;
    }
    public static Date getDatescheduleDetailsFromString(String st) {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm:ss MMMM dd, yyyy");
        Date d;
        try {
            d = fmt.parse(st);
        } catch (ParseException e) {

            e.printStackTrace();
            return null;
        }
        return d;
    }
    @SuppressWarnings("deprecation")
    public static String getCalendarUriBase(Activity act) {

        String calendarUriBase = null;
        Uri calendars = Uri.parse("content://calendar/calendars");
        Cursor managedCursor = null;
        try {
            managedCursor = act.managedQuery(calendars, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (managedCursor != null) {
            calendarUriBase = "content://calendar/";
        } else {
            calendars = Uri.parse("content://com.android.calendar/calendars");
            try {
                managedCursor = act.managedQuery(calendars, null, null, null,
                        null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (managedCursor != null) {
                calendarUriBase = "content://com.android.calendar/";
            }
        }
        // //Log.d("Calendar", "" + calendarUriBase);
        return calendarUriBase;
    }

    public static ArrayList<String> readCalendarEvents(Activity act,
                                                       Context context) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor;
        if (ActiveLifeApplication.getInstance().SDK_INT <= 10) {
            cursor = cr.query(
                    Uri.parse(getCalendarUriBase(act) + "events"),
                    new String[] { "calendar_id", "title", "description",
                            "dtstart", "dtend", "eventLocation" }, null, null,
                    null);
        } else {
            cursor = cr.query(
                    Uri.parse(getCalendarUriBase(act) + "events"),
                    new String[] { "" +Events.CALENDAR_ID, "" + Events.TITLE,
                            "" + Events.DESCRIPTION, "" + Events.DTSTART,
                            "" + Events.DTEND, "" + Events.EVENT_LOCATION },
                    null, null, null);
        }
        ArrayList<String> addedEvents = new ArrayList<String>();
        cursor.moveToFirst();
        String[] CalNames = new String[cursor.getCount()];
        int[] CalIds = new int[cursor.getCount()];
        for (int i = 0; i < CalNames.length; i++) {
            CalIds[i] = cursor.getInt(0);
            CalNames[i] = "Event" + cursor.getInt(0) + ": \nTitle: "
                    + cursor.getString(1) + "\nDescription: "
                    + cursor.getString(2) + "\nStart Date: "
                    + new Date(cursor.getLong(3)) + "\nEnd Date : "
                    + new Date(cursor.getLong(4)) + "\nLocation : "
                    + cursor.getString(5);
            if (CalNames[i] != null) {

                addedEvents.add(CalNames[i]);
            }

            cursor.moveToNext();
        }
        cursor.close();
        return addedEvents;
    }
}

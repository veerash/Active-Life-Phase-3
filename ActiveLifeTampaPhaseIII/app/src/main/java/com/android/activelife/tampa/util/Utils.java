package com.android.activelife.tampa.util;

import android.content.res.Resources;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bruce on 14-11-6.
 */
public final class Utils {
    
    private Utils() {
    }
    
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static String getStartOFWeek(int enterWeek, int enterYear){
//enterWeek is week number
//enterYear is year
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, enterWeek);
        calendar.set(Calendar.YEAR, enterYear);

        SimpleDateFormat formatter = new SimpleDateFormat("dd"); // PST`
        Date startDate = calendar.getTime();
        String startDateInStr = formatter.format(startDate);
        System.out.println("...date..."+startDateInStr);

//        calendar.add(Calendar.DATE, 6);
//        Date enddate = calendar.getTime();
//        String endDaString = formatter.format(enddate);
//        System.out.println("...date..."+endDaString);
        return startDateInStr;
    }

    public static String getEndOFWeek(int enterWeek, int enterYear){
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
        System.out.println("...date..."+endDaString);
        return endDaString;
    }
}

package com.android.activelife.tampa.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.activelife.tampa.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;

/**
 * Created by vsatrasala on 3/1/2017.
 */

public class SchedulesFilterFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView textView;

    public SchedulesFilterFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SchedulesFilterFragment newInstance(int sectionNumber) {
        SchedulesFilterFragment fragment = new SchedulesFilterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        /** end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /** start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);


//            HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
//                    .startDate(startDate.getTime())
//                    .endDate(endDate.getTime())
//                    .build();
            textView = (TextView) rootView.findViewById(R.id.section_label2);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)   // Number of Dates cells shown on screen (Recommended 5)
                .dayFormat("EEE")     // WeekDay text format
                .dayNumberFormat("dd")  // Date format
                .textColor(Color.LTGRAY, Color.WHITE)    // Text color for none selected Dates, Text color for selected Date.
                .selectedDateBackground(Color.TRANSPARENT)  // Background color of the selected date cell.
                .selectorColor(Color.RED)   // Color of the selection indicator bar (default to colorAccent).
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                textView.setText("Selected Date : " + df.format(date));
            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Date date, int position) {
                return true;
            }
        });

        return rootView;
    }
}

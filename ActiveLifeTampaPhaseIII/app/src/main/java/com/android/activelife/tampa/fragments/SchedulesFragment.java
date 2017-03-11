package com.android.activelife.tampa.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.ui.ScheduleContainerActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;

/**
 * Created by vsatrasala on 3/1/2017.
 */

public class SchedulesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private TextView textView;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout mFilterLayout;
    private LinearLayout mScheduleLayout;
    private CheckBox mFilterImageView;
    private Button mApplyButton;

    public SchedulesFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SchedulesFragment newInstance(String param1, String param2) {
        SchedulesFragment fragment = new SchedulesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View includedView = rootView.findViewById(R.id.schedule_row_one);
        View includedView1 = rootView.findViewById(R.id.schedule_row_two);
        View includedView2 = rootView.findViewById(R.id.schedule_row_three);
        View includedView3 = rootView.findViewById(R.id.schedule_row_four);
        mFilterLayout = (LinearLayout) rootView.findViewById(R.id.filter_schedules);
        mScheduleLayout = (LinearLayout) rootView.findViewById(R.id.main_schedules);
        mFilterImageView = (CheckBox) rootView.findViewById(R.id.img_schedule_filter);
        mApplyButton = (Button) rootView.findViewById(R.id.btn_apply_filter);
        mFilterImageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFilterLayout.setVisibility(View.VISIBLE);
                    mScheduleLayout.setVisibility(View.GONE);
                } else {
                    mFilterLayout.setVisibility(View.GONE);
                    mScheduleLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilterImageView.setChecked(false);
            }
        });
        includedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ScheduleContainerActivity.class));
            }
        });
        includedView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).jSectionsPagerAdapter.getItem(5);
                startActivity(new Intent(getActivity(), ScheduleContainerActivity.class));
            }
        });
        includedView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ScheduleContainerActivity.class));
            }
        });
        includedView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ScheduleContainerActivity.class));
            }
        });
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

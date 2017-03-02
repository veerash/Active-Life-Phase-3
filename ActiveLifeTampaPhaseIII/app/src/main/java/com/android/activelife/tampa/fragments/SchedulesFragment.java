package com.android.activelife.tampa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsatrasala on 3/1/2017.
 */

public class SchedulesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView textView;
    public SchedulesFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SchedulesFragment newInstance(int sectionNumber) {
        SchedulesFragment fragment = new SchedulesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.schedules_time, container, false);
        // Spinner element
        Spinner spinner1 = (Spinner) rootView.findViewById(R.id.spinner_1);
        Spinner spinner2 = (Spinner) rootView.findViewById(R.id.spinner_2);
        Spinner spinner3 = (Spinner) rootView.findViewById(R.id.spinner_3);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Group Exercise");
        categories.add("Abs crunch");
        categories.add("Ashley");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);

//            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView = (TextView) rootView.findViewById(R.id.section_label2);
//
//            textView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
//
//
//
//            /** end after 1 month from now */
//            Calendar endDate = Calendar.getInstance();
//            endDate.add(Calendar.MONTH, 1);
//
//            /** start before 1 month from now */
//            Calendar startDate = Calendar.getInstance();
//            startDate.add(Calendar.MONTH, -1);
//
//
////            HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
////                    .startDate(startDate.getTime())
////                    .endDate(endDate.getTime())
////                    .build();
//
//            HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
//            .startDate(startDate.getTime())
//                    .endDate(endDate.getTime())
//                    .datesNumberOnScreen(5)   // Number of Dates cells shown on screen (Recommended 5)
//                    .dayFormat("EEE")     // WeekDay text format
//                    .dayNumberFormat("dd")  // Date format
//                    .textColor(Color.LTGRAY, Color.WHITE)    // Text color for none selected Dates, Text color for selected Date.
//                    .selectedDateBackground(Color.TRANSPARENT)  // Background color of the selected date cell.
//                    .selectorColor(Color.RED)   // Color of the selection indicator bar (default to colorAccent).
//                    .build();
//
//            horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
//                @Override
//                public void onDateSelected(Date date, int position) {
//                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//                    textView.setText("Selected Date : "+ df.format(date));
//                }
//
//                @Override
//                public void onCalendarScroll(HorizontalCalendarView calendarView,
//                                             int dx, int dy) {
//
//                }
//
//                @Override
//                public boolean onDateLongClicked(Date date, int position) {
//                    return true;
//                }
//            });

        return rootView;
    }
}

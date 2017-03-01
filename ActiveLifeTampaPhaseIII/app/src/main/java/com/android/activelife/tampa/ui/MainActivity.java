package com.android.activelife.tampa.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.fragments.LocationsFragment;
import com.android.activelife.tampa.fragments.MemberDetailsFragment;
import com.android.activelife.tampa.fragments.MemberFragment;
import com.android.activelife.tampa.fragments.MemberSettingsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.tab_icon_location,
            R.drawable.tab_contact_member,
            R.drawable.tab_icon_contact,
            R.drawable.tab_icon_schedules,
            R.drawable.tab_icon_membership
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(""+getIntent().getExtras().getString("title"));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView textView;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return LocationsFragment.newInstance(null, null);
                case 1:
                    return MemberSettingsFragment.newInstance(null, null);
                case 2:
                    return MemberDetailsFragment.newInstance(null, null);
                case 3:
                    return PlaceholderFragment.newInstance(position + 1);
                case 4:
                    return MemberFragment.newInstance(position + 1);
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return null;
                case 1:
                    return null;
                case 2:
                    return null;
                case 3:
                    return null;
                case 4:
                    return null;
            }
            return null;
        }

    }
}

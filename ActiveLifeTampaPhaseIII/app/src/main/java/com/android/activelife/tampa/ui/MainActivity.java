package com.android.activelife.tampa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.fragments.LocationsFragment;
import com.android.activelife.tampa.fragments.MemberDetailsFragment;
import com.android.activelife.tampa.fragments.MemberFragment;
import com.android.activelife.tampa.fragments.MemberSettingsFragment;
import com.android.activelife.tampa.fragments.SchedulesFragment;
import com.android.activelife.tampa.util.Utilities;

public class MainActivity extends BaseActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    public SectionsPagerAdapter jSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private SchedulesFragment fragment;

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
        setTitle("" + Utilities.getSharedPrefernceData().retreiveValueFromSharedPreference(getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        jSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(jSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
        tabLayout.getTabAt(3).select();
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
                    fragment =SchedulesFragment.newInstance(null, null);
                    return fragment;
                case 4:
                    return MemberFragment.newInstance(null, null);
                default:
                    return SchedulesFragment.newInstance(null, null);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}

package com.android.activelife.tampa.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.services.response.LocationData.Hour;
import com.android.activelife.tampa.services.response.LocationData.LocationDataResponse;
import com.android.activelife.tampa.services.response.LocationData.Times;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.util.Utilities;

import java.util.List;

/**
 * Created by vsatrasala on 7/19/2017.
 */

public class LocationSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context jContext;
    private List<LocationDataResponse> locationDataResponsesList;

    public LocationSelectAdapter(Context ctx, List<LocationDataResponse> locationDataResponsesList) {
        this.jContext = ctx;
        this.locationDataResponsesList = locationDataResponsesList;
    }

    @Override
    public int getItemCount() {
        return locationDataResponsesList.size();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_select_branch, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ((ViewHolder) holder).locationName.setText("" + locationDataResponsesList.get(position).getName());
        ((ViewHolder) holder).mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(jContext, MainActivity.class);
                mainIntent.putExtra("title", locationDataResponsesList.get(position).getName());
                ActiveLifeApplication.getInstance().setUpDb().deleteHours();
                for (int j = 0; j < locationDataResponsesList.get(position).getHours().size(); j++) {
                    Hour object = locationDataResponsesList.get(position).getHours().get(j);
                    String name = object.getName();
                    Times times = object.getTimes();
                    String monStartTime = null;
                    List<com.android.activelife.tampa.services.response.LocationData._1> mondayList = times.get1();
                    for (int mon = 0; mon < mondayList.size(); mon++) {
                        if (monStartTime != null && monStartTime.length() > 0)
                            monStartTime = monStartTime+"\n"+mondayList.get(mon).getStartTime() + " - " + mondayList.get(mon).getEndTime();
                        else
                            monStartTime = mondayList.get(mon).getStartTime() + " - " + mondayList.get(mon).getEndTime();
                    }
                    String tueStartTime = null;
                    List<com.android.activelife.tampa.services.response.LocationData._2> tuesdayList = times.get2();
                    for (int tue = 0; tue < tuesdayList.size(); tue++) {
                        if (tueStartTime != null && tueStartTime.length() > 0)
                            tueStartTime = tueStartTime+"\n"+tuesdayList.get(tue).getStartTime() + " - " + tuesdayList.get(tue).getEndTime();
                        else
                            tueStartTime = tuesdayList.get(tue).getStartTime() + " - " + tuesdayList.get(tue).getEndTime();
                    }
                    String wedStartTime = null;
                    List<com.android.activelife.tampa.services.response.LocationData._3> wednesdayList = times.get3();
                    for (int wed = 0; wed < wednesdayList.size(); wed++) {
                        if (wedStartTime != null && wedStartTime.length() > 0)
                            wedStartTime = wedStartTime+"\n"+wednesdayList.get(wed).getStartTime() + " - " + wednesdayList.get(wed).getEndTime();
                        else
                            wedStartTime = wednesdayList.get(wed).getStartTime() + " - " + wednesdayList.get(wed).getEndTime();
                    }
                    String thuStartTime = null;
                    List<com.android.activelife.tampa.services.response.LocationData._4> thursdayList = times.get4();
                    for (int thu = 0; thu < thursdayList.size(); thu++) {
                        if (thuStartTime != null && thuStartTime.length() > 0)
                            thuStartTime = thuStartTime+"\n"+thursdayList.get(thu).getStartTime() + " - " + thursdayList.get(thu).getEndTime();
                        else
                            thuStartTime = thursdayList.get(thu).getStartTime() + " - " + thursdayList.get(thu).getEndTime();
                    }
                    String friStartTime = null;
                    List<com.android.activelife.tampa.services.response.LocationData._5> fridayList = times.get5();
                    for (int fri = 0; fri < fridayList.size(); fri++) {
                        if (friStartTime != null && friStartTime.length() > 0)
                            friStartTime = friStartTime+"\n"+fridayList.get(fri).getStartTime() + " - " + fridayList.get(fri).getEndTime();
                        else
                            friStartTime = fridayList.get(fri).getStartTime() + " - " + fridayList.get(fri).getEndTime();
                    }
                    String satStartTime = null;
                    List<com.android.activelife.tampa.services.response.LocationData._6> saturdayList = times.get6();
                    for (int sat = 0; sat < saturdayList.size(); sat++) {
                        if (satStartTime != null && satStartTime.length() > 0)
                            satStartTime = satStartTime+"\n"+saturdayList.get(sat).getStartTime() + " - " + saturdayList.get(sat).getEndTime();
                        else
                            satStartTime = saturdayList.get(sat).getStartTime() + " - " + saturdayList.get(sat).getEndTime();
                    }
                    String sunStartTime = null;
                    List<com.android.activelife.tampa.services.response.LocationData._7> sundayList = times.get7();
                    for (int sun = 0; sun < sundayList.size(); sun++) {
                        if (sunStartTime != null && sunStartTime.length() > 0)
                            sunStartTime = sunStartTime+"\n"+sundayList.get(sun).getStartTime() + " - " + sundayList.get(sun).getEndTime();
                        else
                            sunStartTime = sundayList.get(sun).getStartTime() + " - " + sundayList.get(sun).getEndTime();
                    }
                    ActiveLifeApplication.getInstance().setUpDb().insertHoursDao(name, monStartTime, null, tueStartTime, null, wedStartTime, null, thuStartTime, null, friStartTime, null, satStartTime, null, sunStartTime, null);

                }
                jContext.startActivity(mainIntent);
                ActiveLifeApplication.getInstance().setUpDb().deleteDefaultLocations();
                ActiveLifeApplication.getInstance().setUpDb().insertDefaultLocation(position, "" + locationDataResponsesList.get(position).getId(), locationDataResponsesList.get(position).getName(), locationDataResponsesList.get(position).getAddress(), locationDataResponsesList.get(position).getCity(), locationDataResponsesList.get(position).getState(), locationDataResponsesList.get(position).getZipCode(), locationDataResponsesList.get(position).getPhone(), locationDataResponsesList.get(position).getEmail(), locationDataResponsesList.get(position).getProgramLink(), locationDataResponsesList.get(position).getDonationLink());
                ActiveLifeApplication.getInstance().setUpDb().deleteLocation();
                ActiveLifeApplication.getInstance().setUpDb().insertLocation(position, "" + locationDataResponsesList.get(position).getId(), locationDataResponsesList.get(position).getName(), locationDataResponsesList.get(position).getAddress(), locationDataResponsesList.get(position).getCity(), locationDataResponsesList.get(position).getState(), locationDataResponsesList.get(position).getZipCode(), locationDataResponsesList.get(position).getPhone(), locationDataResponsesList.get(position).getEmail(), locationDataResponsesList.get(position).getProgramLink(), locationDataResponsesList.get(position).getDonationLink());
                Utilities.getSharedPrefernceData().storeIntValueIntoSharedPreference(jContext.getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_ID, locationDataResponsesList.get(position).getId());
                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(jContext.getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME, locationDataResponsesList.get(position).getName());
                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(jContext.getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_PROGRAM_LINK, locationDataResponsesList.get(position).getProgramLink());
                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(jContext.getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_DONATE_LINK, locationDataResponsesList.get(position).getDonationLink());

                ((MainActivity) jContext).finish();
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        private LinearLayout mainLayout;

        public ViewHolder(View convertView) {
            super(convertView);
            locationName = (TextView) convertView.findViewById(R.id.list_row_select_branch_name);
            mainLayout = (LinearLayout) convertView.findViewById(R.id.main_content);
        }
    }
}

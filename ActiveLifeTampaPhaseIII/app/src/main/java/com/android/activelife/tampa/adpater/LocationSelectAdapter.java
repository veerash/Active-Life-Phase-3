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
                    String monStartTime = times.get1().get(0).getStartTime();
                    String monEndTime = times.get1().get(0).getEndTime();
                    String tueStartTime = times.get2().get(0).getStartTime();
                    String tueEndTime = times.get2().get(0).getEndTime();
                    String wedStartTime = times.get3().get(0).getStartTime();
                    String wedEndTime = times.get3().get(0).getEndTime();
                    String thuStartTime = times.get4().get(0).getStartTime();
                    String thuEndTime = times.get4().get(0).getEndTime();
                    String friStartTime = times.get5().get(0).getStartTime();
                    String friEndTime = times.get5().get(0).getEndTime();
                    String satStartTime = times.get6().get(0).getStartTime();
                    String satEndTime = times.get6().get(0).getEndTime();
                    String sunStartTime = times.get7().get(0).getStartTime();
                    String sunEndTime = times.get7().get(0).getEndTime();
                    ActiveLifeApplication.getInstance().setUpDb().insertHoursDao(name, monStartTime, monEndTime, tueStartTime, tueEndTime, wedStartTime, wedEndTime, thuStartTime, thuEndTime, friStartTime, friEndTime, satStartTime, satEndTime, sunStartTime, sunEndTime);
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

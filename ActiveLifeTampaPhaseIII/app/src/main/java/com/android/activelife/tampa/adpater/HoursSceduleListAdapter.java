package com.android.activelife.tampa.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.db.HoursData;

import java.util.ArrayList;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class HoursSceduleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context jContext;
    private ArrayList<HoursData> mHoursDataArrayList;

    public HoursSceduleListAdapter(Context ctx, ArrayList<HoursData> hoursDataArrayList) {
        this.jContext = ctx;
        this.mHoursDataArrayList = hoursDataArrayList;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_hours, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
//        }
    }

    @Override
    public int getItemCount() {
        return mHoursDataArrayList.size();
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).hoursTile.setText("" + mHoursDataArrayList.get(position).getHours_name());
//        ((ViewHolder) holder).hoursOneLayout.setVisibility(View.VISIBLE);
//        ((ViewHolder) holder).hoursOneTitle.setText("Monday");
        ((ViewHolder) holder).hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() );
//        ((ViewHolder) holder).hoursTwoTitle.setText("Tuesday");
        ((ViewHolder) holder).hoursTwo.setText("" + mHoursDataArrayList.get(position).getHour_tuesday_start_time() );
//        ((ViewHolder) holder).hoursTwoLayout.setVisibility(View.VISIBLE);
//        ((ViewHolder) holder).hoursThreeTitle.setText("Wednesday");
        ((ViewHolder) holder).hoursThree.setText("" + mHoursDataArrayList.get(position).getHour_wednesday_start_time() );
//        ((ViewHolder) holder).hoursFourTtile.setText("Thursday");
        ((ViewHolder) holder).hoursFour.setText("" + mHoursDataArrayList.get(position).getHour_thursday_start_time() );
//        ((ViewHolder) holder).hoursThreeLayout.setVisibility(View.VISIBLE);
//        ((ViewHolder) holder).hourFiveTitle.setText("Friday");
        ((ViewHolder) holder).hourFive.setText("" + mHoursDataArrayList.get(position).getHour_friday_start_time() );
//        ((ViewHolder) holder).hourSixTitle.setText("Saturday");
        ((ViewHolder) holder).hourSix.setText("" + mHoursDataArrayList.get(position).getHour_saturday_start_time());
//        ((ViewHolder) holder).hoursFourLayout.setVisibility(View.VISIBLE);
//        ((ViewHolder) holder).hourSevenTitle.setText("Sunday");
        ((ViewHolder) holder).hourSeven.setText("" + mHoursDataArrayList.get(position).getHour_sunday_start_time() );
//        ((ViewHolder) holder).hourNineTitle.setText("Sunday");
//        ((ViewHolder) holder).hourNine.setText("" + mHoursDataArrayList.get(position).getHour_sunday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_sunday_end_time());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hoursTile, hoursOneTitle, hoursOne, hoursTwoTitle, hoursTwo, hoursThreeTitle, hoursThree, hoursFourTtile, hoursFour, hourFiveTitle, hourFive, hourSixTitle, hourSix, hourSevenTitle, hourSeven;
//        hourEightTitle, hourEight, hourNineTitle,hourNine;
//        private LinearLayout hoursOneLayout, hoursTwoLayout, hoursThreeLayout, hoursFourLayout;

        public ViewHolder(View convertView) {
            super(convertView);
            hoursTile = (TextView) convertView.findViewById(R.id.hours_title);
//            hoursOneLayout = (LinearLayout) convertView.findViewById(R.id.hour_one_layout);
//            hoursTwoLayout = (LinearLayout) convertView.findViewById(R.id.hour_two_layout);
//            hoursThreeLayout = (LinearLayout) convertView.findViewById(R.id.hour_three_layout);
//            hoursFourLayout = (LinearLayout) convertView.findViewById(R.id.hour_four_layout);
            hoursOneTitle = (TextView) convertView.findViewById(R.id.hours_one);
            hoursOne = (TextView) convertView.findViewById(R.id.hours_one_ti);
            hoursTwoTitle = (TextView) convertView.findViewById(R.id.hours_two);
            hoursTwo = (TextView) convertView.findViewById(R.id.hours_two_ti);
            hoursThreeTitle = (TextView) convertView.findViewById(R.id.hours_three);
            hoursThree = (TextView) convertView.findViewById(R.id.hours_three_ti);
            hoursFourTtile = (TextView) convertView.findViewById(R.id.hours_four);
            hoursFour = (TextView) convertView.findViewById(R.id.hours_four_ti);
            hourFiveTitle = (TextView) convertView.findViewById(R.id.hours_five);
            hourFive = (TextView) convertView.findViewById(R.id.hours_five_ti);
            hourSixTitle = (TextView) convertView.findViewById(R.id.hours_six);
            hourSix = (TextView) convertView.findViewById(R.id.hours_six_ti);
            hourSevenTitle = (TextView) convertView.findViewById(R.id.hours_seven);
            hourSeven = (TextView) convertView.findViewById(R.id.hours_seven_ti);
//            hourNineTitle = (TextView) convertView.findViewById(R.id.hours_nine);
//            hourNine = (TextView) convertView.findViewById(R.id.hours_nine_ti);
        }
    }

    @Override
    public int getItemViewType(int position) {
//            return 0;

        return 1;
    }
}

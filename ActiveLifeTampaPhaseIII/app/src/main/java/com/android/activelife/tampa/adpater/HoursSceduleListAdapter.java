package com.android.activelife.tampa.adpater;

import android.content.Context;
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

public class HoursSceduleListAdapter extends BaseAdapter {

    public Context jContext;
    private ArrayList<HoursData> mHoursDataArrayList;

    public HoursSceduleListAdapter(Context ctx, ArrayList<HoursData> hoursDataArrayList) {
        this.jContext = ctx;
        this.mHoursDataArrayList = hoursDataArrayList;
    }

    @Override
    public int getCount() {
        return mHoursDataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(jContext).inflate(R.layout.list_row_hours, parent, false);
            holder.hoursTile = (TextView) convertView.findViewById(R.id.hours_title);
            holder.hoursOneLayout = (LinearLayout) convertView.findViewById(R.id.hour_one_layout);
            holder.hoursTwoLayout = (LinearLayout) convertView.findViewById(R.id.hour_two_layout);
            holder.hoursThreeLayout = (LinearLayout) convertView.findViewById(R.id.hour_three_layout);
            holder.hoursFourLayout = (LinearLayout) convertView.findViewById(R.id.hour_four_layout);
            holder.hoursOneTitle = (TextView) convertView.findViewById(R.id.hours_one);
            holder.hoursOne = (TextView) convertView.findViewById(R.id.hours_one_ti);
            holder.hoursTwoTitle = (TextView) convertView.findViewById(R.id.hours_two);
            holder.hoursTwo = (TextView) convertView.findViewById(R.id.hours_two_ti);
            holder.hoursThreeTitle = (TextView) convertView.findViewById(R.id.hours_three);
            holder.hoursThree = (TextView) convertView.findViewById(R.id.hours_three_ti);
            holder.hoursFourTtile = (TextView) convertView.findViewById(R.id.hours_four);
            holder.hoursFour = (TextView) convertView.findViewById(R.id.hours_four_ti);
            holder.hourFiveTitle = (TextView) convertView.findViewById(R.id.hours_five);
            holder.hourFive = (TextView) convertView.findViewById(R.id.hours_five_ti);
            holder.hourSixTitle = (TextView) convertView.findViewById(R.id.hours_six);
            holder.hourSix = (TextView) convertView.findViewById(R.id.hours_six_ti);
            holder.hourSevenTitle = (TextView) convertView.findViewById(R.id.hours_seven);
            holder.hourSeven = (TextView) convertView.findViewById(R.id.hours_seven_ti);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.hoursTile.setText("" + mHoursDataArrayList.get(position).getHours_name());
//        String startTime = mHoursDataArrayList.get(position).getHour_monday_start_time();
//        if (startTime.equals(mHoursDataArrayList.get(position).getHour_tuesday_start_time())) {
//            if(startTime.equals(mHoursDataArrayList.get(position).getHour_wednesday_start_time())){
//                if(startTime.equals(mHoursDataArrayList.get(position).getHour_thursday_start_time())){
//                    if(startTime.equals(mHoursDataArrayList.get(position).getHour_friday_start_time())){
//                        if(startTime.equals(mHoursDataArrayList.get(position).getHour_saturday_start_time())){
//                            if(startTime.equals(mHoursDataArrayList.get(position).getHour_sunday_start_time())){
//                                holder.hoursOneLayout.setVisibility(View.VISIBLE);
//                                holder.hoursOneTitle.setText("Monday - Sunday");
//                                holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
//                                holder.hoursTwoLayout.setVisibility(View.GONE);
//                                holder.hoursThreeLayout.setVisibility(View.GONE);
//                                holder.hoursFourLayout.setVisibility(View.GONE);
//                            }else{
//                                holder.hoursTwoLayout.setVisibility(View.GONE);
//                                holder.hoursThreeLayout.setVisibility(View.GONE);
//                                holder.hoursFourLayout.setVisibility(View.GONE);
//                                holder.hoursOneLayout.setVisibility(View.VISIBLE);
//                                holder.hoursOneTitle.setText("Monday - Saturday");
//                                holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
//                                holder.hoursTwoTitle.setText("Sunday");
//                                holder.hoursTwo.setText("" + mHoursDataArrayList.get(position).getHour_sunday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_sunday_end_time());
//
//                            }
//                        }else{
//                            holder.hoursOneLayout.setVisibility(View.VISIBLE);
//                            holder.hoursOneTitle.setText("Monday - Friday");
//                            holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
//                        }
//                    }else{
//                        holder.hoursOneLayout.setVisibility(View.VISIBLE);
//                        holder.hoursOneTitle.setText("Monday - Thursday");
//                        holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
//                    }
//                }else{
//                    holder.hoursOneLayout.setVisibility(View.VISIBLE);
//                    holder.hoursOneTitle.setText("Monday - Wednesay");
//                    holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
//                }
//
//            }else{
//                holder.hoursOneLayout.setVisibility(View.VISIBLE);
//                holder.hoursOneTitle.setText("Monday - Tuesday");
//                holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
//            }
//
//        } else {
//            holder.hoursOneLayout.setVisibility(View.VISIBLE);
//            holder.hoursOneTitle.setText("Monday");
//            holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
//        }
        holder.hoursOneLayout.setVisibility(View.VISIBLE);
        holder.hoursOneTitle.setText("Monday");
        holder.hoursOne.setText("" + mHoursDataArrayList.get(position).getHour_monday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_monday_end_time());
        holder.hoursTwoTitle.setText("Tuesday");
        holder.hoursTwo.setText("" + mHoursDataArrayList.get(position).getHour_tuesday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_tuesday_end_time());
        holder.hoursTwoLayout.setVisibility(View.VISIBLE);
        holder.hoursThreeTitle.setText("Wednesday");
        holder.hoursThree.setText("" + mHoursDataArrayList.get(position).getHour_wednesday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_wednesday_end_time());
        holder.hoursFourTtile.setText("Thursday");
        holder.hoursFour.setText("" + mHoursDataArrayList.get(position).getHour_thursday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_thursday_end_time());
        holder.hoursThreeLayout.setVisibility(View.VISIBLE);
        holder.hourFiveTitle.setText("Friday");
        holder.hourFive.setText("" + mHoursDataArrayList.get(position).getHour_friday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_friday_end_time());
        holder.hourSixTitle.setText("Saturday");
        holder.hourSix.setText("" + mHoursDataArrayList.get(position).getHour_saturday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_saturday_end_time());
        holder.hoursFourLayout.setVisibility(View.VISIBLE);
        holder.hourSevenTitle.setText("Sunday");
        holder.hourSeven.setText("" + mHoursDataArrayList.get(position).getHour_sunday_start_time() + " - " + mHoursDataArrayList.get(position).getHour_sunday_end_time());
        return convertView;
    }

    class ViewHolder {
        TextView hoursTile, hoursOneTitle, hoursOne, hoursTwoTitle, hoursTwo, hoursThreeTitle, hoursThree, hoursFourTtile, hoursFour, hourFiveTitle, hourFive, hourSixTitle, hourSix, hourSevenTitle, hourSeven, hourEightTitle, hourEight;
        LinearLayout hoursOneLayout, hoursTwoLayout, hoursThreeLayout, hoursFourLayout;

    }
}

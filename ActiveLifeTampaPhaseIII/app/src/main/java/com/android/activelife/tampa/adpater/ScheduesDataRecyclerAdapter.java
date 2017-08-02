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
import com.android.activelife.tampa.services.response.scheduledatedata.ScheduleDateDataResponse;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.ui.ReserveActivity;
import com.android.activelife.tampa.ui.ScheduleContainerActivity;
import com.android.activelife.tampa.util.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vsatrasala on 4/6/2017.
 */

public class ScheduesDataRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context jContext;
    private List<ScheduleDateDataResponse> mMessagesDataResponseList;
    private String date;

    public ScheduesDataRecyclerAdapter(Context ctx, List<ScheduleDateDataResponse> mMessagesDataResponseList,String date) {
        this.jContext = ctx;
        this.mMessagesDataResponseList = mMessagesDataResponseList;
        this.date=date;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_date_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
//        }
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ((ViewHolder) holder).tvYmca.setText("" + mMessagesDataResponseList.get(position).getLocation().getName());
        ((ViewHolder) holder).tvHours.setText(Utils.getApplyiedDateType("" + mMessagesDataResponseList.get(position).getStartTime(), "HH:mm:ss", "hh:mm a"));
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date startDate = null, endDate = null;
        try {
            startDate = df.parse(mMessagesDataResponseList.get(position).getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = df.parse(mMessagesDataResponseList.get(position).getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long duration = endDate.getTime() - startDate.getTime();

        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        ((ViewHolder) holder).tvMins.setText("" + diffInMinutes + " Mins");
        ((ViewHolder) holder).tvEvent.setText("" + mMessagesDataResponseList.get(position).getGetClass().getName());
        ((ViewHolder) holder).tvName.setText("" + mMessagesDataResponseList.get(position).getInstructor().getName());
        ((ViewHolder) holder).reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reserveIntent = new Intent(jContext, ReserveActivity.class);
                reserveIntent.putExtra("session_id",""+mMessagesDataResponseList.get(position).getId());
                reserveIntent.putExtra("date",date);
                ((MainActivity)jContext).startActivityForResult(reserveIntent,1000);
            }
        });
        if (mMessagesDataResponseList.get(position).getIsReservable() == 1) {
            ((ViewHolder) holder).reserveButton.setVisibility(View.VISIBLE);
        } else {
            ((ViewHolder) holder).reserveButton.setVisibility(View.GONE);
        }
        ((ViewHolder) holder).mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scheduleDetailIntent = new Intent(jContext, ScheduleContainerActivity.class);
                scheduleDetailIntent.putExtra("schedule_id", mMessagesDataResponseList.get(position).getId());
                scheduleDetailIntent.putExtra("date",date);
                scheduleDetailIntent.putExtra("schedule_name", mMessagesDataResponseList.get(position).getSchedule().getName());
                jContext.startActivity(scheduleDetailIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMessagesDataResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvYmca, tvHours, tvMins, tvName, tvEvent, reserveButton;
        private LinearLayout mainLayout;

        public ViewHolder(View convertView) {
            super(convertView);
            tvYmca = (TextView) convertView.findViewById(R.id.tv_ymca);
            tvHours = (TextView) convertView.findViewById(R.id.tv_hours);
            tvMins = (TextView) convertView.findViewById(R.id.tv_mins);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvEvent = (TextView) convertView.findViewById(R.id.tv_event);
            reserveButton = (TextView) convertView.findViewById(R.id.reserve);
            mainLayout =(LinearLayout)convertView.findViewById(R.id.main_content);
        }
    }

    @Override
    public int getItemViewType(int position) {
//            return 0;

        return 1;
    }


}

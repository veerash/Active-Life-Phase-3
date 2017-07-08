package com.android.activelife.tampa.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.db.ScheduleDateData;
import com.android.activelife.tampa.services.response.messagesdata.MessagesDataResponse;
import com.android.activelife.tampa.services.response.scheduledatedata.ScheduleDateDataResponse;
import com.android.activelife.tampa.util.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class SchedulesDateListAdapter extends BaseAdapter {

    public Context jContext;
    private List<ScheduleDateDataResponse> mMessagesDataResponseList;

    public SchedulesDateListAdapter(Context ctx, List<ScheduleDateDataResponse> mMessagesDataResponseList) {
        this.jContext = ctx;
        this.mMessagesDataResponseList=mMessagesDataResponseList;
    }

    @Override
    public int getCount() {
        return mMessagesDataResponseList.size();
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
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(jContext).inflate(R.layout.calender_date_list_row, parent, false);
            holder.tvYmca=(TextView)convertView.findViewById(R.id.tv_ymca);
            holder.tvHours=(TextView)convertView.findViewById(R.id.tv_hours);
            holder.tvMins=(TextView)convertView.findViewById(R.id.tv_mins);
            holder.tvName=(TextView)convertView.findViewById(R.id.tv_name);
            holder.tvEvent=(TextView)convertView.findViewById(R.id.tv_event);
            holder.reserveButton=(TextView)convertView.findViewById(R.id.reserve);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvYmca.setText(""+mMessagesDataResponseList.get(position).getLocation().getName());
        holder.tvHours.setText(Utils.getApplyiedDateType(""+mMessagesDataResponseList.get(position).getStartTime(),"HH:mm:ss","hh:mm a"));
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date startDate=null, endDate=null;
        try {
             startDate=df.parse(mMessagesDataResponseList.get(position).getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate=df.parse(mMessagesDataResponseList.get(position).getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long duration  = endDate.getTime() - startDate.getTime();

        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        holder.tvMins.setText(""+diffInMinutes +"Mins");
        holder.tvEvent.setText(""+mMessagesDataResponseList.get(position).getGetClass().getName());
        holder.tvName.setText(""+mMessagesDataResponseList.get(position).getInstructor().getName());
        return convertView;
    }

    class ViewHolder {
        TextView tvYmca,tvHours, tvMins, tvEvent, tvName, reserveButton;
    }
}

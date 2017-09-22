package com.android.activelife.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.activelife.R;
import com.android.activelife.appcontroller.ActiveLifeApplication;
import com.android.activelife.db.LocationsData;
import com.android.activelife.db.MessagesData;
import com.android.activelife.ui.MessageDetailsActivity;
import com.android.activelife.util.Utils;

import java.util.List;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class MessagesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context jContext;
    private List<MessagesData> mMessagesDataResponseList;
    private String mLocationId;

    public MessagesListAdapter(Context ctx, List<MessagesData> mMessagesDataResponseList,String mLocationId) {
        this.jContext = ctx;
        this.mMessagesDataResponseList = mMessagesDataResponseList;
        this.mLocationId=mLocationId;
    }

    @Override
    public int getItemCount() {
        return mMessagesDataResponseList.size();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_messages, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).messageLabel.setText("" + mMessagesDataResponseList.get(position).getMessage_title());
        ((ViewHolder) holder).messageDesc.setText("" + mMessagesDataResponseList.get(position).getMessage_msg());

        ((ViewHolder)holder).messageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messsageIntent = new Intent(jContext, MessageDetailsActivity.class);
                messsageIntent.putExtra("title", mMessagesDataResponseList.get(position).getMessage_title());
                messsageIntent.putExtra("desc", mMessagesDataResponseList.get(position).getMessage_msg());
                String location = null;
                LocationsData data = ActiveLifeApplication.getInstance().setUpDb().getLocationById(mLocationId);
                if (location != null && location.length() > 0) {
                    location = location + ", " + data.getLocation_name();
                } else {
                    location = data.getLocation_name();
                }

                messsageIntent.putExtra("location", location);
                String dateTime = mMessagesDataResponseList.get(position).getMessage_send_at();
                messsageIntent.putExtra("date", Utils.getApplyiedDateType(dateTime, "yyyy-MM-dd HH:mm:ss", "MMM dd, EEE"));
                messsageIntent.putExtra("time", Utils.getApplyiedDateType(dateTime, "yyyy-MM-dd HH:mm:ss", "hh:mm a"));
                jContext.startActivity(messsageIntent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageLabel, messageDesc;
        LinearLayout messageLayout;

        public ViewHolder(View convertView) {
            super(convertView);
            messageLabel = (TextView) convertView.findViewById(R.id.message_heading);
            messageDesc = (TextView) convertView.findViewById(R.id.message_desc);
            messageLayout=(LinearLayout)convertView.findViewById(R.id.messages_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
//            return 0;

        return 1;
    }
}

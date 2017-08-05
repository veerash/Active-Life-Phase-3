package com.android.activelife.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.android.activelife.R;
import com.android.activelife.db.MessagesData;
import com.android.activelife.services.response.messagesdata.MessagesDataResponse;

import java.util.List;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class MessagesListAdapter extends BaseAdapter {

    public Context jContext;
    private List<MessagesData> mMessagesDataResponseList;

    public MessagesListAdapter(Context ctx,List<MessagesData> mMessagesDataResponseList) {
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
            convertView = LayoutInflater.from(jContext).inflate(R.layout.list_row_messages, parent, false);
            holder.messageLabel=(TextView)convertView.findViewById(R.id.message_heading);
            holder.messageDesc=(TextView)convertView.findViewById(R.id.message_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.messageLabel.setText(""+mMessagesDataResponseList.get(position).getMessage_title());
        holder.messageDesc.setText(""+mMessagesDataResponseList.get(position).getMessage_msg());
        return convertView;
    }

    class ViewHolder {
        TextView messageLabel, messageDesc;
    }
}

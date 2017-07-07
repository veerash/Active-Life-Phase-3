package com.android.activelife.tampa.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.db.ClassData;
import com.android.activelife.tampa.db.InstructorData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class InstructorsListAdapter extends BaseAdapter {

    public Context jContext;
    private ArrayList<InstructorData> locationDataResponsesList;

    public InstructorsListAdapter(Context ctx, ArrayList<InstructorData> locationDataResponsesList) {
        this.jContext = ctx;
        this.locationDataResponsesList=locationDataResponsesList;
    }

    @Override
    public int getCount() {
        return locationDataResponsesList.size();
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
            convertView = LayoutInflater.from(jContext).inflate(R.layout.list_row_spinner, parent, false);
            holder.locationName = (TextView) convertView.findViewById(R.id.list_row_select_branch_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.locationName.setText(""+locationDataResponsesList.get(position).getInstructor_name());
        return convertView;
    }

    class ViewHolder {
        TextView locationName;
    }
}

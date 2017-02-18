package com.android.activelife.tampa.adpater;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.squareup.picasso.Picasso;

import java.net.URLEncoder;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class MembersListAdapter extends BaseAdapter {

    public Context jContext;

    public MembersListAdapter(Context ctx) {
        this.jContext = ctx;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView = LayoutInflater.from(jContext).inflate(R.layout.list_row_members, parent, false);
            holder.memberId = (TextView) convertView.findViewById(R.id.id);
            holder.memberName = (TextView) convertView.findViewById(R.id.member_name);
            holder.memmberBarCodeId = (TextView) convertView.findViewById(R.id.member_id);
            holder.memberDetailsLayout = (LinearLayout) convertView.findViewById(R.id.membership_details);
            holder.addDetails = (ImageView) convertView.findViewById(R.id.add_member);
            holder.barCodeImage = (ImageView) convertView.findViewById(R.id.bar_code_image);
            holder.updateDetails = (ImageView) convertView.findViewById(R.id.edit_member);
            holder.cardView = convertView.findViewById(R.id.add_edit_member_layout);
            holder.addButton = (Button) holder.cardView.findViewById(R.id.add_member);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String barCode = "257962";
        String astrticksBarcode = "*" + barCode + "*";
        Typeface typeface = Typeface.createFromAsset(jContext.getAssets(),
                "fonts/IDAutomationHC39M_FREE.otf");
        holder.memmberBarCodeId.setTypeface(typeface);
        holder.memmberBarCodeId.setText(astrticksBarcode);
        String sizeofQRCode = "300x300";
        String url = "https://chart.googleapis.com/chart?chs=" + sizeofQRCode
                + "&cht=qr&chl=" + URLEncoder.encode(barCode);
        Picasso.with(jContext)
                .load(url)
                .into(holder.barCodeImage);
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.memberDetailsLayout.setVisibility(View.GONE);
                holder.memberId.setVisibility(View.VISIBLE);
                holder.cardView.setVisibility(View.GONE);
            }
        });
        holder.updateDetails
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Creating the instance of PopupMenu
                        // Context wrapper = new
                        // ContextThemeWrapper(context,
                        // R.style.MyPopupMenu);
                        final PopupMenu popup = new PopupMenu(jContext, v);
                        // Inflating the Popup using xml file
                        popup.getMenuInflater().inflate(R.menu.popup_menu,
                                popup.getMenu());

                        // registering popup with OnMenuItemClickListener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                if (item.getTitle().equals("Edit")) {
                                    holder.memberDetailsLayout.setVisibility(View.GONE);
                                    holder.memberId.setVisibility(View.GONE);
                                    holder.cardView.setVisibility(View.VISIBLE);
                                } else {
                                    popup.dismiss();
                                }
                                return true;
                            }
                        });

                        popup.show();
                    }
                });
        holder.memberId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.memberDetailsLayout.setVisibility(View.VISIBLE);
                holder.memberId.setVisibility(View.GONE);
                holder.cardView.setVisibility(View.GONE);
            }
        });
        holder.memberDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.memberDetailsLayout.setVisibility(View.GONE);
                holder.memberId.setVisibility(View.VISIBLE);
                holder.cardView.setVisibility(View.GONE);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.memberDetailsLayout.setVisibility(View.VISIBLE);
                holder.memberId.setVisibility(View.GONE);
                holder.cardView.setVisibility(View.GONE);
            }
        });
        holder.addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.memberDetailsLayout.setVisibility(View.GONE);
                holder.memberId.setVisibility(View.GONE);
                holder.cardView.setVisibility(View.VISIBLE);
            }
        });

        return convertView;
    }


    class ViewHolder {
        TextView memberName, memberId, memmberBarCodeId;
        LinearLayout memberDetailsLayout;
        ImageView updateDetails, addDetails, barCodeImage;
        Button addButton;
        View cardView;

    }

}
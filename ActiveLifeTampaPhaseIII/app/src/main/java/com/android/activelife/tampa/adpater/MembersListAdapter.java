package com.android.activelife.tampa.adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
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
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.MemberData;
import com.android.activelife.tampa.fragments.MemberFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class MembersListAdapter extends BaseAdapter {

    public Context jContext;
    private List<MemberData> instructorDataResponse;
    private MemberFragment fragment;

    public MembersListAdapter(Context ctx, List<MemberData> instructorDataResponse, MemberFragment fragment) {
        this.jContext = ctx;
        this.instructorDataResponse = instructorDataResponse;
        this.fragment=fragment;
    }

    @Override
    public int getCount() {
        return instructorDataResponse.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(jContext).inflate(R.layout.list_row_members, parent, false);
            holder.memberId = (TextView) convertView.findViewById(R.id.id);
            holder.memberName = (TextView) convertView.findViewById(R.id.member_name);
            holder.memmberBarCodeId = (TextView) convertView.findViewById(R.id.member_id);
            holder.memberDetailsLayout = (LinearLayout) convertView.findViewById(R.id.membership_details);
            holder.cardDetailsLayout = (LinearLayout) convertView.findViewById(R.id.card_details_layout);
            holder.barCodeImage = (ImageView) convertView.findViewById(R.id.bar_code_image);
            holder.closeCradLayout= (ImageView) convertView.findViewById(R.id.back_button);
            holder.updateDetails = (ImageView) convertView.findViewById(R.id.edit_member);
            holder.cardView = convertView.findViewById(R.id.add_edit_member_layout);
            holder.addButton = (Button) holder.cardView.findViewById(R.id.add_member);
            holder.memberEditId = (TextView) holder.cardView.findViewById(R.id.member_id_edit_text);
            holder.memberEditName = (TextView) holder.cardView.findViewById(R.id.member_name_edit_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.memberId.setText("" + instructorDataResponse.get(position).getMember_name());
        holder.memberName.setText("" + instructorDataResponse.get(position).getMember_name());
        holder.memberEditId.setText("" + instructorDataResponse.get(position).getMember_id());
        holder.memberEditName.setText("" + instructorDataResponse.get(position).getMember_name());
        String barCode = instructorDataResponse.get(position).getMember_id();
        String astrticksBarcode = "*" + barCode + "*";
        Typeface typeface = Typeface.createFromAsset(jContext.getAssets(),
                "fonts/IDAutomationHC39M_FREE.otf");
        holder.memmberBarCodeId.setTypeface(typeface);
        holder.memmberBarCodeId.setText(astrticksBarcode);
        String sizeofQRCode = "300x300";
        String url = "https://chart.googleapis.com/chart?chs=" + sizeofQRCode
                + "&cht=qr&chl=" + URLEncoder.encode(barCode);
        Log.i("Url", url);
        try {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.chart).cacheOnDisc()
                    .build();
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(url, holder.barCodeImage, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                                    holder.cardDetailsLayout.setVisibility(View.VISIBLE);
                                    holder.addButton.setText("Update");
                                } else {
                                    holder.addButton.setText("Add");
                                    ActiveLifeApplication.getInstance().setUpDb().deleteMemberDataById(instructorDataResponse.get(position).getMember_id());
                                    popup.dismiss();
                                    fragment.setMembersData();
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
                holder.cardDetailsLayout.setVisibility(View.GONE);
            }
        });
        holder.memberDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.memberDetailsLayout.setVisibility(View.GONE);
                holder.memberId.setVisibility(View.VISIBLE);
                holder.cardDetailsLayout.setVisibility(View.GONE);
            }
        });
        holder.closeCradLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.memberDetailsLayout.setVisibility(View.VISIBLE);
                holder.memberId.setVisibility(View.GONE);
                holder.cardDetailsLayout.setVisibility(View.GONE);
            }
        });

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.memberEditId.getText()==null||holder.memberEditId.getText().length()==0){
                    holder.memberEditId.setError("Please enter id");
                }else if(holder.memberEditName.getText()==null||holder.memberEditName.getText().length()==0){
                    holder.memberEditName.setError("Please enter name");
                }else {
                    holder.memberEditId.setError(null);
                    holder.memberEditName.setError(null);
                    ActiveLifeApplication.getInstance().setUpDb().insertOrReplaceMember(holder.memberEditId.getText().toString(), holder.memberEditName.getText().toString());
                    fragment.setMembersData();
                }
            }
        });

        return convertView;
    }


    class ViewHolder {
        TextView memberName, memberEditName, memberId, memberEditId, memmberBarCodeId;
        LinearLayout memberDetailsLayout, cardDetailsLayout;
        ImageView updateDetails, barCodeImage, closeCradLayout;
        Button addButton;
        View cardView;

    }

}

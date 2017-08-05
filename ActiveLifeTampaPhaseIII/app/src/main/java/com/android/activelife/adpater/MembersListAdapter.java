package com.android.activelife.adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
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

import com.android.activelife.R;
import com.android.activelife.appcontroller.ActiveLifeApplication;
import com.android.activelife.db.MemberData;
import com.android.activelife.fragments.MemberFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by vsatrasala on 2/11/2017.
 */

public class MembersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context jContext;
    private List<MemberData> instructorDataResponse;
    private MemberFragment fragment;

    public MembersListAdapter(Context ctx, List<MemberData> instructorDataResponse, MemberFragment fragment) {
        this.jContext = ctx;
        this.instructorDataResponse = instructorDataResponse;
        this.fragment=fragment;
    }

    @Override
    public int getItemCount() {
        return instructorDataResponse.size();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }
    // Replace the contents of a view (invoked by the layout manager)
// Replace the contents of a view (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_members, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
//        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).memberId.setText("" + instructorDataResponse.get(position).getMember_name());
        ((ViewHolder) holder).memberName.setText("" + instructorDataResponse.get(position).getMember_name());
        ((ViewHolder) holder).memberEditId.setText("" + instructorDataResponse.get(position).getMember_id());
        ((ViewHolder) holder).memberEditName.setText("" + instructorDataResponse.get(position).getMember_name());
        String barCode = instructorDataResponse.get(position).getMember_id();
        String astrticksBarcode = "*" + barCode + "*";
        Typeface typeface = Typeface.createFromAsset(jContext.getAssets(),
                "fonts/IDAutomationHC39M_FREE.otf");
        ((ViewHolder) holder).memmberBarCodeId.setTypeface(typeface);
        ((ViewHolder) holder).memmberBarCodeId.setText(astrticksBarcode);
        String sizeofQRCode = "300x300";
        String url = "https://chart.googleapis.com/chart?chs=" + sizeofQRCode
                + "&cht=qr&chl=" + URLEncoder.encode(barCode);
        Log.i("Url", url);
        try {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.chart).cacheOnDisc()
                    .build();
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(url, ((ViewHolder) holder).barCodeImage, options, new ImageLoadingListener() {
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
        ((ViewHolder) holder).updateDetails
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
                                    ((ViewHolder) holder).memberDetailsLayout.setVisibility(View.GONE);
                                    ((ViewHolder) holder).memberId.setVisibility(View.GONE);
                                    ((ViewHolder) holder).cardDetailsLayout.setVisibility(View.VISIBLE);
                                    ((ViewHolder) holder).addButton.setText("Update");
                                } else {
                                    ((ViewHolder) holder).addButton.setText("Add");
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
        ((ViewHolder) holder).memberId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ViewHolder) holder).memberDetailsLayout.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).memberId.setVisibility(View.GONE);
                ((ViewHolder) holder).cardDetailsLayout.setVisibility(View.GONE);
            }
        });
        ((ViewHolder) holder).memberDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ViewHolder) holder).memberDetailsLayout.setVisibility(View.GONE);
                ((ViewHolder) holder).memberId.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).cardDetailsLayout.setVisibility(View.GONE);
            }
        });
        ((ViewHolder) holder).closeCradLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ViewHolder) holder).memberDetailsLayout.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).memberId.setVisibility(View.GONE);
                ((ViewHolder) holder).cardDetailsLayout.setVisibility(View.GONE);
            }
        });

        ((ViewHolder) holder).addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ViewHolder) holder).memberEditId.getText()==null||((ViewHolder) holder).memberEditId.getText().length()==0){
                    ((ViewHolder) holder).memberEditId.setError("Please enter id");
                }else if(((ViewHolder) holder).memberEditName.getText()==null||((ViewHolder) holder).memberEditName.getText().length()==0){
                    ((ViewHolder) holder).memberEditName.setError("Please enter name");
                }else {
                    ((ViewHolder) holder).memberEditId.setError(null);
                    ((ViewHolder) holder).memberEditName.setError(null);
                    ActiveLifeApplication.getInstance().setUpDb().insertOrReplaceMember(((ViewHolder) holder).memberEditId.getText().toString(), ((ViewHolder) holder).memberEditName.getText().toString());
                    fragment.setMembersData();
                }
            }
        });
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView memberName, memberEditName, memberId, memberEditId, memmberBarCodeId;
        LinearLayout memberDetailsLayout, cardDetailsLayout;
        ImageView updateDetails, barCodeImage, closeCradLayout;
        Button addButton;
        View cardView;
        public ViewHolder(View convertView) {
            super(convertView);
            memberId = (TextView) convertView.findViewById(R.id.id);
            memberName = (TextView) convertView.findViewById(R.id.member_name);
            memmberBarCodeId = (TextView) convertView.findViewById(R.id.member_id);
            memberDetailsLayout = (LinearLayout) convertView.findViewById(R.id.membership_details);
            cardDetailsLayout = (LinearLayout) convertView.findViewById(R.id.card_details_layout);
            barCodeImage = (ImageView) convertView.findViewById(R.id.bar_code_image);
            closeCradLayout= (ImageView) convertView.findViewById(R.id.back_button);
            updateDetails = (ImageView) convertView.findViewById(R.id.edit_member);
            cardView = convertView.findViewById(R.id.add_edit_member_layout);
            addButton = (Button) cardView.findViewById(R.id.add_member);
            memberEditId = (TextView) cardView.findViewById(R.id.member_id_edit_text);
            memberEditName = (TextView) cardView.findViewById(R.id.member_name_edit_text);
        }
    }

}

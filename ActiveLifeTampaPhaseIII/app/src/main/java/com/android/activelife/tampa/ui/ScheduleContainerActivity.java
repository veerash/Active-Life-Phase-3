package com.android.activelife.tampa.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.ScheduleDateData;
import com.android.activelife.tampa.util.TypefaceSpan;
import com.android.activelife.tampa.util.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ScheduleContainerActivity extends BaseActivity {
    private TextView tvYmca, tvHours, tvDate, tvEvent, tvName, tvDesc;
    private int mScheduleId;
    private ImageView mAddToCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_container);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mAddToCalendar = (ImageView) findViewById(R.id.add_to_calendar);
        tvYmca = (TextView) findViewById(R.id.tv_ymca);
        tvHours = (TextView) findViewById(R.id.tv_start_end_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEvent = (TextView) findViewById(R.id.tv_event);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
        mScheduleId = getIntent().getIntExtra("schedule_id", 0);
        SpannableString s = new SpannableString(getIntent().getStringExtra("schedule_name"));
        s.setSpan(new TypefaceSpan(this, "Verdana.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        setTitle(s);
//        setTitle(getIntent().getStringExtra("schedule_name"));
        final ScheduleDateData dateData = ActiveLifeApplication.getInstance().setUpDb().getScheduleDateOfId("" + mScheduleId);
        tvEvent.setText("" + dateData.getClass_name());
        tvYmca.setText("" + dateData.getLocation_name());
        tvName.setText("" + dateData.getInstructor_name());
        tvDesc.setText("" + dateData.getClass_desc());
        tvHours.setText(Utils.getApplyiedDateType(dateData.getSchedule_start_time(), "HH:mm:ss", "hh:mm a") + " - " + Utils.getApplyiedDateType(dateData.getSchedule_end_time(), "HH:mm:ss", "hh:mm a"));
        String date=getIntent().getExtras().getString("date");
             date=   Utils.getApplyiedDateType(date, "yyyy-MM-dd", "MMMM dd");
        if(date.contains("01")||date.contains("21")||date.contains("31")){
            date =date+"st";
        }else if(date.contains("02")||date.contains("22")){
            date =date+"nd";
        }else if(date.contains("03")||date.contains("23")){
            date =date+"rd";
        }else{
            date =date +"th";
        }
        tvDate.setText("" +date );
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date startDate = null, endDate = null;
        try {
            startDate = df.parse(dateData.getSchedule_start_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = df.parse(dateData.getSchedule_end_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mAddToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endtime = dateData.getSchedule_end_time() + " " + Utils.getApplyiedDateType(dateData.getSchedule_start_date(), "yyyy-MM-dd HH:mm:ss", "MMMM dd, yyyy");
                String starttime = dateData.getSchedule_start_time() + " " + Utils.getApplyiedDateType(dateData.getSchedule_start_date(), "yyyy-MM-dd HH:mm:ss", "MMMM dd, yyyy");
                Date d1 = Utils.getDatescheduleDetailsFromString(endtime);
                Date d = Utils.getDatescheduleDetailsFromString(starttime);
                long duration = d1.getTime() - d.getTime();
//                if (dateData.getSchedule_frequency().equalsIgnoreCase("MONTHLY")) {
//                    addEvent(dateData.getClass_name()
//                            + "-"
//                            + dateData.getSchedule_type(), dateData.getClass_desc(), d, duration, "" + dateData.getSchedule_id(), dateData.getLocation_name(), dateData.getSchedule_frequency());
//                } else if (dateData.getSchedule_frequency().equalsIgnoreCase("YEARLY")) {
//
//                    addEvent(dateData.getClass_name()
//                            + "-"
//                            + dateData.getSchedule_type(), dateData.getClass_desc(), d, duration, "" + dateData.getSchedule_id(), dateData.getLocation_name(), dateData.getSchedule_frequency());
//                } else if (dateData.getSchedule_frequency().equalsIgnoreCase("DAILY")) {
                    addEvent(dateData.getClass_name()
                            + "-"
                            + dateData.getSchedule_type(), dateData.getClass_desc(), d, duration, "" + dateData.getSchedule_id(), dateData.getLocation_name(), dateData.getSchedule_frequency());
//                }

            }
        });

    }
}

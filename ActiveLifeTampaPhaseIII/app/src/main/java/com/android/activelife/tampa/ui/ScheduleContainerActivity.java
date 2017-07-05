package com.android.activelife.tampa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.ScheduleDateData;
import com.android.activelife.tampa.util.Utilities;
import com.android.activelife.tampa.util.Utils;

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
        mAddToCalendar= (ImageView) findViewById(R.id.add_to_calendar);
        tvYmca = (TextView) findViewById(R.id.tv_ymca);
        tvHours = (TextView) findViewById(R.id.tv_start_end_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEvent = (TextView) findViewById(R.id.tv_event);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
        mScheduleId = getIntent().getIntExtra("schedule_id", 0);
        setTitle(getIntent().getStringExtra("schedule_name"));
        ScheduleDateData dateData = ActiveLifeApplication.getInstance().setUpDb().getScheduleDateOfId("" + mScheduleId);
        tvEvent.setText("" + dateData.getClass_name());
        tvYmca.setText("" + dateData.getLocation_name());
        tvName.setText("" + dateData.getInstructor_name());
        tvDesc.setText("" + dateData.getClass_desc());
        tvHours.setText(Utils.getApplyiedDateType(dateData.getSchedule_start_time(),"HH:mm:ss","hh:mm a")+" - "+ Utils.getApplyiedDateType(dateData.getSchedule_end_time(),"HH:mm:ss","hh:mm a"));
        tvDate .setText(""+ Utils.getApplyiedDateType(dateData.getSchedule_start_date(),"yyyy-mm-dd HH:mm:ss","dd MMMM"));
        mAddToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

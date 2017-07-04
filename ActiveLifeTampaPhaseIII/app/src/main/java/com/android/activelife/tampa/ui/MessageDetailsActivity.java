package com.android.activelife.tampa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.activelife.tampa.R;

public class MessageDetailsActivity extends AppCompatActivity {
    private TextView mTitle, mMessage, mLocation, mDate, mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mTitle = (TextView) findViewById(R.id.message_heading);
        mMessage = (TextView) findViewById(R.id.message_desc);
        mLocation = (TextView) findViewById(R.id.message_loc);
        mDate = (TextView) findViewById(R.id.message_date);
        mTime = (TextView) findViewById(R.id.message_time);
        mTitle.setText("" + getIntent().getStringExtra("title"));
        mMessage.setText("" + getIntent().getStringExtra("desc"));
        mLocation.setText("Location: " + getIntent().getStringExtra("location"));
        mDate.setText("Location: " + getIntent().getStringExtra("date"));
        mTime.setText("Location: " + getIntent().getStringExtra("time"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}

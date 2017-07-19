package com.android.activelife.tampa.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.util.TypefaceSpan;

public class MessageDetailsActivity extends BaseActivity {
    private TextView mTitle, mMessage, mLocation, mDate, mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        SpannableString s = new SpannableString("" + getIntent().getStringExtra("title"));
        s.setSpan(new TypefaceSpan(this, "Verdana.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

       setTitle(s);
        mTitle = (TextView) findViewById(R.id.message_heading);
        mMessage = (TextView) findViewById(R.id.message_desc);
        mLocation = (TextView) findViewById(R.id.message_loc);
        mDate = (TextView) findViewById(R.id.message_date);
        mTime = (TextView) findViewById(R.id.message_time);
//        setTitle("" + getIntent().getStringExtra("title"));
        mTitle.setText("" + getIntent().getStringExtra("title"));
        mMessage.setText("" + getIntent().getStringExtra("desc"));
        mLocation.setText("Location: " + getIntent().getStringExtra("location"));
        mDate.setText("Date: " + getIntent().getStringExtra("date"));
        mTime.setText("Time: " + getIntent().getStringExtra("time"));
    }


}

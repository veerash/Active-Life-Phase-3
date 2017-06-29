package com.android.activelife.tampa.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.SelectBranchListAdapter;
import com.android.activelife.tampa.util.Utilities;

public class SelectBranchActivity extends AppCompatActivity {

    private ListView mSelectBranchListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_branch);
        mSelectBranchListView = (ListView) findViewById(R.id.select_branch_list);
        mSelectBranchListView.setAdapter(new SelectBranchListAdapter(SelectBranchActivity.this));
        mSelectBranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mainIntent = new Intent(SelectBranchActivity.this, MainActivity.class);
                mainIntent.putExtra("title","Location "+(i+1));
                startActivity(mainIntent);
                Utilities.getSharedPrefernceData().storeValueIntoSharedPreference(getApplicationContext(),Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_NAME,"Location "+(i+1));
                finish();
            }
        });
    }
}

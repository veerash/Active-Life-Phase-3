package com.android.activelife.tampa.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.util.InternetConnectivity;
import com.android.activelife.tampa.util.Utilities;

/**
 * Created by vsatrasala on 6/29/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ProgressDialog getProgressDialog(Context ctx) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(ctx);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(getResources().getString(R.string.please_wait));
        }
        return mProgressDialog;
    }

    public void showProgressDialog(Context ctx) {
        if (!getProgressDialog(ctx).isShowing()) {
            mProgressDialog.show();
        }
    }

    public void hideProgressDialog(Context ctx) {
        if (getProgressDialog(ctx).isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public boolean checkIfInternet(Context ctx) {
        if (new InternetConnectivity(ctx).isNetworkAvailable()) {
            return true;
        } else {
            Utilities.showToast(getApplicationContext(), getResources().getString(R.string.internet_connection_message));
        }
        return false;
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

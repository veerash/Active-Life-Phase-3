package com.android.activelife.tampa.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.request.ReserveSchedule;
import com.android.activelife.tampa.services.response.ReserveScheduleData;
import com.android.activelife.tampa.util.TypefaceSpan;
import com.android.activelife.tampa.util.Utilities;
import com.android.activelife.tampa.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReserveActivity extends BaseActivity {

    private EditText mNameEditText, mEmailEditText;
    private Button mReserveButon;
    private ApiRequest mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        SpannableString s = new SpannableString("Reserve");
        s.setSpan(new TypefaceSpan(this, "Verdana.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

       setTitle(s);
        mNameEditText = (EditText) findViewById(R.id.et_name);
        mEmailEditText = (EditText) findViewById(R.id.et_email);
        mReserveButon = (Button) findViewById(R.id.btn_submit);
        mReserveButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSubmit();
            }
        });
    }

    private void validateAndSubmit() {
        if (TextUtils.isEmpty(mEmailEditText.getText().toString()))
            mEmailEditText.setError("Please enter email");
        else if (!isValidEmail(mEmailEditText.getText().toString()))
            mEmailEditText.setError("Please enter valid email");
        else if (TextUtils.isEmpty(mNameEditText.getText().toString()))
            mNameEditText.setError("Please enter name");
        else {
            getScheduleDateData();
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void getScheduleDateData() {
        if (checkIfInternet(ReserveActivity.this)) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date dt = new Date();
            ReserveSchedule request = new ReserveSchedule();
            request.setName(mNameEditText.getText().toString());
            request.setEmail(mEmailEditText.getText().toString());
            Gson gson = new Gson();
            JsonElement json = gson.toJsonTree(request);
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<ReserveScheduleData> call = mApiInterface.reserveSchedule(getIntent().getStringExtra("session_id"), Utils.getApplyiedDateType(df.format(dt), "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd"), json);
            call.enqueue(new Callback<ReserveScheduleData>() {
                @Override
                public void onResponse(Call<ReserveScheduleData> call, Response<ReserveScheduleData> response) {
                    hideProgressDialog(ReserveActivity.this);
                    if (response.isSuccessful()) {
                        Utilities.showToast(ReserveActivity.this, response.body().getMessage());
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        if (response.errorBody() != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Utilities.showToast(ReserveActivity.this, "" + jsonObject.getString("message"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }


                    }

                }

                @Override
                public void onFailure(Call<ReserveScheduleData> call, Throwable t) {
                    Utilities.showToast(ReserveActivity.this, t.getMessage());
                    hideProgressDialog(ReserveActivity.this);
                }
            });
            showProgressDialog(ReserveActivity.this);
        }
    }
}

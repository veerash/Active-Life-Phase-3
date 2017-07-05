package com.android.activelife.tampa.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.util.Utilities;

public class ReserveActivity extends BaseActivity {

    private EditText mNameEditText, mEmailEditText;
    private Button mReserveButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
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
            Utilities.showToast(ReserveActivity.this, "Your reservation is confirmed for" + mNameEditText.getText().toString() + "with the emai id" + mEmailEditText.getText().toString());
            finish();
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}

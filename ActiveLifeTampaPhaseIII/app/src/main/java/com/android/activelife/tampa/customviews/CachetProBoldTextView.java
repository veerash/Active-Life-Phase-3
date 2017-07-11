package com.android.activelife.tampa.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;

@SuppressWarnings("nls")
public class CachetProBoldTextView extends TextView {

    public CachetProBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CachetProBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CachetProBoldTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Verdana-Bold.ttf");
        setTypeface(tf);

    }

}
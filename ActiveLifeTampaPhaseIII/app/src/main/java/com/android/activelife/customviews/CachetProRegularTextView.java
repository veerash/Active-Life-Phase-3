package com.android.activelife.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.activelife.appcontroller.ActiveLifeApplication;


@SuppressWarnings("nls")
public class CachetProRegularTextView extends TextView {

	public CachetProRegularTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CachetProRegularTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CachetProRegularTextView(Context context) {
		super(context);
		init();
	}

	private void init() {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"fonts/Verdana.ttf");
			setTypeface(tf);

	}

}
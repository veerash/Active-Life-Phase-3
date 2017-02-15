package com.android.activelife.tampa.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.TextView;


@SuppressWarnings("nls")
public class CachetProRegularRadioButton extends RadioButton {

	public CachetProRegularRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CachetProRegularRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CachetProRegularRadioButton(Context context) {
		super(context);
		init();
	}

	private void init() {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"fonts/CachetPro-Book.otf");
			setTypeface(tf);

	}

}
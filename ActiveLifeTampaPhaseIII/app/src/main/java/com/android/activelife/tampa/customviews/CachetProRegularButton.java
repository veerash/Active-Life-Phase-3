package com.android.activelife.tampa.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;


@SuppressWarnings("nls")
public class CachetProRegularButton extends Button {

	public CachetProRegularButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CachetProRegularButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CachetProRegularButton(Context context) {
		super(context);
		init();
	}

	private void init() {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"fonts/Verdana.ttf");
			setTypeface(tf);

	}

}
package com.zaim.braillekeyboard;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class BrailleTextView extends TextView implements Observer {

	public BrailleTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public BrailleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public BrailleTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable observable, Object data) {
		if (observable instanceof BrailleInputModel) {
			String text = (String) data;
			setText(text);
		}

	}

}

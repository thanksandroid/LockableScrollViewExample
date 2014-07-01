package com.thanksandroid.example.lockable;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	protected TimePicker startTimePicker, endTimePicker;
	protected LockableScrollView lockableScrollView;
	protected Rect startRect, endRect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// build view references
		startTimePicker = (TimePicker) findViewById(R.id.tp_start_time);
		endTimePicker = (TimePicker) findViewById(R.id.tp_end_time);
		lockableScrollView = (LockableScrollView) findViewById(R.id.sv_lockable);
		// create empty rect objects
		startRect = new Rect();
		endRect = new Rect();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent motionEvent) {
		// x,y of touch event
		final int x = (int) motionEvent.getRawX();
		final int y = (int) motionEvent.getRawY();
		// get the coordinates of the two time pickers
		startTimePicker.getGlobalVisibleRect(startRect);
		endTimePicker.getGlobalVisibleRect(endRect);		
		if (startRect.contains(x, y) || endRect.contains(x, y)) {
			// touched the time picker, stop scrolling of scroll view
			lockableScrollView.setScrollable(false);
		} else {
			// can scroll
			lockableScrollView.setScrollable(true);
		}
		return super.dispatchTouchEvent(motionEvent);
	}
}
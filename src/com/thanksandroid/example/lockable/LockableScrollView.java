package com.thanksandroid.example.lockable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * This scroll view can be locked to stop the scrolling if some other view wants to capture the touch event.
 */
public class LockableScrollView extends ScrollView {

	// indicates whether scroll view can scroll
	private boolean mScrollable;

	public LockableScrollView(Context context) {
		super(context);
		mScrollable = true;
	}

	public LockableScrollView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		mScrollable = true;
	}

	public boolean isScrollable() {
		return mScrollable;
	}

	public void setScrollable(boolean scrollable) {
		this.mScrollable = scrollable;
	}

	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		switch (motionEvent.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (isScrollable()) {
					// allow default behaviour of ScrollView
					return super.onTouchEvent(motionEvent);
				} else {
					// otherwise, lock the scrolling					
					return mScrollable; // mScrollable is always false here
				}
			default:
				return super.onTouchEvent(motionEvent);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {		
		if (!isScrollable()) {
			// do nothing, if not scrolling
			return false;
		} else {
			// default behavior
			return super.onInterceptTouchEvent(event);
		}
	}
}
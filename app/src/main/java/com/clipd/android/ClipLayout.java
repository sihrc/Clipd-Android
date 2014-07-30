package com.clipd.android;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by sihrc on 7/30/14.
 */
public class ClipLayout extends RelativeLayout implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
	/**
	 * TAG *
	 */
	final private static String DEBUG_TAG = ClipLayout.class.getSimpleName();

	/** Window Management **/
	WindowManager manager;
	WindowManager.LayoutParams params;

	/**
	 * Gestures *
	 */
	GestureDetector mDetector;


	/** Constructors **/
	public ClipLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup(context);
	}

	public ClipLayout(Context context) {
		super(context);
		setup(context);
	}

	public ClipLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup(context);
	}

	public void setup(Context context) {
		mDetector = new GestureDetector(context, this);
		mDetector.setOnDoubleTapListener(this);
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				mDetector.onTouchEvent(motionEvent);
				return false;
			}
		});
	}

	public void setWindowManager(WindowManager manager) {
		this.manager = manager;
	}

	public void setWindowParams(WindowManager.LayoutParams params) {
		this.params = params;
	}

	@Override
	public boolean onDown(MotionEvent event) {
		d("onDown: " + event.toString());
		return true;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
	                       float velocityX, float velocityY) {
		d("onFling: " + event1.toString() + event2.toString());
		return true;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		d("onLongPress: " + event.toString());
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	                        float distanceY) {
		move(e2.getRawX(), e2.getRawY());
		return true;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		d("onShowPress: " + event.toString());
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		d("onSingleTapUp: " + event.toString());
		return true;
	}

	@Override
	public boolean onDoubleTap(MotionEvent event) {
		d("onDoubleTap: " + event.toString());
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent event) {
		d("onDoubleTapEvent: " + event.toString());
		return true;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent event) {
		d("onSingleTapConfirmed: " + event.toString());
		return true;
	}

	public void destroy() {
		manager.removeView(this);
	}

	private void move(float x, float y) {
		params.x += (int) x;
		params.y += (int) y;

		manager.updateViewLayout(this, params);
	}

	private void d(String message) {
		Log.d(DEBUG_TAG, message);
	}
}

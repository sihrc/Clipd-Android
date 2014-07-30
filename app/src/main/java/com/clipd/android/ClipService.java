package com.clipd.android;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by sihrc on 7/29/14.
 */
public class ClipService extends Service {
	/**
	 * TAG *
	 */
	final private static String DEBUG_TAG = ClipService.class.getSimpleName();

	/** View **/
	ClipLayout view;

	@Override
	public IBinder onBind(Intent intent) {
		// Not used
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		view = (ClipLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.cliphead, null, false);

		((ImageView) view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_launcher);

		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);

		params.gravity = Gravity.TOP | Gravity.LEFT;
		params.x = 0;
		params.y = 100;

		/** Setting Window Context **/
		view.setWindowManager(windowManager);
		view.setWindowParams(params);

		/** Add initial view **/
		windowManager.addView(view, params);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		view.destroy();
	}

	private void d(String message) {
		Log.d(DEBUG_TAG, message);
	}
}
package com.victor.kaiser.pendergrast.settings.types.activity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.google.android.glass.app.Card;
import com.google.android.glass.app.Card.ImageLayout;
import com.victor.kaiser.pendergrast.settings.R;

/**
 * An implementation of AbstractPreferenceActivity in which
 * the user can set a head tilt angle
 */
public class HeadTiltPreferenceActivity extends AbstractPreferenceActivity implements SensorEventListener {

	/**
	 * The minimum time in milliseconds to update the View
	 */
	private static final long UPDATE_TIME_MILLIS = 150;
	
	/**
	 * Used to make the View for us
	 */
	private Card mCard;

	private SensorManager mSensorManager;
	private Sensor mGravity;

	private float mLastReading = 0;
	private long mLastViewUpdate = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		mCard = new Card(this);
		mCard.setFootnote(R.string.tap_to_set_angle);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
		
		setContentView(mCard.getView());
	}

	@Override
	public void onResume() {
		super.onResume();

		// Get updates from gravity sensor
		mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onStop() {
		super.onStop();

		// Stop sensor updates
		mSensorManager.unregisterListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// When touchpad is tapped, set the preference's value and finish()
		if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
			setPreferenceFloat(mLastReading);
			
			playSuccessSound();
			
			finish();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// Don't constantly update the View, only every UPDATE_TIME_MILLIS
		if (System.currentTimeMillis() - mLastViewUpdate > UPDATE_TIME_MILLIS) {
			mLastReading = event.values[2];

			// Convert the meters per second acceleration to angle
			double angle = Math.asin(mLastReading / 9.80665f);
			
			// Convert from radians to degrees and limit decimal places
			angle =	new BigDecimal(angle * -180 / Math.PI)
						.setScale(1, RoundingMode.HALF_UP)
						.doubleValue();
			
			mCard.setText(" " + angle + "\n degrees");
			
			setContentView(mCard.getView());

			mLastViewUpdate = System.currentTimeMillis();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Nothing to do
	}

}

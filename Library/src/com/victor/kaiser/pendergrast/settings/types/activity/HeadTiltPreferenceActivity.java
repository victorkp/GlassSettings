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

import com.google.android.glass.app.Card;
import com.google.android.glass.app.Card.ImageLayout;
import com.victor.kaiser.pendergrast.settings.R;

public class HeadTiltPreferenceActivity extends AbstractPreferenceActivity implements SensorEventListener {

	/**
	 * The minimum time in milliseconds to update the View
	 */
	private static final long UPDATE_TIME_MILLIS = 100;
	
	/**
	 * Used to make the View for us
	 */
	private Card mCard;

	private SensorManager mSensorManager;
	private Sensor mGravity;

	private long mLastViewUpdate = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mCard.setImageLayout(ImageLayout.FULL);
		mCard.addImage(R.drawable.ic_angle_50);
		mCard.setFootnote(R.string.tap_to_set_angle);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
	}

	@Override
	public void onResume() {
		super.onResume();

		mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onStop() {
		super.onStop();

		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// Don't constantly update the View
		if (System.currentTimeMillis() - mLastViewUpdate > UPDATE_TIME_MILLIS) {

			// Convert the meters per second acceleration to angle
			double angle = new BigDecimal(Math.asin(event.values[2] / 9.8f))
								.setScale(3, RoundingMode.HALF_UP)
								.doubleValue();
			
			mCard.setText(angle + " degrees");

			mLastViewUpdate = System.currentTimeMillis();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do nothing
	}

}

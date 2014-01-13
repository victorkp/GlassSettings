package com.victor.kaiser.pendergrast.settings.types;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.victor.kaiser.pendergrast.settings.R;

public class TogglePreference extends AbstractPreference {

	boolean mEnabled = false;

	public TogglePreference(SharedPreferences prefs, String key, String title) {
		super(prefs, key, title);

		mEnabled = getBoolean(key);
		
		setToggleImage();
	}

	public TogglePreference(SharedPreferences prefs, String key, String title, boolean defaultValue) {
		super(prefs, key, title);

		mEnabled = getBoolean(key, defaultValue);
		
		setToggleImage();
	}
	
	@Override
	public boolean onSelect() {
		mEnabled = !mEnabled;
		putBoolean(mPreferenceKey, mEnabled);

		setToggleImage();
		
		// Return true because everything is done
		return true;
	}

	@Override
	public View getCard(Context context) {
		return getDefaultCard(context);
	}

	@Override
	public String[] getOptions() {
		// TogglePreference handles everything by itself
		return null;
	}
	
	private void setToggleImage(){
		setImageResource((mEnabled) ? (R.drawable.ic_done_50) : (R.drawable.ic_no_50));
	}

}

package com.victor.kaiser.pendergrast.settings.types;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.kaiser.pendergrast.settings.R;

public abstract class AbstractPreference {

	protected String mPreferenceKey;
	protected String mTitle;
	protected SharedPreferences mPrefs;
	protected int mImageResource;

	public AbstractPreference(SharedPreferences prefs, String key, String title) {
		mPrefs = prefs;
		mPreferenceKey = key;
		mTitle = title;
		mImageResource = -1;
	}

	public AbstractPreference(SharedPreferences prefs, String key, String title, int imageResource) {
		mPrefs = prefs;
		mPreferenceKey = key;
		mTitle = title;
		mImageResource = imageResource;
	}

	/**
	 * Called when this preference is tapped in a GlassPreferenceActivity
	 * 
	 * @return true if this method does everything needed, false if the
	 *         GlassPreferenceActivity should show the options in getOptions()
	 */
	public abstract boolean onSelect();

	public abstract View getCard(Context context);

	public abstract String[] getOptions();

	/**
	 * @param context
	 *            Activity/Service context
	 * @return A card that has this Preference's title and image (if any)
	 */
	protected View getDefaultCard(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View card = inflater.inflate(R.layout.default_preference, null);
		
		((TextView) card.findViewById(R.id.card_text)).setText(getTitle());
		
		if(getImageResource() != -1){
			((ImageView) card.findViewById(R.id.card_image)).setImageResource(getImageResource());
		}

		return card;
	}

	public String getKey() {
		return mPreferenceKey;
	}

	public void setKey(String key) {
		mPreferenceKey = key;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public int getImageResource() {
		return mImageResource;
	}

	public void setImageResource(int imageResource) {
		mImageResource = imageResource;
	}

	protected boolean getBoolean(String key) {
		return mPrefs.getBoolean(key, false);
	}

	protected boolean getBoolean(String key, boolean defaultValue) {
		return mPrefs.getBoolean(key, defaultValue);
	}

	protected void putBoolean(String key, boolean value) {
		mPrefs.edit().putBoolean(key, value).commit();
	}

}

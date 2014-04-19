/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.types;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.kaiser.pendergrast.settings.R;
import com.victor.kaiser.pendergrast.settings.option.PreferenceOption;

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

	/**
	 * If onSelect() does not return true, this method will be called on click.
	 * This method needs to return a non-null value,
	 * so that GlassPreferenceActivity can populate a Menu
	 * @return An ArrayList of PreferenceOptions
	 */
	public abstract List<PreferenceOption> getOptions();
	
	/**
	 * If onSelect() is non-null, this method will be called
	 * when one of the PreferenceOptions in getOptions() is tapped
	 * @param index
	 */
	public abstract void onOptionSelected(int index);
	
	/**
	 * Build a View to represent this Preference.
	 * getDefaultCard() can generate a View with a title and image.
	 * @param context
	 * @return a View to represent this Preference
	 */
	public abstract View getCard(Context context);

	/**
	 * Return true to have the GlassPreferenceActivity play a success sound on tap,
	 * false to play the tap sound
	 */
	public boolean playSuccessSoundOnSelect(){
		return true;
	}

	/**
	 * Builds a View with this Preference's title and image (if any)
	 * @param context
	 *            Activity/Service context
	 * @return A card that has this Preference's title and image (if any)
	 */
	protected View getDefaultCard(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View card = inflater.inflate(R.layout.default_preference, null);
		
		((TextView) card.findViewById(R.id.card_text)).setText(Html.fromHtml(getTitle()));
		
		if(getImageResource() != -1){
			((ImageView) card.findViewById(R.id.card_image)).setImageResource(getImageResource());
		} else {
			// No image, so don't measure/show the ImageView
			card.findViewById(R.id.card_image).setVisibility(View.GONE);
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

	protected boolean getBoolean() {
		return mPrefs.getBoolean(mPreferenceKey, false);
	}

	protected boolean getBoolean(boolean defaultValue) {
		return mPrefs.getBoolean(mPreferenceKey, defaultValue);
	}

	protected void putBoolean(boolean value) {
		mPrefs.edit().putBoolean(mPreferenceKey, value).commit();
	}
	
	protected int getInt() {
		return mPrefs.getInt(mPreferenceKey, 0);
	}

	protected int getInt(int defaultValue) {
		return mPrefs.getInt(mPreferenceKey, defaultValue);
	}

	protected void putInt(int value) {
		mPrefs.edit().putInt(mPreferenceKey, value).commit();
	}

}

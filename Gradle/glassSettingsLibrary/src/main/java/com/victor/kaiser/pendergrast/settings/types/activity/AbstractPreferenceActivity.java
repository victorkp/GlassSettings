/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.types.activity;

import java.util.HashSet;
import java.util.Set;

import com.google.android.glass.media.Sounds;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Extend this class to create a PreferenceActivity.
 * Preference Activities represent cases where more than
 * a simple tap or list selection is needed.
 * See HeadTiltPreferenceActivity for an example.
 */
public abstract class AbstractPreferenceActivity extends Activity {
	private static final String EXTRA_PREFERENCE_KEY = "preference_key";
	
	private SharedPreferences mPrefs;
	private String mPreferenceKey;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		mPreferenceKey = getIntent().getExtras().getString(EXTRA_PREFERENCE_KEY);
	}
	
	/**
	 * Play the standard Glass success sound
	 */
	protected void playSuccessSound() {
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audio.playSoundEffect(Sounds.SUCCESS);
	}

	/**
	 * Play the standard Glass tap sound
	 */
	protected void playClickSound() {
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audio.playSoundEffect(Sounds.TAP);
	}
	
	protected String getPreferenceKey(){
		return mPreferenceKey;
	}
	
	protected String getPreferenceString(){
		return mPrefs.getString(mPreferenceKey, "");
	}
	
	protected String getPreferenceString(String defaultValue){
		return mPrefs.getString(mPreferenceKey, defaultValue);
	}

	protected void setPreferenceString(String value){
		mPrefs.edit().putString(mPreferenceKey, value).apply();
	}
	
	protected int getPreferenceInt(){
		return mPrefs.getInt(mPreferenceKey, 0);
	}
	
	protected int getPreferenceInt(int defaultValue){
		return mPrefs.getInt(mPreferenceKey, defaultValue);
	}

	protected void setPreferenceInt(int value){
		mPrefs.edit().putInt(mPreferenceKey, value).apply();
	}
	
	protected boolean getPreferenceBoolean(){
		return mPrefs.getBoolean(mPreferenceKey, false);
	}
	
	protected boolean getPreferenceBoolean(boolean defaultValue){
		return mPrefs.getBoolean(mPreferenceKey, defaultValue);
	}

	protected void setPreferenceBoolean(boolean value){
		mPrefs.edit().putBoolean(mPreferenceKey, value).apply();
	}
	
	protected float getPreferenceFloat(){
		return mPrefs.getFloat(mPreferenceKey, 0);
	}
	
	protected float getPreferenceFloat(float defaultValue){
		return mPrefs.getFloat(mPreferenceKey, defaultValue);
	}

	protected void setPreferenceFloat(float value){
		mPrefs.edit().putFloat(mPreferenceKey, value).apply();
	}
	
	protected long getPreferenceLong(){
		return mPrefs.getLong(mPreferenceKey, 0);
	}
	
	protected long getPreferenceLong(long defaultValue){
		return mPrefs.getLong(mPreferenceKey, defaultValue);
	}

	protected void setPreferenceLong(long value){
		mPrefs.edit().putLong(mPreferenceKey, value).apply();
	}
	
	protected Set<String> getPreferenceStringSet(){
		return mPrefs.getStringSet(mPreferenceKey, new HashSet<String>(0));
	}
	
	protected Set<String> getPreferenceStringSet(Set<String> defaultValue){
		return mPrefs.getStringSet(mPreferenceKey, defaultValue);
	}

	protected void setPreferenceStringSet(Set<String> value){
		mPrefs.edit().putStringSet(mPreferenceKey, value).apply();
	}

}

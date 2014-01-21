/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.types.activity;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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
	
	protected String getPreferenceKey(){
		return mPreferenceKey;
	}
	
	protected String getPreferenceString(){
		return mPrefs.getString(mPreferenceKey, "");
	}
	
	protected String getPreferenceString(String defaultValue){
		return mPrefs.getString(mPreferenceKey, defaultValue);
	}
	
	protected int getPreferenceInt(){
		return mPrefs.getInt(mPreferenceKey, 0);
	}
	
	protected int getPreferenceInt(int defaultValue){
		return mPrefs.getInt(mPreferenceKey, defaultValue);
	}
	
	protected boolean getPreferenceBoolean(){
		return mPrefs.getBoolean(mPreferenceKey, false);
	}
	
	protected boolean getPreferenceBoolean(boolean defaultValue){
		return mPrefs.getBoolean(mPreferenceKey, defaultValue);
	}
	
	protected float getPreferenceFloat(){
		return mPrefs.getFloat(mPreferenceKey, 0);
	}
	
	protected float getPreferenceFloat(float defaultValue){
		return mPrefs.getFloat(mPreferenceKey, defaultValue);
	}
	
	protected long getPreferenceLong(){
		return mPrefs.getLong(mPreferenceKey, 0);
	}
	
	protected long getPreferenceLong(long defaultValue){
		return mPrefs.getLong(mPreferenceKey, defaultValue);
	}
	
	protected Set<String> getPreferenceStringSet(){
		return mPrefs.getStringSet(mPreferenceKey, new HashSet<String>(0));
	}
	
	protected Set<String> getPreferenceStringSet(Set<String> defaultValue){
		return mPrefs.getStringSet(mPreferenceKey, defaultValue);
	}
	

}

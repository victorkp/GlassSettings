/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.types;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;

import com.victor.kaiser.pendergrast.settings.option.PreferenceOption;

public class ActivityPreference extends AbstractPreference {
	private static final String EXTRA_PREFERENCE_KEY = "preference_key";

	private Class<?> mActivityClass;
	private Context mContext;

	public ActivityPreference(Context context, SharedPreferences prefs, String key, String title, Class<?>activity) {
		super(prefs, key, title);
		mContext = context;
		mActivityClass = activity;
	}

	public ActivityPreference(Context context, SharedPreferences prefs, String key, String title, int imageResource, Class<?> activity) {
		super(prefs, key, title, imageResource);
		mContext = context;
		mActivityClass = activity;
	}

	@Override
	public boolean onSelect(){
		// Launch the specified Activity
		Intent launchIntent = new Intent(mContext, mActivityClass);
		launchIntent.putExtra(EXTRA_PREFERENCE_KEY, getKey());
		mContext.startActivity(launchIntent);
		return true;
	}

	@Override
	public boolean playSuccessSoundOnSelect(){
		return false;
	}

	@Override
	public View getCard(Context context){
		return getDefaultCard(context);
	}

	@Override
	public List<PreferenceOption> getOptions() {
		// onSelect returns true, so this will not be called
		return null;
	}
	
	@Override
	public void onOptionSelected(int index){
		// onSelect returns true, so this will not be called
	}
}

/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.types;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.victor.kaiser.pendergrast.settings.option.PreferenceOption;

/**
 * A preference that displays a list of options
 */
public class ChooserPreference extends AbstractPreference{

	private SharedPreferences mPrefs;
	
	private int mSelection = 0;
	private List<PreferenceOption> mOptions;  
	
	/**
	 * The "prompt" for the title
	 */
	private String mBaseTitle;

	public ChooserPreference(SharedPreferences prefs, String key, String title, List<PreferenceOption> options) {
		super(prefs, key, title);
		mBaseTitle = title;
		mOptions = options;
		mSelection = getInt();
	}
	
	public ChooserPreference(SharedPreferences prefs, String key, String title, int imageResource, List<PreferenceOption> options) {
		super(prefs, key, title, imageResource);
		mBaseTitle = title;
		mOptions = options;
		mSelection = getInt();
	}

	public ChooserPreference(SharedPreferences prefs, String key, String title, List<PreferenceOption> options, int defaultValueIndex) {
		super(prefs, key, title);
		mBaseTitle = title;
		mOptions = options;
		mSelection = getInt(defaultValueIndex);
	}
	
	public ChooserPreference(SharedPreferences prefs, String key, String title, int imageResource, List<PreferenceOption> options, int defaultValueIndex) {
		super(prefs, key, title, imageResource);
		mBaseTitle = title;
		mOptions = options;
		mSelection = getInt(defaultValueIndex);
	}
	
	
	@Override
	public boolean onSelect() {
		// Return false, because a menu should be generated from the items in getOptions()
		return false;
	}

	@Override
	public View getCard(Context context) {
		// Update the title to be the prompt + the selected item
		// Example:
		//		Update every...
		//			Two hours
		updateTitle();
		
		return getDefaultCard(context);
	}

	@Override
	public List<PreferenceOption> getOptions() {
		return mOptions;
	}
	
	@Override
	public void onOptionSelected(int index){
		putInt(index);
		mSelection = index;
	}
	
	/**
	 * Make the title from the title and the current value
	 */
	private void updateTitle(){
		setTitle("<b>" + mBaseTitle + "</b> " + mOptions.get(mSelection).getTitle());
	}
	
}

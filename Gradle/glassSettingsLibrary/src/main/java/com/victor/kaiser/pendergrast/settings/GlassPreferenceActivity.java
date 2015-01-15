/**
 * @author Victor Kaiser-Pendergrast
 */

package com.victor.kaiser.pendergrast.settings;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollView;
import com.victor.kaiser.pendergrast.settings.adapter.CardPreferenceAdapter;
import com.victor.kaiser.pendergrast.settings.option.PreferenceOption;
import com.victor.kaiser.pendergrast.settings.types.AbstractPreference;
import com.victor.kaiser.pendergrast.settings.types.ChooserPreference;
import com.victor.kaiser.pendergrast.settings.types.TogglePreference;
import com.victor.kaiser.pendergrast.settings.types.ActivityPreference;
import com.victor.kaiser.pendergrast.settings.types.activity.HeadTiltPreferenceActivity;

/**
 * Extend GlassPreferenceActivity to quickly create a list of preferences.
 * In onCreate() make calls to add preferences [i.e. addTooglePreference(...) ]
 * and then call buildAndShowOptions(). See MainActivity in the demo project for an example.
 */
public class GlassPreferenceActivity extends Activity implements OnItemClickListener {

	private CardScrollView mScrollView;
	private CardPreferenceAdapter mAdapter;

	private SharedPreferences mPrefs;

	// These are used for Preferences like ChooserPreference
	// that return false for onSelected() and make
	// the OptionsMenu open with options
	private List<PreferenceOption> mCurrentOptions;
	private AbstractPreference mCurrentPreference;
	private int mCurrentPreferenceIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		mScrollView = new CardScrollView(this);
		mAdapter = new CardPreferenceAdapter(this);

		mScrollView.setOnItemClickListener(this);
	}

	protected void buildAndShowOptions() {
		mAdapter.buildCards();
		mScrollView.setAdapter(mAdapter);
		mScrollView.activate();
		setContentView(mScrollView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		for (int i = 0; i < mCurrentOptions.size(); i++) {
			menu.add(Menu.NONE, i, i, mCurrentOptions.get(i).getTitle());
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mCurrentPreference.onOptionSelected(item.getItemId());
		
		// Update the View for this AbstractPreference
		// in case the View changed
		mAdapter.buildCard(mCurrentPreferenceIndex);
		mAdapter.notifyDataSetChanged();
		
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> view, View clicked, int index, long id) {
		AbstractPreference preference = (AbstractPreference) mAdapter.getItem(index);
		if (preference.onSelect()) {
			// True: the AbstractPreference handled everything

			if(preference.playSuccessSoundOnSelect()){
				playSuccessSound();
			}else{
				playClickSound();
			}

			// Update the View for this AbstractPreference
			// in case the View changed
			mAdapter.buildCard(index);
			mAdapter.notifyDataSetChanged();
		} else {
			// False: show the AbstractPreference's options as a Menu

			playClickSound();

			if (preference instanceof ChooserPreference) {
				mCurrentOptions = ((ChooserPreference) preference).getOptions();
				mCurrentPreference = preference;
				mCurrentPreferenceIndex = index;
				
				invalidateOptionsMenu();
				openOptionsMenu();
			}
		}
	}

	/**
	 * Add a Preference that can be on or off
	 */
	protected void addTogglePreference(String key, String title) {
		mAdapter.addPreference(new TogglePreference(mPrefs, key, title));
	}

	/**
	 * Add a Preference that can be on or off
	 */
	protected void addTogglePreference(String key, String title, boolean defaultValue) {
		mAdapter.addPreference(new TogglePreference(mPrefs, key, title, defaultValue));
	}

	/**
	 * Add a Preference that gives a list of options. The index of the chosen option is saved.
	 */
	protected void addChoicePreference(String key, String title, List<PreferenceOption> options) {
		mAdapter.addPreference(new ChooserPreference(mPrefs, key, title, options));
	}

	/**
	 * Add a Preference that gives a list of options. The index of the chosen option is saved.
	 */
	protected void addChoicePreference(String key, String title, List<PreferenceOption> options, int defaultValueIndex) {
		mAdapter.addPreference(new ChooserPreference(mPrefs, key, title, options, defaultValueIndex));
	}

	/**
	 * Add a Preference that launches a specified Activity, passing in the preference key as an extra.
	 * For ease of use, extend AbstractPreferenceActivity for the Activity to launch
	 */
	protected void addActivityPreference(String key, String title, Class<?> activityClass){
		mAdapter.addPreference(new ActivityPreference(this, mPrefs, key, title, activityClass));
	}

	/**
	 * Add a Preference that launches a specified Activity, passing in the preference key as an extra.
	 * For ease of use, extend AbstractPreferenceActivity for the Activity to launch
	 */
	protected void addActivityPreference(String key, String title, int imageResource, Class<?> activityClass){
		mAdapter.addPreference(new ActivityPreference(this, mPrefs, key, title, imageResource, activityClass));
	}
	
	/**
	 * Add a Preference that allows the user to specify head tilt. The value put into the preference is 
	 * an acceleration in meters/second^2 along Glass's Z axis.
	 */
	protected void addHeadTiltPreference(String key, String title){
		mAdapter.addPreference(new ActivityPreference(this, mPrefs, key, title, R.drawable.ic_angle_50, HeadTiltPreferenceActivity.class));
	}

	/**
	 * Add a Preference that allows the user to specify head tilt. The value put into the preference is 
	 * an acceleration in meters/second^2 along Glass's Z axis.
	 */
	protected void addHeadTiltPreference(String key, String title, int imageResource){
		mAdapter.addPreference(new ActivityPreference(this, mPrefs, key, title, imageResource, HeadTiltPreferenceActivity.class));
	}
	
	/**
	 * Add a generic Preference
	 */
	protected void addPreference(AbstractPreference preference) {
		mAdapter.addPreference(preference);
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

}

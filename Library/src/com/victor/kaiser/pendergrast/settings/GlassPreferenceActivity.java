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

			playSuccessSound();

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

	protected void addTogglePreference(String key, String title) {
		mAdapter.addPreference(new TogglePreference(mPrefs, key, title));
	}

	protected void addTogglePreference(String key, String title, boolean defaultValue) {
		mAdapter.addPreference(new TogglePreference(mPrefs, key, title, defaultValue));
	}

	protected void addChoicePreference(String key, String title, List<PreferenceOption> options) {
		mAdapter.addPreference(new ChooserPreference(mPrefs, key, title, options));
	}

	protected void addChoicePreference(String key, String title, List<PreferenceOption> options, int defaultValueIndex) {
		mAdapter.addPreference(new ChooserPreference(mPrefs, key, title, options, defaultValueIndex));
	}
	
	protected void addActivityPreference(String key, String title, Class<?> activityClass){
		mAdapter.addPreference(new ActivityPreference(this, mPrefs,  key, title, activityClass));
	}

	protected void addActivityPreference(String key, String title, int imageResource, Class<?> activityClass){
		mAdapter.addPreference(new ActivityPreference(this, mPrefs,  key, title, imageResource, activityClass));
	}

	protected void addPreference(AbstractPreference preference) {
		mAdapter.addPreference(preference);
	}

	private void playSuccessSound() {
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audio.playSoundEffect(Sounds.SUCCESS);
	}

	private void playClickSound() {
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audio.playSoundEffect(Sounds.TAP);
	}

}

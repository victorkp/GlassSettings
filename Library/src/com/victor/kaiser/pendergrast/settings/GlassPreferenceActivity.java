package com.victor.kaiser.pendergrast.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollView;
import com.victor.kaiser.pendergrast.settings.adapter.CardPreferenceAdapter;
import com.victor.kaiser.pendergrast.settings.types.AbstractPreference;
import com.victor.kaiser.pendergrast.settings.types.TogglePreference;

public class GlassPreferenceActivity extends Activity implements OnItemClickListener {

	private CardScrollView mScrollView;
	private CardPreferenceAdapter mAdapter;
	
	private SharedPreferences mPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		mScrollView = new CardScrollView(this);
		mAdapter = new CardPreferenceAdapter(this);

		mScrollView.setOnItemClickListener(this);
	}

	protected void finishOnCreate() {
		mAdapter.buildCards();
		mScrollView.setAdapter(mAdapter);
		mScrollView.activate();
		setContentView(mScrollView);
	}

	@Override
	public void onItemClick(AdapterView<?> view, View clicked, int index, long id) {
		AbstractPreference preference = (AbstractPreference) mAdapter.getItem(index);
		if (preference.onSelect()) {
			// True: the AbstractPreference handled everything
			// Update the Card for this AbstractPreference

			playSuccessSound();

			mAdapter.buildCard(index);
			mAdapter.notifyDataSetChanged();
		} else {
			// False: show the AbstractPreference's options

			playClickSound();
		}
	}
	
	protected void addTogglePreference(String key, String title){
		mAdapter.addPreference(new TogglePreference(mPrefs, key, title));
	}
	
	protected void addTogglePreference(String key, String title, boolean defaultValue){
		mAdapter.addPreference(new TogglePreference(mPrefs, key, title, defaultValue));
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

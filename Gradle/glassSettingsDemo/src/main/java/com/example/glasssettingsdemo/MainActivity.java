/**
 * @author Victor Kaiser-Pendergrast
 */

package com.example.glasssettingsdemo;

import android.os.Bundle;

import com.victor.kaiser.pendergrast.settings.GlassPreferenceActivity;
import com.victor.kaiser.pendergrast.settings.option.OptionsBuilder;

public class MainActivity extends GlassPreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Add a toggle preference
		// The SharedPreference's key is "toggle1"
		// The title is shown as "Toggle 1"
		addTogglePreference("toggle1", "Toggle 1");
		
		
		// Add a Preference with multiple choices
		OptionsBuilder syncOptions = new OptionsBuilder();
		syncOptions.addOption("Every hour")
					.addOption("Every two hours")
					.addOption("Once per day")
					.addOption("Never");
		addChoicePreference("choice", "Sync news", syncOptions.build());
		
		// Add a preference that launches an Activity
		addActivityPreference("test-key", "DemoPreferenceActivity", DemoPreferenceActivity.class);

		// Add a head tilt preference
		addHeadTiltPreference("head-tilt", "Head Tilt Threshold");

		// Add a toggle preference that defaults to true
		addTogglePreference("toggle2", "Toggle 2", true);
		
		
		// Builds all the preferences and shows them in a CardScrollView
		buildAndShowOptions();
	}


}

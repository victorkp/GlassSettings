package com.example.glasssettingsdemo;

import com.victor.kaiser.pendergrast.settings.GlassPreferenceActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends GlassPreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addTogglePreference("toggle1", "Toggle 1", true);
		addTogglePreference("toggle2", "Toggle 2", true);
		addTogglePreference("toggle3", "Toggle 3", true);
		
		finishOnCreate();
	}


}

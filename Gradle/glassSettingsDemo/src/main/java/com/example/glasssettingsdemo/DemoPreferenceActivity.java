package com.example.glasssettingsdemo;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.victor.kaiser.pendergrast.settings.types.activity.AbstractPreferenceActivity;


public class DemoPreferenceActivity extends AbstractPreferenceActivity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);
		textView.setText(Html.fromHtml("<b>Preference key</b>: " + getPreferenceKey() + 
										"<br><b>Preference Value:</b> " + getPreferenceString("test-value")));
		textView.setTextSize(50);
		textView.setTextColor(0xFFFFFFFF);
		
		setContentView(textView);
	}

}

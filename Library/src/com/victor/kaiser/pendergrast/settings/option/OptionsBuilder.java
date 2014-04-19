/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.option;

import java.util.ArrayList;
import java.util.List;

public class OptionsBuilder {
	
	private List<PreferenceOption> mOptions;
	
	public OptionsBuilder(){
		mOptions = new ArrayList<PreferenceOption>();
	}
	
	public OptionsBuilder addOption(String title){
		mOptions.add(new PreferenceOption(title));
		return this;
	}
	
	public OptionsBuilder addOption(String title, int imageResource){
		mOptions.add(new PreferenceOption(title, imageResource));
		return this;
	}
	
	public List<PreferenceOption> build(){
		return mOptions;
	}
	
}

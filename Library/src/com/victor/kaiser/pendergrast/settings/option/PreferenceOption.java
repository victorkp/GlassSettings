/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.option;

public class PreferenceOption {
	
	private String mTitle;
	private int mImageResource;
	
	public PreferenceOption(String title){
		mTitle = title;
		mImageResource = -1;
	}
	
	public PreferenceOption(String title, int imageResource){
		mTitle = title;
		mImageResource = imageResource;
	}
	
	public String getTitle(){
		return mTitle;
	}
	
	public int getImageResource(){
		return mImageResource;
	}
	

}

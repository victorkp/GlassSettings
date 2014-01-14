/**
 * @author Victor Kaiser-Pendergrast
 */


package com.victor.kaiser.pendergrast.settings.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.victor.kaiser.pendergrast.settings.types.AbstractPreference;

public class CardPreferenceAdapter extends CardScrollAdapter{
	
	private ArrayList<AbstractPreference> mPreferences;
	private ArrayList<View> mCards;
	
	private Context mContext;
	
	public CardPreferenceAdapter(Context context){
		mContext = context;
		mPreferences = new ArrayList<AbstractPreference>();
		mCards = new ArrayList<View>();
	}
	
	public void addPreference(AbstractPreference preference){
		mPreferences.add(preference);
	}
	
	/**
	 * Builds Cards from all of the AbstractPreferences 
	 * in mPreferences. Call this before setting the Adapter
	 * and showing the CardScrollAdapter
	 */
	public void buildCards(){
		for(AbstractPreference preference : mPreferences){
			mCards.add(preference.getCard(mContext));
		}
	}
	
	/**
	 * Used to build the Card of a specific 
	 * @param index The index of the AbstractPreference to build the card for
	 */
	public void buildCard(int index){
		mCards.set(index, mPreferences.get(index).getCard(mContext));
	}
	
	@Override
	public int findIdPosition(Object arg0) {
		return -1;
	}

	@Override
	public int findItemPosition(Object arg0) {
		return mCards.indexOf(arg0);
	}

	@Override
	public int getCount() {
		return mCards.size();
	}

	@Override
	public Object getItem(int index) {
		return mPreferences.get(index);
	}

	@Override
	public View getView(int index, View convertView, ViewGroup viewGroup) {
		return mCards.get(index);
	}
}

package com.melontech.pokerclock.utils;

import java.util.ArrayList;

import com.melontech.pokerclock.statsobjects.AbstractListModel;
import com.melontech.pokerclock.statsobjects.PayoutsListModel;

public class ObjectParser {
	public static final int BLINDS_TYPE = 0;
	public static final int TOURNAMENT_TYPE = 1;
	public static final int PAYOUTS_TYPE = 2;
	@SuppressWarnings("unchecked")
	public static <T extends AbstractListModel> ArrayList<T> parseObject(int type, String stringToParse) {
		final ArrayList<T> objectCollection = new ArrayList<T>();
		switch(type){
			case PAYOUTS_TYPE:
				
				String[] arr = stringToParse.split(",");
				int place = 0;
				for(String s : arr) {
					
					PayoutsListModel payoutObj = new PayoutsListModel(place + 1, s);
					objectCollection.add((T)payoutObj);
					place++;
				}
				
				break;
		}
		return objectCollection;
		
	}
}

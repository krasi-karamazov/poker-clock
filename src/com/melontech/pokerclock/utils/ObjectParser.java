package com.melontech.pokerclock.utils;

import java.util.ArrayList;

import android.database.Cursor;

import com.melontech.pokerclock.constants.Constants;
import com.melontech.pokerclock.statsobjects.BlindsStatObjectModel;
import com.melontech.pokerclock.statsobjects.PayoutsStatObjectModel;
import com.melontech.pokerclock.statsobjects.TournamentStatObjectModel;

import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;
import kpk.dbaccesswrapper.accessor.errors.DatabaseException;

public class ObjectParser {
	public static final int BLINDS_TYPE = 0;
	public static final int TOURNAMENT_TYPE = 1;
	public static final int PAYOUTS_TYPE = 2;
	private static final String SELECT_FROM_DB_STRING = "SELECT * FROM ";
	private static final String WHERE_STRING = " WHERE _id="; 
	public static ArrayList<AbstractObjectModel> inflateDefaultData(int type) {
		final ArrayList<AbstractObjectModel> itemsList = new ArrayList<AbstractObjectModel>();
		int i = 0;
		switch(type) {
		case BLINDS_TYPE:
			for(String blindsName : Constants.BLINDS_NAMES) {
				BlindsStatObjectModel blindsObject = new BlindsStatObjectModel(blindsName, Constants.SMALL_BLINDS_PRESETS[i], 
						Constants.BIG_BLINDS_PRESETS[i], Constants.BLINDS_ANTES[i]);
				itemsList.add(blindsObject);
				i++;
			}
			break;
		case PAYOUTS_TYPE:
			for(String payoutName : Constants.PAYOUTS_NAMES) {
				PayoutsStatObjectModel payoutsObject = new PayoutsStatObjectModel(payoutName, Constants.PAYOUTS_PRESETS[i]);
				itemsList.add(payoutsObject);
				i++;
			}
			break;
		}
		return itemsList;
	}
	
	public static AbstractObjectModel getItemAt(int type, int index) {
		AbstractObjectModel result = null;
		Cursor cursor = null;
		switch(type) {
			case TOURNAMENT_TYPE:
				try{
					cursor = AbstractObjectModel.getDatabase().rawQuery(SELECT_FROM_DB_STRING + TournamentStatObjectModel.class.getSimpleName() + WHERE_STRING + index);
					if(cursor.moveToFirst()) {
						result = new TournamentStatObjectModel();
						result.inflate(cursor);
					}else {
						result = null;
					}
				}catch(DatabaseException e) {
					e.printStackTrace();
				}
				
			break;
			
			case BLINDS_TYPE:
				try{
					cursor = AbstractObjectModel.getDatabase().rawQuery(SELECT_FROM_DB_STRING + BlindsStatObjectModel.class.getSimpleName() + WHERE_STRING + index);
					if(cursor.moveToFirst()) {
						result = new BlindsStatObjectModel();
						result.inflate(cursor);
					}else {
						result = null;
					}
				}catch(DatabaseException e) {
					e.printStackTrace();
				}
			break;
			
			case PAYOUTS_TYPE:
				try{
					cursor = AbstractObjectModel.getDatabase().rawQuery(SELECT_FROM_DB_STRING + PayoutsStatObjectModel.class.getSimpleName() + WHERE_STRING + index);
					if(cursor.moveToFirst()) {
						result = new PayoutsStatObjectModel();
						result.inflate(cursor);
					}else {
						result = null;
					}
				}catch(DatabaseException e) {
					e.printStackTrace();
				}
			break;
		}
		cursor.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends AbstractObjectModel> ArrayList<T> getAllItemsOfType(int type) {
		final ArrayList<T> itemsList = new ArrayList<T>();
		Cursor cursor = null;
		switch(type) {
			case TOURNAMENT_TYPE:
				
				try {
					cursor = AbstractObjectModel.getDatabase().rawQuery(SELECT_FROM_DB_STRING + TournamentStatObjectModel.class.getSimpleName());
					while(cursor.moveToNext()) {
						TournamentStatObjectModel tournament = new TournamentStatObjectModel();
						tournament.inflate(cursor);
						itemsList.add((T)tournament);
					}
					
				}catch(DatabaseException e) {
					e.printStackTrace();
				}
			break;
			case BLINDS_TYPE:
				try {
					cursor = AbstractObjectModel.getDatabase().rawQuery(SELECT_FROM_DB_STRING + BlindsStatObjectModel.class.getSimpleName());
					while(cursor.moveToNext()) {
						BlindsStatObjectModel blinds = new BlindsStatObjectModel();
						blinds.inflate(cursor);
						itemsList.add((T)blinds);
					}
					
				}catch(DatabaseException e) {
					e.printStackTrace();
				}
			break;
			
			case PAYOUTS_TYPE:
				try {
					cursor = AbstractObjectModel.getDatabase().rawQuery(SELECT_FROM_DB_STRING + PayoutsStatObjectModel.class.getSimpleName());
					while(cursor.moveToNext()) {
						PayoutsStatObjectModel payouts = new PayoutsStatObjectModel();
						payouts.inflate(cursor);
						itemsList.add((T)payouts);
					}
					
				}catch(DatabaseException e) {
					e.printStackTrace();
				}
			break;
		}
		cursor.close();
		return itemsList;
		
	}
}

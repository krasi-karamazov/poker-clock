package com.melontech.pokerclock.constants;

import java.util.ArrayList;

import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;

import com.melontech.pokerclock.statsobjects.TournamentStatObjectModel;
import com.melontech.pokerclock.utils.ObjectParser;

public class Constants {
	public static final String DEFAULT_TOURNAMENT_NAME = "Default Tournament";
	public static final int DEFAULT_TOURNAMENT_LEVEL_TIME = 20;
	public static final int DEFAULT_TOURNAMENT_NUMPLAYERS = 2;
	public static final boolean DEFAULT_TOURNAMENT_SOUND_ON = false;
	public static final String DEFAULT_TOURNAMENT_SMALL_BLINDS = "10,15,25,50,75,100,150,200,300,400,500,600,800,1000";
	public static final String DEFAULT_TOURNAMENT_BIG_BLINDS = "20,30,50,100,150,200,300,400,600,800,1000,1200,1600,2000";
	public static final String DEFAULT_TOURNAMENT_ANTES = "0";
	public static final String DEFAULT_TOURNAMENT_PAYOUTS = "50,30,20";
	
	public static final String[] SMALL_BLINDS_PRESETS = {"10,15,25,50,75,100,150,200,300,400,500,600,800,1000", "1,2,3,4,6,8,10,15,20,30,40,60,80,100", "25,50,100,150,150,200,300,400,600,800,1200,1500,2000"};
	public static final String[] BIG_BLINDS_PRESETS = {"20,30,50,100,150,200,300,400,600,800,1000,1200,1600,2000", "2,4,6,8,12,16,20,30,40,60,80,120,160,200", "50,100,200,300,300,400,600,800,1200,1600,2400,3000,4000"};
	public static final String[] BLINDS_ANTES = {"0", "0", "0,0,0,0,25,50,75,100,200,300,400,500,500"};
	public static final String[] BLINDS_NAMES = {"Standard", "Standard Low", "Standard Ante"};
	public static final String[] PAYOUTS_PRESETS = {"50,30,20", "37,22,15,11,8,7", "30,20,12,10,8,6.5,5.5,4.5,3.5", "29,18.5,12,10,8,6.5,5.5,4.5,3.5,2.5", "28.5,17.25,11,9.1,7.6,6.4,5.4,4.3,3.3,2.35,1.6", "28,17,10.5,8.25,7.1,6,5,4.1,2.8,2.25,1.6,1.4", "27.5,16.6,10.15,7.9,6.8,5.8,4.7,3.7,2.6,2,1.5,1.25,1"};
	public static final String[] PAYOUTS_NAMES = {"3 Spots", "6 Spots", "9 Spots", "10 Spots", "13 Spots", "16 Spots", "20 Spots"};
	public static final int HOURS = 2;
	public static final int MINUTES = 1;
	public static final int DATABASE_VERSION = 1000;
	public static final String DATABASE_NAME = "PokerClockDatabase";
	public static final String PREFERENCES_FILE = "PokerClockPreferences";
	public static final String DEFAULT_DATA_WRITTEN_KEY = "isDefaultDataWritten";
	public static final TournamentStatObjectModel DEFAULT_TORNAMENT = new TournamentStatObjectModel(Constants.DEFAULT_TOURNAMENT_NAME, 
    		Constants.DEFAULT_TOURNAMENT_LEVEL_TIME, Boolean.valueOf(Constants.DEFAULT_TOURNAMENT_SOUND_ON).toString(),
    		Constants.DEFAULT_TOURNAMENT_SMALL_BLINDS, Constants.DEFAULT_TOURNAMENT_BIG_BLINDS, Constants.DEFAULT_TOURNAMENT_ANTES, 
    		Constants.DEFAULT_TOURNAMENT_PAYOUTS);
	public static final ArrayList<AbstractObjectModel> DEFAULT_BLINDS = ObjectParser.inflateDefaultData(ObjectParser.BLINDS_TYPE);
	public static final ArrayList<AbstractObjectModel> DEFAULT_PAYOUTS = ObjectParser.inflateDefaultData(ObjectParser.PAYOUTS_TYPE);
	public static final String SELECTED_TOURNAMENT_KEY = "selectedTournament";
	public static final String NEW_TOURNAMENT_KEY = "newTournament";
	public static final String SAVED_INSTANCE_TOURNAMENT_KEY = "savedTournament";
	public static final String SAVED_INSTANCE_INITIAL_TOURNAMENT_KEY = "savedInitTournament";
	public static final int VERTICAL_SEEKBAR_MAX_VALUE = 30;
	public static final int VERTICAL_SEEKBAR_DEFAULT_VALUE = 20;
	public static final String TOURNAMENT_STATS_CHANGED_PROMPT_FRAGMENT_KEY = "statsChangedFragmentKey";
}

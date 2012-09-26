package com.melontech.pokerclock;

import kpk.dbaccesswrapper.accessor.Database;
import kpk.dbaccesswrapper.accessor.SQLiteDatabaseBuilder;
import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;
import kpk.dbaccesswrapper.accessor.errors.DatabaseException;

import com.melontech.pokerclock.constants.Constants;
import com.melontech.pokerclock.statsobjects.BlindsStatObjectModel;
import com.melontech.pokerclock.statsobjects.PayoutsStatObjectModel;
import com.melontech.pokerclock.statsobjects.TournamentStatObjectModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


public class PokerClockApplication extends Application {

	private static String sTAG = PokerClockApplication.class.getSimpleName(); 
	private Database mDatabase;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initDatabase();
		checkSharedPreferences();
	}
	
	private void initDatabase() {
		Log.d(sTAG, "YES");
		final SQLiteDatabaseBuilder builder = SQLiteDatabaseBuilder.getInstance();
		final TournamentStatObjectModel tempTour = new TournamentStatObjectModel();
		final BlindsStatObjectModel tempBlinds = new BlindsStatObjectModel();
		final PayoutsStatObjectModel tempPayouts = new PayoutsStatObjectModel();
        builder.addEntity(tempTour.getClass().getSimpleName(), tempTour.getFields(), tempTour.getFieldTypes());
        builder.addEntity(tempBlinds.getClass().getSimpleName(), tempBlinds.getFields(), tempTour.getFieldTypes());
        builder.addEntity(tempPayouts.getClass().getSimpleName(), tempPayouts.getFields(), tempPayouts.getFieldTypes());
        mDatabase= Database.getInstance(this, Constants.DATABASE_NAME, Constants.DATABASE_VERSION, builder);
        AbstractObjectModel.setDatabase(mDatabase);
        AbstractObjectModel.setBuilder(builder);
		
	}

	private void checkSharedPreferences() {
		SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES_FILE, Context.MODE_PRIVATE);
		
		if(!preferences.contains(Constants.DEFAULT_DATA_WRITTEN_KEY) || !preferences.getBoolean(Constants.DEFAULT_DATA_WRITTEN_KEY, false)) {
			final Editor editor = preferences.edit();
			initDatabaseAndDefaultData();
			editor.putBoolean(Constants.DEFAULT_DATA_WRITTEN_KEY, true);
			boolean preferencesSaved = editor.commit();
			if(preferencesSaved) {
				Log.d(sTAG, Boolean.valueOf(preferences.getBoolean(Constants.DEFAULT_DATA_WRITTEN_KEY, false)).toString());
			}
		}
	}

	private void initDatabaseAndDefaultData() {
		
        AbstractObjectModel.getDatabase().open();
        Constants.DEFAULT_TORNAMENT.addToDatabase();
        for(AbstractObjectModel obj : Constants.DEFAULT_BLINDS) {
        	obj.addToDatabase();
        }
        for(AbstractObjectModel obj : Constants.DEFAULT_PAYOUTS) {
        	obj.addToDatabase();
        }
        try {
        	AbstractObjectModel.getDatabase().close();
        	Log.d(sTAG, Boolean.valueOf(mDatabase.isOpen()).toString());
        }catch(DatabaseException e) {
        	e.printStackTrace();
        }
        
	}
}

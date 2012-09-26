package com.melontech.pokerclock.activities;

import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;
import kpk.dbaccesswrapper.accessor.errors.DatabaseException;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;

public class SuperActivity extends Activity {

	private static String sTAG = SuperActivity.class.getSimpleName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	if(AbstractObjectModel.getDatabase() != null) {
    		try{
    			AbstractObjectModel.getDatabase().close();
    			Log.d(sTAG, Boolean.valueOf(AbstractObjectModel.getDatabase().isOpen()).toString());
    		}catch(DatabaseException e) {
    			e.printStackTrace();
    		}catch(NullPointerException e) {
    			Log.d(sTAG, Boolean.valueOf(AbstractObjectModel.getDatabase().isOpen()).toString());
    		}
    	}
    }
}

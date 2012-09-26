package com.melontech.pokerclock.activities;

import java.util.ArrayList;

import com.melontech.pokerclock.R;
import com.melontech.pokerclock.adapters.PayoutsAdapter;
import com.melontech.pokerclock.constants.Constants;
import com.melontech.pokerclock.listeners.PayoutsChangeListener;
import com.melontech.pokerclock.statsobjects.AbstractListModel;
import com.melontech.pokerclock.statsobjects.PayoutsListModel;
import com.melontech.pokerclock.utils.ObjectParser;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class PayoutsActivity extends FragmentActivity implements PayoutsChangeListener {
	private static String sTag = PayoutsActivity.class.getSimpleName(); 
	private PayoutsAdapter mAdapter;
	private ArrayList<AbstractListModel> mList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payouts_list_layout);
		ListView payoutsListView = (ListView)findViewById(R.id.payouts_list);
		
		setInfo(savedInstanceState);
		payoutsListView.setAdapter(mAdapter);
	}
	
	private void setInfo(Bundle savedState) {
		if(savedState == null) {
			mList =  ObjectParser.parseObject(ObjectParser.PAYOUTS_TYPE, getIntent().getStringExtra(Constants.SELECTED_TOURNAMENT_PAYOUTS_KEY));
		}else{
			mList =  ObjectParser.parseObject(ObjectParser.PAYOUTS_TYPE, savedState.getString(Constants.SELECTED_TOURNAMENT_PAYOUTS_KEY));
		}
		
		mAdapter = new PayoutsAdapter(PayoutsActivity.this, R.layout.payouts_row_layout, mList);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		final StringBuilder builder = new StringBuilder();
		for(AbstractListModel p : mList) {
			builder.append(((PayoutsListModel)p).getPrize() + ',');
		}
		outState.putString(Constants.SELECTED_TOURNAMENT_PAYOUTS_KEY, builder.toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.stats_options_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void editPayouts() {
		Log.d(sTag, "SHOW DIALOG");
	}
}

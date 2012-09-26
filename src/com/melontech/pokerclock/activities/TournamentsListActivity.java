package com.melontech.pokerclock.activities;

import java.util.List;

import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;

import com.melontech.pokerclock.R;
import com.melontech.pokerclock.adapters.TournamentsListAdapter;
import com.melontech.pokerclock.constants.Constants;
import com.melontech.pokerclock.statsobjects.BlindsStatObjectModel;
import com.melontech.pokerclock.statsobjects.PayoutsStatObjectModel;
import com.melontech.pokerclock.statsobjects.TournamentStatObjectModel;
import com.melontech.pokerclock.utils.ObjectParser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TournamentsListActivity extends SuperActivity {
	
	private List<TournamentStatObjectModel> mTournamentList;
	private static final int EDIT_ITEM = 0;
	private static final int DELETE_ITEM = 1;
	private static final int START_ITEM = 2;
	private static String sTag = TournamentsListActivity.class.getSimpleName();
	private int mSelectedTournamentIndex;
	private ListView mTournamentsListView;
	private TournamentsListAdapter mAdapter;
	private AddTournamentListener mAddTournamentListener = new AddTournamentListener();
	public static final int RESULT_CODE_CANCEL_EDITS = 554;
	public static final int RESULT_CODE_EDITED = 555;
	public static final int RESULT_CODE_ADDED = 556;
	private static final int REQUEST_CODE = 557;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tournaments_list_layout);
		AbstractObjectModel.getDatabase().open();
		mTournamentsListView = (ListView)findViewById(R.id.tournaments_list);
		drawList();
		Button addTournamentButton = (Button)findViewById(R.id.add_tournament_button);
		addTournamentButton.setOnClickListener(mAddTournamentListener);
		registerForContextMenu(mTournamentsListView);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if(v.getId() == R.id.tournaments_list) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			mSelectedTournamentIndex = info.position;
			menu.setHeaderTitle(mTournamentList.get(mSelectedTournamentIndex).getName());
			String[] menuItems = getResources().getStringArray(R.array.context_menu_items);
			for(int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}
	
	private void drawList() {
		mTournamentList = ObjectParser.getAllItemsOfType(ObjectParser.TOURNAMENT_TYPE);
		mAdapter = new TournamentsListAdapter(TournamentsListActivity.this, R.layout.tournament_row_layout, mTournamentList);
		mTournamentsListView.setAdapter(mAdapter);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
			case EDIT_ITEM:
				startChildActivity(mTournamentList.get(mSelectedTournamentIndex));
			break;
			case DELETE_ITEM:
				if(mSelectedTournamentIndex > 0) {
					drawList();
				}else {
					Toast.makeText(TournamentsListActivity.this, getString(R.string.cannot_delete_default), Toast.LENGTH_SHORT).show();
				}
				
				break;
			case START_ITEM:
				Log.d(sTag, "START");
				break;
		}
		return true;
	}
	
	private void startChildActivity(TournamentStatObjectModel tournament) {
		Intent editIntent = new Intent(TournamentsListActivity.this, EditTournamentActivity.class);
		if(tournament == null) {
			BlindsStatObjectModel blindsPreset = (BlindsStatObjectModel)ObjectParser.getItemAt(ObjectParser.BLINDS_TYPE, 1);
			PayoutsStatObjectModel payoutsPreset = (PayoutsStatObjectModel)ObjectParser.getItemAt(ObjectParser.PAYOUTS_TYPE, 1);
			tournament = new TournamentStatObjectModel("", Constants.DEFAULT_TOURNAMENT_LEVEL_TIME, "true", blindsPreset.smallBlinds, blindsPreset.bigBlinds, blindsPreset.antes, payoutsPreset.payouts);
			editIntent.putExtra(Constants.NEW_TOURNAMENT_KEY, tournament);
		}else {
			editIntent.putExtra(Constants.SELECTED_TOURNAMENT_KEY, tournament);
		}
		startActivityForResult(editIntent, REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQUEST_CODE) {
			Log.d(sTag, "result CODE " + Integer.valueOf(resultCode).toString());
			if(resultCode == RESULT_CODE_EDITED || resultCode == RESULT_CODE_ADDED) {
				
				Log.d(sTag, "SAVE TOUR INFO");
				drawList();
			}
		}
	}
	
	private class AddTournamentListener implements View.OnClickListener {
		
		public void onClick(View v) {
			startChildActivity(null);
		}
	}
}

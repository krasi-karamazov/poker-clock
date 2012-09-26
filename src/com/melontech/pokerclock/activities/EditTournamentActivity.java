package com.melontech.pokerclock.activities;


import com.melontech.pokerclock.R;
import com.melontech.pokerclock.components.VerticalSeekBar;
import com.melontech.pokerclock.constants.Constants;
import com.melontech.pokerclock.fragments.PokerClockDialogFragment;
import com.melontech.pokerclock.listeners.EditPromptConfirmationListener;
import com.melontech.pokerclock.statsobjects.TournamentStatObjectModel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class EditTournamentActivity extends FragmentActivity implements EditPromptConfirmationListener {
	private TournamentStatObjectModel mTournament;
	private TournamentStatObjectModel mInitTournament;
	private EditText mTournamentNameField;
	private TextView mLevelTimeField;
	private ToggleButton mSoundOnButton;
	private VerticalSeekBar mSeekBar;
	private final EditTournamentActivityClickListener mClickListener = new EditTournamentActivityClickListener();
	private final SeekBarScrollListener mScrollListener = new SeekBarScrollListener();
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_tournament_layout);
		
		initTournamentData(savedInstanceState);
		mTournamentNameField = (EditText)findViewById(R.id.tournament_name_field);
		mLevelTimeField = (TextView)findViewById(R.id.tournament_leveltime_field);
		mSoundOnButton = (ToggleButton)findViewById(R.id.soundCheckBox);
		Button blindsButton = (Button)findViewById(R.id.blindsButton);
		Button payoutsButton = (Button)findViewById(R.id.payoutsButton);
		
		blindsButton.setOnClickListener(mClickListener);
		payoutsButton.setOnClickListener(mClickListener);
		
		mSeekBar = (VerticalSeekBar)findViewById(R.id.seek_bar);
		mSeekBar.setMax(Constants.VERTICAL_SEEKBAR_MAX_VALUE);
		mSeekBar.setOnSeekBarChangeListener(mScrollListener);
		setInfo();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		mTournament.setSoundOn(mSoundOnButton.isChecked()?"true":"false");
		mTournament.setName(mTournamentNameField.getText().toString());
		mTournament.setLevelTime(mSeekBar.getProgress());
		outState.putParcelable(Constants.SAVED_INSTANCE_TOURNAMENT_KEY, mTournament);
		outState.putParcelable(Constants.SAVED_INSTANCE_INITIAL_TOURNAMENT_KEY, mInitTournament);
	}
	
	private void initTournamentData(Bundle savedState) {
		if(savedState == null) {
			Bundle extras = getIntent().getExtras();
			
			if(extras.containsKey(Constants.SELECTED_TOURNAMENT_KEY)) {
				mTournament = (TournamentStatObjectModel)extras.getParcelable(Constants.SELECTED_TOURNAMENT_KEY);
			}else if(extras.containsKey(Constants.NEW_TOURNAMENT_KEY)) {
				mTournament = extras.getParcelable(Constants.NEW_TOURNAMENT_KEY);
			}
		mInitTournament = new TournamentStatObjectModel(mTournament.getName(), mTournament.getLevelTime(), mTournament.getSoundOn(), 
				mTournament.getSmallBlinds(), mTournament.getBigBlinds(), mTournament.getAntes(), mTournament.getPayouts());
		}else{
			mTournament = savedState.getParcelable(Constants.SAVED_INSTANCE_TOURNAMENT_KEY);
			mInitTournament = savedState.getParcelable(Constants.SAVED_INSTANCE_INITIAL_TOURNAMENT_KEY);
		}
	}
	
	private void setInfo() {
		if(mTournament.getName().length() > 0) {
			mTournamentNameField.setText(mTournament.getName());
			mLevelTimeField.setText(Integer.valueOf(mTournament.getLevelTime()).toString());
			mSeekBar.setProgress(Integer.valueOf(mTournament.getLevelTime()));
			mSoundOnButton.setChecked(Boolean.valueOf(mTournament.getSoundOn()));
		}else{
			mTournamentNameField.setHint(getString(R.string.tournament_name));
			mLevelTimeField.setText(Integer.valueOf(20).toString());
			mSeekBar.setProgress(20);
			mSoundOnButton.setChecked(Boolean.valueOf(true));
		}
	}

	private class SeekBarScrollListener implements OnSeekBarChangeListener {
		public void onStopTrackingTouch(SeekBar seekBar) {}
		
		public void onStartTrackingTouch(SeekBar seekBar) {}
		
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			mLevelTimeField.setText(Integer.valueOf(progress).toString());
		}
	}
	
	@Override
	public void onBackPressed() {
		saveTempTournamentData();
		checkIfTournamentIsChanged();
	}
	
	private void saveTempTournamentData() {
		mTournament.setName(mTournamentNameField.getText().toString());
		mTournament.setSoundOn(Boolean.valueOf(mSoundOnButton.isChecked()).toString());
		mTournament.setLevelTime(Integer.valueOf(mLevelTimeField.getText().toString()).intValue());
	}

	private void checkIfTournamentIsChanged() {
		if(mInitTournament.equals(mTournament)) {
			super.onBackPressed();
		}else {
			FragmentManager fm = getSupportFragmentManager();
			Bundle args = new Bundle();
			args.putString(Constants.TOURNAMENT_STATS_CHANGED_PROMPT_FRAGMENT_KEY, mTournamentNameField.getText().toString());
	        PokerClockDialogFragment editConfirmationPrompt = PokerClockDialogFragment.getInstance(args);
	        editConfirmationPrompt.show(fm, "fragment_confirmation_prompt");
		}
		
	}

	private class EditTournamentActivityClickListener implements View.OnClickListener {
		public void onClick(View v) {
			Intent intent = null;
			switch(v.getId()) {
				case R.id.payoutsButton:
//					intent = new Intent(EditTournamentActivity.this, PayoutsActivity.class);
//					
//					intent.putExtra("selectedTournament", selectedTournament);
					Log.d("STARTING", "PAYOUT");
//					startActivityForResult(intent, 0);
					break;
				case R.id.blindsButton:
					Log.d("STARTING", "BLINDS");
//					intent = new Intent(TournamentInfoActivity.this, BlindsListActivity.class);
//					
//					intent.putExtra("selectedTournament", selectedTournament);
//					
//					startActivityForResult(intent, 0);
					break;
			}
		}
	}

	public void promptResponse(int response) {
		switch(response){
			case DialogInterface.BUTTON_POSITIVE:
			if(mInitTournament.getName().length() < 1) {
				mTournament.addToDatabase();
				setResult(TournamentsListActivity.RESULT_CODE_ADDED);
			}else {
				Log.d("CHECK TOURNAMENT", "UPDATE " + mTournament.getName());
				mTournament.update();
				setResult(TournamentsListActivity.RESULT_CODE_EDITED);
			}
			break;
			case DialogInterface.BUTTON_NEGATIVE:
				setResult(TournamentsListActivity.RESULT_CODE_CANCEL_EDITS);
			break;
		}
		finish();
	}
}

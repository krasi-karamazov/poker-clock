package com.melontech.pokerclock.activities;

import com.melontech.pokerclock.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PokerClockMainActivity extends SuperActivity {
	private Button mStartButton;
	private Button mEditButton;
	private PokerClockMainScreenButtonsListener sListener = new PokerClockMainScreenButtonsListener();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		mStartButton = (Button)findViewById(R.id.start_button_main_activity);
		mEditButton = (Button)findViewById(R.id.edit_button_main_activity);
		
		mEditButton.setOnClickListener(sListener);
		mStartButton.setOnClickListener(sListener);
	}
	
	private class PokerClockMainScreenButtonsListener implements View.OnClickListener {
		
		public void onClick(View v) {
			Intent intent;
			switch(v.getId()) {
			
			case R.id.edit_button_main_activity:
				intent = new Intent(PokerClockMainActivity.this, TournamentsListActivity.class);
				startActivity(intent);
				break;
				
			case R.id.start_button_main_activity:
				break;
			}
		}
	}

}

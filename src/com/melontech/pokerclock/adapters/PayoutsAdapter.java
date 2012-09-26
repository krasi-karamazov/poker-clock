package com.melontech.pokerclock.adapters;

import java.util.List;

import com.melontech.pokerclock.R;
import com.melontech.pokerclock.listeners.PayoutsChangeListener;
import com.melontech.pokerclock.statsobjects.AbstractListModel;
import com.melontech.pokerclock.statsobjects.PayoutsListModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PayoutsAdapter extends ArrayAdapter<AbstractListModel> {
	public PayoutsAdapter(Context context, int layoutID, List<AbstractListModel> collection) {
		super(context, layoutID, collection);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(row == null) {
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.payouts_row_layout, parent, false);
		}
		TextView placeView = (TextView)row.findViewById(R.id.place_field);
		TextView prizeView = (TextView)row.findViewById(R.id.prize_field);
		Button editButton = (Button)row.findViewById(R.id.edit_payouts_button);
		placeView.setText(Integer.valueOf(((PayoutsListModel)getItem(position)).getPlace()).toString());
		prizeView.setText(((PayoutsListModel)getItem(position)).getPrize());
		editButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				((PayoutsChangeListener)getContext()).editPayouts();
				
			}
		});
		return row;
	}
}

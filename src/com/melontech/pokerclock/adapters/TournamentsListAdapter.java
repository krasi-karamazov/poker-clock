package com.melontech.pokerclock.adapters;

import java.util.List;

import com.melontech.pokerclock.R;
import com.melontech.pokerclock.statsobjects.TournamentStatObjectModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TournamentsListAdapter extends ArrayAdapter<TournamentStatObjectModel> {

	public TournamentsListAdapter(Context context, int layoutID, List<TournamentStatObjectModel> collection) {
		super(context, layoutID, collection);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(row == null) {
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.tournament_row_layout, parent, false);
		}
		TextView tournamentLabel = (TextView)row.findViewById(R.id.tournament_row_name_field);
		tournamentLabel.setText(getItem(position).getName());
		return row;
	}
}

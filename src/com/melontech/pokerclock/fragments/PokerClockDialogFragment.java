package com.melontech.pokerclock.fragments;

import com.melontech.pokerclock.R;
import com.melontech.pokerclock.constants.Constants;
import com.melontech.pokerclock.listeners.EditPromptConfirmationListener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class PokerClockDialogFragment extends DialogFragment {

	private final static String sTag = PokerClockDialogFragment.class.getSimpleName();
	private final ConfirmationPromptClickListener listener = new ConfirmationPromptClickListener();
	public static PokerClockDialogFragment getInstance(Bundle args) {
		PokerClockDialogFragment fragment = new PokerClockDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		String message = getResources().getString(R.string.tournament_stats_changed_prompt_message);
		String positive = getResources().getString(R.string.positive_dialog_label);
		String negative = getResources().getString(R.string.negative_dialog_label);
		builder.setMessage(message);
		builder.setTitle(getArguments().getString(Constants.TOURNAMENT_STATS_CHANGED_PROMPT_FRAGMENT_KEY));
		builder.setPositiveButton(positive, listener);
		builder.setNegativeButton(negative, listener);
		return builder.create();
	}
	
	private class ConfirmationPromptClickListener implements DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			((EditPromptConfirmationListener)getActivity()).promptResponse(which);
		}
	}
}

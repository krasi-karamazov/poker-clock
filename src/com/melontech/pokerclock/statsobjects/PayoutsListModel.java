package com.melontech.pokerclock.statsobjects;

public class PayoutsListModel extends AbstractListModel {
	private String mPrize = null;
	private int mPlace;
	public PayoutsListModel(int place, String prize)
	{
		this.mPrize = prize;
		this.mPlace = place;
	}
	
	public void setPrize(String prize) {
		this.mPrize = prize;
	}
	
	public String getPrize() {
		return this.mPrize;
	}
	
	public void setPlace(int place) {
		this.mPlace = place;
	}
	
	public int getPlace() {
		return this.mPlace;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return "Payout";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return PayoutsListModel.class.getSimpleName() + " Place: " + Integer.valueOf(this.mPlace) + ", Prize: " + this.mPrize;
	}
}

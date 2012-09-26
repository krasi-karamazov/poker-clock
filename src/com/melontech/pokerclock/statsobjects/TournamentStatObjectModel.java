package com.melontech.pokerclock.statsobjects;

import java.util.ArrayList;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;
import kpk.dbaccesswrapper.accessor.errors.DatabaseException;
import kpk.dbaccesswrapper.accessor.utils.EntityFieldNameConverter;

public class TournamentStatObjectModel extends AbstractObjectModel implements Parcelable {
	
	private String tournamentName;
	private int levelTime;
	private String soundOn;
	private String smallBlinds;
	private String bigBlinds;
	private String antes;
	private String payouts;
	
	public TournamentStatObjectModel() {
		
	}
	
	public TournamentStatObjectModel(String name, int levelTime, String soundOn, String smallBlinds, String bigBlinds, String antes, String payouts) {
		this.tournamentName = name;
		this.levelTime = levelTime;
		this.soundOn = soundOn;
		this.smallBlinds = smallBlinds;
		this.bigBlinds = bigBlinds;
		this.antes = antes;
		this.payouts = payouts;
	}
	
	public TournamentStatObjectModel(long id, String name, int levelTime, String soundOn, String smallBlinds, String bigBlinds, String antes, String payouts) {
		this._id = id;
		this.tournamentName = name;
		this.levelTime = levelTime;
		this.soundOn = soundOn;
		this.smallBlinds = smallBlinds;
		this.bigBlinds = bigBlinds;
		this.antes = antes;
		this.payouts = payouts;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		TournamentStatObjectModel other = (TournamentStatObjectModel)o;
		if(this.tournamentName != null) {
			if(this.tournamentName.equals(other.tournamentName) && this.levelTime == other.levelTime && this.soundOn.equals(other.soundOn) && this.smallBlinds.equals(other.smallBlinds) && this.bigBlinds.equals(other.bigBlinds) && this.antes.equals(other.antes) && this.payouts.equals(other.payouts)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void inflate(Cursor cursor) throws DatabaseException {
		// TODO Auto-generated method stub
		this._id = cursor.getLong(cursor.getColumnIndex("_id"));
		this.tournamentName = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("tournamentName")));
		this.levelTime = cursor.getInt(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("levelTime")));
		this.soundOn = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("soundOn")));
		this.smallBlinds = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("smallBlinds")));
		this.bigBlinds = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("bigBlinds")));
		this.antes = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("antes")));
		this.payouts = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("payouts")));
	}
	
	@Override
	public ArrayList<String> getFieldTypes() {
		final ArrayList<String> fieldTypes = new ArrayList<String>();
		fieldTypes.add("String");
		fieldTypes.add("int");
		fieldTypes.add("String");
		fieldTypes.add("String");
		fieldTypes.add("String");
		fieldTypes.add("String");
		fieldTypes.add("String");
		return fieldTypes;
	}
	
	@Override
	public ArrayList<Object> getFieldValues() {
		final ArrayList<Object> fieldValues = new ArrayList<Object>();
		fieldValues.add(this.tournamentName);
		fieldValues.add(this.levelTime);
		fieldValues.add(this.soundOn);
		fieldValues.add(this.smallBlinds);
		fieldValues.add(this.bigBlinds);
		fieldValues.add(this.antes);
		fieldValues.add(this.payouts);
		return fieldValues;
	}
	
	@Override
	public ArrayList<String> getFields() {
		final ArrayList<String> fields = new ArrayList<String>();
		fields.add("tournamentName");
		fields.add("levelTime");
		fields.add("soundOn");
		fields.add("smallBlinds");
		fields.add("bigBlinds");
		fields.add("antes");
		fields.add("payouts");
		return fields;
	}
	
	public int describeContents() {
		return 0;
	}
	
	public static final Parcelable.Creator<TournamentStatObjectModel> CREATOR = new Creator<TournamentStatObjectModel>() {
		public TournamentStatObjectModel createFromParcel(Parcel source) 
		{
			return new TournamentStatObjectModel(source.readLong(), source.readString(), source.readInt(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
		};
		
		public TournamentStatObjectModel[] newArray(int size) 
		{
			return new TournamentStatObjectModel[size];
		};
	};
	
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeLong(this._id);
		dest.writeString(this.tournamentName);
		dest.writeInt(this.levelTime);
		dest.writeString(this.soundOn);
		dest.writeString(this.smallBlinds);
		dest.writeString(this.bigBlinds);
		dest.writeString(this.antes);
		dest.writeString(this.payouts);
	}
	
	public void setID(long id) {
		this._id = id;
	}
	
	public long getID() {
		return this._id;
	}
	
	public void setLevelTime(int time) {
		this.levelTime = time;
	}
	
	public int getLevelTime() {
		return this.levelTime;
	}
	
	
	public void setName(String name) {
		this.tournamentName = name;
	}
	
	public String getName() {
		return this.tournamentName;
	}
	
	public void setSoundOn(String sound) {
		this.soundOn = sound;
	}
	
	public String getSoundOn() {
		return this.soundOn;
	}
	
	public void setSmallBlinds(String blinds) {
		this.smallBlinds = blinds;
	}
	
	public String getSmallBlinds() {
		return this.smallBlinds;
	}
	
	public void setBigBlinds(String blinds) {
		this.bigBlinds = blinds;
	}
	
	public String getBigBlinds() {
		return this.bigBlinds;
	}
	
	public void setAntes(String antes) {
		this.antes = antes;
	}
	
	public String getAntes() {
		return this.antes;
	}
	
	public void setPayouts(String payouts) {
		this.payouts = payouts;
	}
	
	public String getPayouts() {
		return this.payouts;
	}
}

package com.melontech.pokerclock.statsobjects;

import java.util.ArrayList;

import android.database.Cursor;

import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;
import kpk.dbaccesswrapper.accessor.errors.DatabaseException;
import kpk.dbaccesswrapper.accessor.utils.EntityFieldNameConverter;

public class BlindsStatObjectModel extends AbstractObjectModel {
	
	public String name;
	public String smallBlinds;
	public String bigBlinds;
	public String antes;
	
	public BlindsStatObjectModel() {
		
	}
	
	public BlindsStatObjectModel(String name, String smallBlinds, String bigBlinds, String antes) {
		
		this.name = name;
		this.smallBlinds = smallBlinds;
		this.bigBlinds = bigBlinds;
		this.antes = antes;
	}
	
	@Override
	public ArrayList<String> getFieldTypes() {
		final ArrayList<String> fieldTypes = new ArrayList<String>();
		fieldTypes.add("String");
		fieldTypes.add("String");
		fieldTypes.add("String");
		fieldTypes.add("String");
		return fieldTypes;
	}
	
	@Override
	public void inflate(Cursor cursor) throws DatabaseException {
		// TODO Auto-generated method stub
		this._id = cursor.getLong(cursor.getColumnIndex("_id"));
		this.name = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("name")));
		this.smallBlinds = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("smallBlinds")));
		this.bigBlinds = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("bigBlinds")));
		this.antes = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("antes")));
	}
	
	@Override
	public ArrayList<Object> getFieldValues() {
		final ArrayList<Object> fieldTypes = new ArrayList<Object>();
		fieldTypes.add(this.name);
		fieldTypes.add(this.smallBlinds);
		fieldTypes.add(this.bigBlinds);
		fieldTypes.add(this.antes);
		return fieldTypes;
	}
	
	@Override
	public ArrayList<String> getFields() {
		final ArrayList<String> fields = new ArrayList<String>();
		fields.add("name");
		fields.add("smallBlinds");
		fields.add("bigBlinds");
		fields.add("antes");
		return fields;
	}
}

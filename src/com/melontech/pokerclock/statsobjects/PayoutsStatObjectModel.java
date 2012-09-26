package com.melontech.pokerclock.statsobjects;

import java.util.ArrayList;

import android.database.Cursor;

import kpk.dbaccesswrapper.accessor.entities.AbstractObjectModel;
import kpk.dbaccesswrapper.accessor.errors.DatabaseException;
import kpk.dbaccesswrapper.accessor.utils.EntityFieldNameConverter;

public class PayoutsStatObjectModel extends AbstractObjectModel {
	
	public String name;
	public String payouts;
	
	public PayoutsStatObjectModel() {
		
	}
	
	public PayoutsStatObjectModel(String name, String payouts) {
		this.name = name;
		this.payouts = payouts;
	}
	
	@Override
	public ArrayList<String> getFieldTypes() {
		final ArrayList<String> fieldTypes = new ArrayList<String>();
		fieldTypes.add("String");
		fieldTypes.add("String");
		return fieldTypes;
	}
	
	@Override
	public void inflate(Cursor cursor) throws DatabaseException {
		// TODO Auto-generated method stub
		this._id = cursor.getLong(cursor.getColumnIndex("_id"));
		this.name = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("name")));
		this.payouts = cursor.getString(cursor.getColumnIndex(EntityFieldNameConverter.fromCamelCase("payouts")));
	}
	
	@Override
	public ArrayList<Object> getFieldValues() {
		final ArrayList<Object> fieldTypes = new ArrayList<Object>();
		fieldTypes.add(this.name);
		fieldTypes.add(this.payouts);
		return fieldTypes;
	}
	
	@Override
	public ArrayList<String> getFields() {
		final ArrayList<String> fields = new ArrayList<String>();
		fields.add("name");
		fields.add("payouts");
		return fields;
	}

}

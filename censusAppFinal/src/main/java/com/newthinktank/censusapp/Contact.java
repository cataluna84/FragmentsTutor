package com.newthinktank.censusapp;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import android.util.Log;

public class Contact {
	
	private String name;
	private String phoneNumber;
	private String streetAddress;
	private String city;
	
	private UUID idNumber;
	private boolean contacted = false;
	
	private Date dateOfBirth;
	
	public Contact(){
		
		// Generates a type 4 pseudo randomly generated UUID
		// Wiki : 1 billion UUIDs every second for the next 
		// 100 years, the probability of creating just one 
		// duplicate would be about 50%
		
		idNumber = UUID.randomUUID();
		
		dateOfBirth = new Date();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		Log.e("CENSUS", "NAME CHANGED TO " + name);
		
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		
		Log.e("CENSUS", "PHONE CHANGED TO " + phoneNumber);
		
		this.phoneNumber = phoneNumber;
	}


	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		
		Log.e("CENSUS", "STREET CHANGED TO " + streetAddress);
		
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		
		Log.e("CENSUS", "CITY CHANGED TO " + city);
		
		this.city = city;
	}
	
	public boolean getContacted() {
		return contacted;
	}

	public void setContacted(boolean contacted) {
		
		Log.e("CENSUS", "CONTACTED CHANGED TO " + contacted);
		
		this.contacted = contacted;
	}

	public UUID getIdNumber() {
		return idNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateString(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(dateOfBirth);
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return month + "/" + day + "/" + year;
		
	}
	
}









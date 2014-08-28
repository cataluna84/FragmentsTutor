package com.newthinktank.censusapp;

//We will use the android.support.v4.app.Fragment
//support library so our app runs on older versions
//of Android

import java.util.UUID;

import android.support.v4.app.Fragment;

// Change to extend FragmentActivityBuilder so that
//that class can handle the work FragmentManager must do

public class CensusApp extends FragmentActivityBuilder {

	// Call for FragmentActivityBuilder to have the FragmentManager
	// add the right Fragment to the Activity
	
	@Override
	protected Fragment createFragment() {
		
		// Get the Contact ID that was passed over
		
		UUID contactIdNumber = (UUID) getIntent()
				.getSerializableExtra(ContactFragment.CONTACT_ID);
		
		// Create an instance of ContactFragment and pass in
				// the ID so the proper Contact data is displayed
		
		new ContactFragment();
		return ContactFragment.newContactFragment(contactIdNumber);
		
	}

	
}
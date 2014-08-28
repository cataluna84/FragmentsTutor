package com.newthinktank.censusapp;

//We will use the android.support.v4.app.Fragment
//support library so our app runs on older versions
//of Android

import android.support.v4.app.Fragment;

// Change to extend FragmentActivityBuilder so that
// that class can handle the work FragmentManager must do

public class ContactListActivity extends FragmentActivityBuilder {

	// Call for FragmentActivityBuilder to have the FragmentManager
	// add the right Fragment to the Activity
	
	@Override
	protected Fragment createFragment() {
		
		return new FragmentContactList();
	}

}
package com.newthinktank.censusapp;

import java.util.Date;
import java.util.UUID;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class ContactFragment extends Fragment {
	
	// Store the ID NUMBER for the current Contact
	// This is the key of the key / value pair that will
	// store the Contacts Id Number
	
	public static final String CONTACT_ID =
			"com.newthinktank.censusapp.contact_id";

	private Contact contact;
	private EditText contactNameEditText;

	private EditText contactStreetEditText;
	private EditText contactCityEditText;
	private EditText contactPhoneEditText;
	
	private CheckBox contactedCheckBox;
	
	// NEW
	
	// The key that represents the value stored for date of birth
	
	private static final String DATE_OF_BIRTH = "Date of Birth";
	
	private EditText contactBirthdayEditText;
	
	// Used to track when we are performing an action on date of birth
	
	private static final int REQUEST_DATE = 0;
	
	// END OF NEW
	
	
	public static ContactFragment newContactFragment(UUID contactId){
		
		// A Bundle is used to pass data between Activitys
		
		Bundle passedData = new Bundle();
		
		// Put the Contacts ID in the Bundle and return the
		// ContactFragment
		
		passedData.putSerializable(CONTACT_ID, contactId);
		
		ContactFragment contactFragment = new ContactFragment();
		
		contactFragment.setArguments(passedData);
		
		return contactFragment;
		
	}

	// Generate this with Right Click - Source - Override/Implement methods
	// This method is called when the Fragment is called for.
	// We initialize everything here.

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Replace contact = new Contact();
		// Get the value from CONTACT_ID that was passed in
		
		UUID contactId = (UUID) getArguments().getSerializable(CONTACT_ID);
		
		// Get the Contact with the matching ID
		
		contact = AllContacts.get(getActivity()).getContact(contactId);
		
	}

	// Used to inflate the Fragment, or show it on the screen

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Pass in the layout to inflate, the views parent and whether
		// to add the inflated view to the parent.
		// We mark this false because the Activity will add the view.

		View theView = inflater.inflate(R.layout.fragment_contact, container, false);

		// Get a reference to the EditText

		contactNameEditText = (EditText) theView.findViewById(R.id.contactNameEditText);

		// If text in the EditText box is edited it will change the
		// name.
		
		contactStreetEditText = (EditText) theView.findViewById(R.id.contactStreetEditText);
		contactCityEditText = (EditText) theView.findViewById(R.id.contactCityEditText);
		contactPhoneEditText = (EditText) theView.findViewById(R.id.contactPhoneEditText);
		
		// All the above EditText components will use just one TextWatcher
		// which auto updates Contact.java

		TextWatcher editTextWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
				// There is an error in that name is getting the wrong information
				// Removing request focus from fragment_contact.xml fixed that

				Log.e("TEXT WAS CHANGED", arg0.toString());

				if (contactNameEditText.hasFocus() == true){

					contact.setName(arg0.toString());

				} else if (contactStreetEditText.hasFocus() == true){
					
					contact.setStreetAddress(arg0.toString());
					
				} else if (contactCityEditText.hasFocus() == true){
					
					contact.setCity(arg0.toString());
					
				} else if (contactPhoneEditText.hasFocus() == true){
					
					contact.setPhoneNumber(arg0.toString());
					
				}

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
				// TODO Auto-generated method stub

			}
		};

		
		contactStreetEditText.addTextChangedListener(editTextWatcher);
		contactCityEditText.addTextChangedListener(editTextWatcher);
		contactPhoneEditText.addTextChangedListener(editTextWatcher);

		contactNameEditText.addTextChangedListener(editTextWatcher);

		// Create CheckBox Listener
		
		contactedCheckBox = (CheckBox) theView.findViewById(R.id.contactedCheckBox);
		
		contactedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				
				// Change the value of the checkbox
				
				contact.setContacted(arg1);
				
			}
			
		});
		
		// NEW 
		
		// Setup the Birth Day EditText box and insert the data
		
		contactBirthdayEditText = (EditText) theView.findViewById(R.id.contactBirthdayEditText);
		
		contactBirthdayEditText.setText(contact.getDateString());
		
		contactBirthdayEditText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// getSupportFragmentManager() returns the FragmentManager
				// that is used to interact with Fragments in this Activity
				
				FragmentManager fragManager = getActivity()
						.getSupportFragmentManager();
				
				DateDialogFragment dateDialog = DateDialogFragment
						.newInstance(contact.getDateOfBirth());
				
				dateDialog.setTargetFragment(ContactFragment.this, REQUEST_DATE);
				
				dateDialog.show(fragManager, DATE_OF_BIRTH);
				
			}
			
		});
		
		// END OF NEW

		// Get the values for the current Contact and put them in
		// the right Components
		
		contactNameEditText.setText(contact.getName());
		contactStreetEditText.setText(contact.getStreetAddress());;
		contactCityEditText.setText(contact.getCity());
		contactPhoneEditText.setText(contact.getPhoneNumber());
		contactedCheckBox.setChecked(contact.getContacted());


		// Pass in the layout to inflate, the views parent and whether
		// to add the inflated view to the parent.
		// We mark this false because the Activity will add the view.

		return theView;

	}

	// NEW
	
	// Called when the OK button is clicked on the date dialog box
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// If this wasn't called by the date dialog exit
		
		if(resultCode != Activity.RESULT_OK) return;
		
		// If it was get the date and save it to the Contact and
		// update the date EditText box
		
		if(requestCode == REQUEST_DATE){
			
			Date birthdate = (Date) data
					.getSerializableExtra(DateDialogFragment.CONTACT_BIRTHDAY);
			
			contact.setDateOfBirth(birthdate);
			
			contactBirthdayEditText.setText(contact.getDateString());
			
		}
		
	}

}
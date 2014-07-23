package com.newthinktank.censusapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class ContactFragment extends Fragment {

	private Contact contact;
	private EditText contactNameEditText;
	private EditText contactStreetEditText;
	private EditText contactCityEditText;
	private EditText contactPhoneEditText;
	
	// NEW 
	private CheckBox contactedCheckBox;
	
	// END OF NEW

	// Generate this with Right Click - Source - Override/Implement methods
	// This method is called when the Fragment is called for.
	// We initialize everything here.

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		contact = new Contact();
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

		// If text in the EditText box is edited it will change the name.
		
		contactStreetEditText = (EditText) theView.findViewById(R.id.contactStreetEditText);
		contactCityEditText = (EditText) theView.findViewById(R.id.contactCityEditText);
		contactPhoneEditText = (EditText) theView.findViewById(R.id.contactPhoneEditText);
		
		// All the EditText components will use just one TextWatcher
		// which auto updates Contact.java
		TextWatcher editTextWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

				if (contactNameEditText.hasFocus()){

					contact.setName(arg0.toString());

				} else if (contactStreetEditText.hasFocus()){
					
					contact.setStreetAddress(arg0.toString());
					
				} else if (contactCityEditText.hasFocus()){
					
					contact.setCity(arg0.toString());
					
				} else if (contactPhoneEditText.hasFocus()){
					
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
		
		// NEW 
		// Create CheckBox Listener
		
		contactedCheckBox = (CheckBox) theView.findViewById(R.id.contactedCheckBox);
		
		contactedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				
				contact.setContacted(true);
				
			}
			
		});
		
		// END OF NEW


		// Pass in the layout to inflate, the views parent and whether
		// to add the inflated view to the parent.
		// We mark this false because the Activity will add the view.

		return theView;
	}
}

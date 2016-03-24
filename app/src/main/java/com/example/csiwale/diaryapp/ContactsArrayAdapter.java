package com.example.csiwale.diaryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by csiwale on 2/15/2016.
 */
public class ContactsArrayAdapter extends ArrayAdapter<Contact>{
    public ContactsArrayAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_row, parent, false);
        }
        // Lookup view for data population
        TextView txtContactID = (TextView) convertView.findViewById(R.id.contact_id);
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtOfficeAddress = (TextView) convertView.findViewById(R.id.officeAddress);
        TextView txtEmailAddress = (TextView) convertView.findViewById(R.id.emailAddress);
        // Populate the data into the template view using the data object
        txtContactID.setText(Integer.toString(contact.getContactID()));
        txtName.setText(contact.getFirstName()+" "+contact.getLastName());
        txtOfficeAddress.setText(contact.getOfficeAddress());
        txtEmailAddress.setText(contact.getEmailAddress());
        // Return the completed view to render on screen
        return convertView;
    }
}

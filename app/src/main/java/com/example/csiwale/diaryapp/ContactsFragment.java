package com.example.csiwale.diaryapp;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csiwale on 2/13/2016.
 */
public class ContactsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    public ContactsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ContactsFragment newInstance(int sectionNumber) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        DatabaseHandler db = new DatabaseHandler(this.getContext());
        ArrayList<Contact> contacts = db.getAllContacts();
        ContactsArrayAdapter adapter = new ContactsArrayAdapter(this.getContext(), contacts);
        final ListView listView = (ListView)rootView.findViewById(R.id.contact_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter,View v, int position, long id){

                //ItemClicked item = adapter.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), CoursesActivity.class);
                //based on item add info to intent
                Contact itemAtPosition = (Contact)listView.getItemAtPosition(position);
                intent.putExtra("Contact_ID", itemAtPosition.getContactID());
                startActivity(intent);

            }


        });
        return rootView;
    }
}

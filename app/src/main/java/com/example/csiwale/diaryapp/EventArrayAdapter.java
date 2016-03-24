package com.example.csiwale.diaryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by csiwale on 2/17/2016.
 */
public class EventArrayAdapter extends ArrayAdapter<Event>{
    public EventArrayAdapter(Context context, ArrayList<Event> event) {
        super(context, 0, event);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_row, parent, false);
        }
        // Lookup view for data population
        TextView txtEventTitle = (TextView) convertView.findViewById(R.id.textEventType);
        TextView txtEventTime = (TextView) convertView.findViewById(R.id.textEventTime);
        TextView txtEventNote = (TextView) convertView.findViewById(R.id.textEventNote);
        // Populate the data into the template view using the data object
        txtEventTitle.setText(event.getEventType());
        txtEventTime.setText(event.getEventDay()+" "+event.getEventTime());
        txtEventNote.setText(event.getEventNote());
        // Return the completed view to render on screen
        return convertView;
    }
}

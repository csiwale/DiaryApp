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
public class NewsArrayAdapter extends ArrayAdapter<NewsItem>{
    public NewsArrayAdapter(Context context, ArrayList<NewsItem> newsItem) {
        super(context, 0, newsItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NewsItem newsItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_row, parent, false);
        }
        // Lookup view for data population
        TextView txtTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView txtOverview = (TextView) convertView.findViewById(R.id.textText);
        TextView txtCreatedAt = (TextView) convertView.findViewById(R.id.textCreatedAt);
        // Populate the data into the template view using the data object
        txtTitle.setText(newsItem.getTitle());
        txtOverview.setText(newsItem.getOverview());
        txtCreatedAt.setText(newsItem.getCreatedAt());
        // Return the completed view to render on screen
        return convertView;
    }
}

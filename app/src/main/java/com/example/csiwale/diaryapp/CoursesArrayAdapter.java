package com.example.csiwale.diaryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by csiwale on 2/18/2016.
 */
public class CoursesArrayAdapter extends ArrayAdapter<Course>{
    public CoursesArrayAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Course course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_row, parent, false);
        }
        // Lookup view for data population
        TextView txtCourseTitle = (TextView) convertView.findViewById(R.id.textCourseTitle);
        TextView txtCourseNumber = (TextView) convertView.findViewById(R.id.textCourseNumber);
        TextView txtCreditHours = (TextView) convertView.findViewById(R.id.textCreditHours);
        TextView txtClassDay = (TextView) convertView.findViewById(R.id.textClassDay);
        TextView txtCourseTime = (TextView) convertView.findViewById(R.id.textCourseTime);
        // Populate the data into the template view using the data object
        txtCourseTitle.setText(course.getCourseTitle());
        txtCourseNumber.setText(course.getCourseNumber());
        txtCreditHours.setText(Integer.toString(course.getCreditHours()));
        txtClassDay.setText(course.getClassDay());
        // Return the completed view to render on screen
        return convertView;
    }
}

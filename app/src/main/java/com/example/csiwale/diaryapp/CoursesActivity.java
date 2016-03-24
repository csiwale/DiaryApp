package com.example.csiwale.diaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    protected int contactID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Courses");
        Intent intent = getIntent();
        contactID = intent.getIntExtra("Contact_ID", 0);

        //Log.w("Contact_ID...", ""+contactID);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CoursesActivity.this, NewCourseActivity.class);
                intent.putExtra("Contact_ID", contactID);
                startActivity(intent);
            }
        });

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<Course> courses = db.getAllCourses(contactID);
        CoursesArrayAdapter adapter = new CoursesArrayAdapter(this, courses);
        ListView listView = (ListView)findViewById(R.id.courses_list);
        listView.setAdapter(adapter);
    }

}

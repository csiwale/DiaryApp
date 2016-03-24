package com.example.csiwale.diaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewCourseActivity extends AppCompatActivity {
    protected int contactID;
    protected TextView txtCourseNumber;
    protected TextView txtCourseTitle;
    protected TextView txtCreditHours;
    protected TextView txtClassDay;
    protected TextView txtClassTime;
    protected Course course;
    protected Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        contactID = intent.getIntExtra("Contact_ID", 0);

        final DatabaseHandler db = new DatabaseHandler(this);

        txtCourseNumber = (TextView)findViewById(R.id.txtCourseNumber);
        txtCourseTitle = (TextView)findViewById(R.id.txtCourseTitle);
        txtCreditHours = (TextView)findViewById(R.id.txtCreditHours);
        txtClassDay = (TextView)findViewById(R.id.txtClassDay);
        txtClassTime = (TextView)findViewById(R.id.txtClassTime);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                course = new Course(contactID,
                        txtCourseNumber.getText().toString(),
                        txtCourseTitle.getText().toString(),
                        Integer.parseInt(txtCreditHours.getText().toString()),
                        txtClassDay.getText().toString(),
                        txtClassTime.getText().toString());
                db.addCourse(course);

                Intent intent = new Intent(NewCourseActivity.this, CoursesActivity.class);
                intent.putExtra("Contact_ID", contactID);
                startActivity(intent);
            }
        });
    }

}

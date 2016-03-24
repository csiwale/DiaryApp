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

public class NewEventActivity extends AppCompatActivity {

    protected Button btnSave;
    protected TextView txtEventType;
    protected TextView txtEventDay;
    protected TextView txtEventTime;
    protected TextView txtEventNote;
    protected Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DatabaseHandler db = new DatabaseHandler(this);

        txtEventType = (TextView)findViewById(R.id.txtEventDay);
        txtEventDay = (TextView)findViewById(R.id.txtEventDay);
        txtEventTime = (TextView)findViewById(R.id.txtEventTime);
        txtEventNote = (TextView)findViewById(R.id.txtEventNote);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event = new Event(txtEventType.getText().toString(),
                        txtEventDay.getText().toString(),
                        txtEventTime.getText().toString(),
                        txtEventNote.getText().toString());
                db.addEvent(event);

                Intent intent = new Intent(NewEventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}

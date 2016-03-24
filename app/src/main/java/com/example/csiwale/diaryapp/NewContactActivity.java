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

public class NewContactActivity extends AppCompatActivity {
    protected Button btnSave;
    protected TextView txtFirstName;
    protected TextView txtLastName;
    protected TextView txtOfficeAddress;
    protected TextView txtEmailAddress;
    protected Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        final DatabaseHandler db = new DatabaseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtFirstName = (TextView)findViewById(R.id.txtFirstName);
        txtLastName = (TextView)findViewById(R.id.txtLastName);
        txtOfficeAddress = (TextView)findViewById(R.id.txtOfficeAddress);
        txtEmailAddress = (TextView)findViewById(R.id.txtEmailAddress);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                contact = new Contact(txtFirstName.getText().toString(),
                        txtLastName.getText().toString(),
                        txtOfficeAddress.getText().toString(),
                        txtEmailAddress.getText().toString());
                db.addContact(contact);

                Intent intent = new Intent(NewContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

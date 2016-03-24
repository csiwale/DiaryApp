package com.example.csiwale.diaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by csiwale on 2/12/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "DiaryAppDB";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts table name
    private static final String TABLE_COURSES = "courses";
    private static final String TABLE_EVENTS = "events";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + "("
                + "Course_ID" + " INTEGER PRIMARY KEY,"
                + "Contact_ID" + " INTEGER,"
                + "CourseNumber" + " TEXT,"
                + "CourseTitle" + " TEXT,"
                + "CreditHours" + " INTEGER,"
                + "ClassDay" + " TEXT,"
                + "ClassTime" + " TEXT" + ")";
        db.execSQL(CREATE_COURSES_TABLE);
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + "Contact_ID" + " INTEGER PRIMARY KEY,"
                + "FirstName" + " TEXT,"
                + "LastName" + " TEXT,"
                + "OfficeAddress" + " TEXT,"
                + "EmailAddress" + " TEXT,"
                + "OfficeHoursStartTime" + " INTEGER,"
                + "OfficeHoursEndTime" + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + "Event_ID" + " INTEGER PRIMARY KEY,"
                + "EventType" + " TEXT,"
                + "EventDay" + " TEXT,"
                + "EventTime" + " TEXT,"
                + "EventNote" + " TEXT" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        // Create tables again
        onCreate(db);
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FirstName", contact.getFirstName());
        values.put("LastName", contact.getLastName());
        values.put("OfficeAddress", contact.getOfficeAddress());
        values.put("EmailAddress", contact.getEmailAddress());
        values.put("OfficeHoursStartTime", contact.getOfficeHoursStartTime());
        values.put("OfficeHoursEndTime", contact.getOfficeHoursEndTime());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { "Contact_ID",
                        "FirstName", "LastName", "OfficeAddress", "EmailAddress", "OfficeHoursStartTime", "OfficeHoursEndTime" }, "Contact_ID" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                                        cursor.getString(1),
                                        cursor.getString(2),
                                        cursor.getString(3),
                                        cursor.getString(4),
                                        Integer.parseInt(cursor.getString(5)),
                                        Integer.parseInt(cursor.getString(6)));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContactID(Integer.parseInt(cursor.getString(0)));
                contact.setFirstName(cursor.getString(1));
                contact.setLastName(cursor.getString(2));
                contact.setOfficeAddress(cursor.getString(3));
                contact.setEmailAddress(cursor.getString(4));
                contact.setOfficeHoursStartTime(Integer.parseInt(cursor.getString(5)));
                contact.setOfficeHoursEndTime(Integer.parseInt(cursor.getString(6)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FirstName", contact.getFirstName());
        values.put("LastName", contact.getLastName());
        values.put("OfficeAddress", contact.getOfficeAddress());
        values.put("EmailAddress", contact.getEmailAddress());
        values.put("OfficeHoursStartTime", contact.getOfficeHoursStartTime());
        values.put("OfficeHoursEndTime", contact.getOfficeHoursEndTime());

        // updating row
        return db.update(TABLE_CONTACTS, values, "Contact_ID" + " = ?",
                new String[]{String.valueOf(contact.getContactID())});
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, "Contact_ID" + " = ?",
                new String[]{String.valueOf(contact.getContactID())});
        db.close();
    }

    // Adding new course
    public void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Contact_ID", course.getContact_ID());
        values.put("CourseNumber", course.getCourseNumber());
        values.put("CourseTitle", course.getCourseTitle());
        values.put("CreditHours", course.getCreditHours());
        values.put("ClassDay", course.getClassDay());
        values.put("ClassTime", course.getClassTime());

        // Inserting Row
        db.insert(TABLE_COURSES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single course
    public Course getCourse(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COURSES, new String[]{"Course_ID", "Contact_ID",
                        "CourseNumber", "CourseTitle", "CreditHours", "ClassDay", "ClassTime"}, "Course_ID" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Course course = new Course(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                Integer.parseInt(cursor.getString(4)),
                                cursor.getString(5),
                                cursor.getString(6));
        // return course
        return course;
    }

    // Getting All Courses
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courseList = new ArrayList<Course>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_COURSES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setCourse_ID(Integer.parseInt(cursor.getString(0)));
                course.setContact_ID(Integer.parseInt(cursor.getString(1)));
                course.setCourseNumber(cursor.getString(2));
                course.setCourseTitle(cursor.getString(3));
                course.setCreditHours(Integer.parseInt(cursor.getString(4)));
                course.setClassDay(cursor.getString(5));
                course.setClassTime(cursor.getString(6));
                // Adding course to list
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        // return course list
        return courseList;
    }

    // Getting All Courses by Contact ID
    public ArrayList<Course> getAllCourses(int Contact_ID) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COURSES + " WHERE Contact_ID = " + Contact_ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setCourse_ID(Integer.parseInt(cursor.getString(0)));
                course.setContact_ID(Integer.parseInt(cursor.getString(1)));
                course.setCourseNumber(cursor.getString(2));
                course.setCourseTitle(cursor.getString(3));
                course.setCreditHours(Integer.parseInt(cursor.getString(4)));
                course.setClassDay(cursor.getString(5));
                course.setClassTime(cursor.getString(6));
                // Adding course to list
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        // return course list
        return courseList;
    }

    // Adding new event
    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("EventType", event.getEventType());
        values.put("EventDay", event.getEventDay());
        values.put("EventTime", event.getEventTime());
        values.put("EventNote", event.getEventNote());

        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single event
    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, new String[] { "Event_ID", "EventType", "EventDay", "EventTime", "EventNote"}, "Event_ID" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        // return course
        return event;
    }

    // Getting All events
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setEventID(Integer.parseInt(cursor.getString(0)));
                event.setEventType(cursor.getString(1));
                event.setEventDay(cursor.getString(2));
                event.setEventTime(cursor.getString(3));
                event.setEventNote(cursor.getString(4));
                // Adding course to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return course list
        return eventList;
    }

}

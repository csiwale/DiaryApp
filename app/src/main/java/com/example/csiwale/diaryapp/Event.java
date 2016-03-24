package com.example.csiwale.diaryapp;

/**
 * Created by csiwale on 2/25/2016.
 */
public class Event {
    private int eventID;
    private String eventType;
    private String eventDay;
    private String eventTime;
    private String eventNote;

    public Event(){
    }

    public Event(int eventID, String eventType, String eventDay, String eventTime, String eventNote) {
        this.eventID = eventID;
        this.eventType = eventType;
        this.eventDay = eventDay;
        this.eventTime = eventTime;
        this.eventNote = eventNote;
    }

    public Event(String eventType, String eventDay, String eventTime, String eventNote) {
        this.eventType = eventType;
        this.eventDay = eventDay;
        this.eventTime = eventTime;
        this.eventNote = eventNote;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDay() {
        return eventDay;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }
}

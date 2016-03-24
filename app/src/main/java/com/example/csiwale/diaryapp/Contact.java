package com.example.csiwale.diaryapp;

import java.sql.Time;

/**
 * Created by csiwale on 2/12/2016.
 */
public class Contact {
    private int contactID;
    private String firstName;
    private String lastName;
    private String officeAddress;
    private String emailAddress;
    private int officeHoursStartTime;
    private int officeHoursEndTime;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String officeAddress, String emailAddress, int officeHoursStartTime, int officeHoursEndTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeAddress = officeAddress;
        this.emailAddress = emailAddress;
        this.officeHoursStartTime = officeHoursStartTime;
        this.officeHoursEndTime = officeHoursEndTime;
    }

    public Contact(String firstName, String lastName, String officeAddress, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeAddress = officeAddress;
        this.emailAddress = emailAddress;
    }

    public Contact(int contactID, String firstName, String lastName, String officeAddress, String emailAddress, int officeHoursStartTime, int officeHoursEndTime) {
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeAddress = officeAddress;
        this.emailAddress = emailAddress;
        this.officeHoursStartTime = officeHoursStartTime;
        this.officeHoursEndTime = officeHoursEndTime;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int id) {
        this.contactID = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getOfficeHoursStartTime() {
        return officeHoursStartTime;
    }

    public void setOfficeHoursStartTime(int officeHoursStartTime) {
        this.officeHoursStartTime = officeHoursStartTime;
    }

    public int getOfficeHoursEndTime() {
        return officeHoursEndTime;
    }

    public void setOfficeHoursEndTime(int officeHoursEndTime) {
        this.officeHoursEndTime = officeHoursEndTime;
    }
}


package com.example.csiwale.diaryapp;

/**
 * Created by csiwale on 2/18/2016.
 */
public class Course {
    private int Course_ID;
    private int Contact_ID;
    private String CourseNumber;
    private String CourseTitle;
    private int CreditHours;
    private String ClassDay;
    private String ClassTime;

    public Course(int contact_ID, String courseNumber, String courseTitle, int creditHours, String classDay, String classTime) {
        Contact_ID = contact_ID;
        CourseNumber = courseNumber;
        CourseTitle = courseTitle;
        CreditHours = creditHours;
        ClassDay = classDay;
        ClassTime = classTime;
    }

    public Course(int course_ID, int contact_ID, String courseNumber, String courseTitle, int creditHours, String classDay, String classTime) {

        Course_ID = course_ID;
        Contact_ID = contact_ID;
        CourseNumber = courseNumber;
        CourseTitle = courseTitle;
        CreditHours = creditHours;
        ClassDay = classDay;
        ClassTime = classTime;
    }

    public Course() {
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public String getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        CourseNumber = courseNumber;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }

    public int getCreditHours() {
        return CreditHours;
    }

    public void setCreditHours(int creditHours) {
        CreditHours = creditHours;
    }

    public String getClassDay() {
        return ClassDay;
    }

    public void setClassDay(String classDay) {
        ClassDay = classDay;
    }

    public String getClassTime() {
        return ClassTime;
    }

    public void setClassTime(String classTime) {
        ClassTime = classTime;
    }
}

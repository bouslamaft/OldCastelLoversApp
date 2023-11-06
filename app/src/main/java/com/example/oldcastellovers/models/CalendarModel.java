package com.example.oldcastellovers.models;

import java.util.Date;

public class CalendarModel {

    private int calendarID;
    private Date date;

    public CalendarModel(int calendarID, Date date) {
        this.calendarID = calendarID;
        this.date = date;
    }

    @Override
    public String toString() {
        return "CalendarModel{" +
                "calendarID=" + calendarID +
                ", date=" + date +
                '}';
    }

    public int getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(int calendarID) {
        this.calendarID = calendarID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

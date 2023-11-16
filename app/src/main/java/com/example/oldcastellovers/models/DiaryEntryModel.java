package com.example.oldcastellovers.models;

import java.util.Date;

public class DiaryEntryModel {
    private int entryID;
    private Date date;
    private String notes;
    private String mediaPath;

    public DiaryEntryModel(int entryID, Date date, String notes, String mediaPath) {
        this.entryID = entryID;
        this.date = date;
        this.notes = notes;
        this.mediaPath = mediaPath;
    }

    @Override
    public String toString() {
        return "DiaryEntryModel{" +
                "entryID=" + entryID +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                ", mediaPath='" + mediaPath + '\'' +
                '}';
    }

    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}

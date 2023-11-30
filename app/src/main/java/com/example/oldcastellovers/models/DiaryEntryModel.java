package com.example.oldcastellovers.models;

import java.util.ArrayList;
import java.util.Date;

public class DiaryEntryModel {
    private int entryID;
    private String date;
    private String castleName;
    private String castleLocation;
    private String website;
    private String notes;
    private ArrayList<String> mediaPath;


    public DiaryEntryModel(int entryID, String date, String castleName, String castleLocation, String website, String notes, ArrayList<String> mediaPath) {
        this.entryID = entryID;
        this.date = date;
        this.castleName = castleName;
        this.castleLocation = castleLocation;
        this.website = website;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<String> getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(ArrayList<String> mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getCastleName() {
        return castleName;
    }

    public void setCastleName(String castleName) {
        this.castleName = castleName;
    }

    public String getCastleLocation() {
        return castleLocation;
    }

    public void setCastleLocation(String castleLocation) {
        this.castleLocation = castleLocation;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}

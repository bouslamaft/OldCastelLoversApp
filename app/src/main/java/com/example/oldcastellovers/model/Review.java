package com.example.oldcastellovers.model;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("author_name")
    private String authorName;
    private int rating;
    @SerializedName("relative_time_description")
    private String date;
    private String text;
    @SerializedName("profile_photo_url")
    private String profilePhotoUrl;

    public String getAuthorName() {
        return authorName;
    }

    public int getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
}

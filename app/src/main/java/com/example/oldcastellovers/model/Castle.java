package com.example.oldcastellovers.model;

import com.example.oldcastellovers.model.Photo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Castle {
    @SerializedName("formatted_address")
    private String formattedAddress;
    private String name;

    @SerializedName("place_id")
    private String placeId;
    private double rating;
    private String url;
    private ArrayList<Photo> photos;

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public String getName() {
        return name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public double getRating() {
        return rating;
    }

    public String getUrl() {
        return url;
    }

}
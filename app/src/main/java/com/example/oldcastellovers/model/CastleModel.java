package com.example.oldcastellovers.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
// TODO erase this class and use Castle instead
public class CastleModel {
    private String name;
    @SerializedName("formatted_address")
    private String formattedAddress;
    @SerializedName("place_id")
    private String placeId;
    private double rating;
    private ArrayList<Photo> photos;

    public CastleModel(String name, String formattedAddress, String placeId, double rating, ArrayList<Photo> photos) {
        this.name = name;
        this.formattedAddress = formattedAddress;
        this.placeId = placeId;
        this.rating = rating;
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "CastleModel{" +
                "name='" + name + '\'' +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", placeId='" + placeId + '\'' +
                ", rating=" + rating +
                ", photos=" + photos +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }
}

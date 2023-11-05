package com.example.oldcastellovers.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    private int height;
    private int weight;
    @SerializedName("photo_reference")
    private String photoReference;

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getReference() {
        return photoReference;
    }
}

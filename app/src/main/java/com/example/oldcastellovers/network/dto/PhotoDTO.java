package com.example.oldcastellovers.network.dto;

import com.google.gson.annotations.SerializedName;

public class PhotoDTO {

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

package com.example.oldcastellovers.models;

public class LikedCastleModel {
    private String placeId;
    private String castleName;
    private String address;
    private double rating;
    private String photoReference;

    public LikedCastleModel(String placeId, String castleName, String address, double rating, String photoReference) {
        this.placeId = placeId;
        this.castleName = castleName;
        this.address = address;
        this.rating = rating;
        this.photoReference = photoReference;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getCastleName() {
        return castleName;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setCastleName(String castleName) {
        this.castleName = castleName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
}

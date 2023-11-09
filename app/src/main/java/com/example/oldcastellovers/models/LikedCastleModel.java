package com.example.oldcastellovers.models;

public class LikedCastleModel {
    private String castleID;
    private String castleName;
    private String address;
    private String website;
    private boolean isLiked;
    private double rating;
    private String photoReference;

    public LikedCastleModel(String castleID, String castleName, String address, String website, boolean isLiked, double rating, String photoReference) {
        this.castleID = castleID;
        this.castleName = castleName;
        this.address = address;
        this.website = website;
        this.isLiked = isLiked;
        this.rating = rating;
        this.photoReference = photoReference;
    }

    @Override
    public String toString() {
        return "LikedCastleModel{" +
                "castleID='" + castleID + '\'' +
                ", castleName='" + castleName + '\'' +
                ", address='" + address + '\'' +
                ", website='" + website + '\'' +
                ", isLiked=" + isLiked +
                ", rating=" + rating +
                ", photoReference='" + photoReference + '\'' +
                '}';
    }

    public String getCastleID() {
        return castleID;
    }

    public void setCastleID(String castleID) {
        this.castleID = castleID;
    }

    public String getCastleName() {
        return castleName;
    }

    public void setCastleName(String castleName) {
        this.castleName = castleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
}

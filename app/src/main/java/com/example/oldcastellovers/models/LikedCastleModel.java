package com.example.oldcastellovers.models;

public class LikedCastleModel {
    private int castleID;
    private String castleName;
    private String country;
    private String region;
    private String website;
    private boolean isLiked;

    public LikedCastleModel(int castleID, String castleName, String country, String region, String website, boolean isLiked) {
        this.castleID = castleID;
        this.castleName = castleName;
        this.country = country;
        this.region = region;
        this.website = website;
        this.isLiked = isLiked;
    }

    @Override
    public String toString() {
        return "LikedCastleModel{" +
                "castleID=" + castleID +
                ", castleName='" + castleName + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", website='" + website + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }

    public int getCastleID() {
        return castleID;
    }

    public void setCastleID(int castleID) {
        this.castleID = castleID;
    }

    public String getCastleName() {
        return castleName;
    }

    public void setCastleName(String castleName) {
        this.castleName = castleName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
}

package com.example.oldcastellovers.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class CastleDTO {
    @SerializedName("formatted_address")
    private String formattedAddress;
    private String name;
    @SerializedName("place_id")
    private String placeId;
    private double rating;
    private String url;
    private ArrayList<PhotoDTO> photos;
    @SerializedName("user_ratings_total")
    private int userRatingTotal;
    @SerializedName("wheelchair_accessible_entrance")
    private boolean wheelchairAccessibleEntrance;
    private String website;
    private ArrayList<ReviewDTO> reviews;
    @SerializedName("editorial_summary")
    private Map<String,String> editorialSummary;
    @SerializedName("current_opening_hours")
    private Map<String,Object> currentOpeningHours;
    @SerializedName("international_phone_number")
    private String internationalPhoneNumber;
    @SerializedName("formatted_phone_number")
    private String formattedPhoneNumber;

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

    public ArrayList<PhotoDTO> getPhotos() {
        return photos;
    }
    public int getUserRatingTotal() {
        return userRatingTotal;
    }

    public boolean isWheelchairAccessibleEntrance() {
        return wheelchairAccessibleEntrance;
    }

    public String getWebsite() {
        return website;
    }

    public ArrayList<ReviewDTO> getReviews() {
        return reviews;
    }

    public Map<String, String> getEditorialSummary() {
        return editorialSummary;
    }

    public Map<String, Object> getCurrentOpeningHours() {
        return currentOpeningHours;
    }

    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }
}
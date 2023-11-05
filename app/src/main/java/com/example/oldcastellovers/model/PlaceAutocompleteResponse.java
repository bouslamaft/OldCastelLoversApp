package com.example.oldcastellovers.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class PlaceAutocompleteResponse {
    private String status;
    @SerializedName("predictions")
    private ArrayList<PlacePredictions> placePredictions;

    public String getStatus() {
        return status;
    }

    public ArrayList<PlacePredictions> getPlacePredictions() {
        return placePredictions;
    }

    public class PlacePredictions{
        @SerializedName("place_id")
        private String placeId;

        public String getPlaceId() {
            return placeId;
        }
    }
}

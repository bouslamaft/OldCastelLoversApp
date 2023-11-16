package com.example.oldcastellovers.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlaceTextSearchResponse {
    private String status;
    private ArrayList<CastleModel> results;

    public String getStatus() {
        return status;
    }

    public ArrayList<CastleModel> getResults() {
        return results;
    }
}

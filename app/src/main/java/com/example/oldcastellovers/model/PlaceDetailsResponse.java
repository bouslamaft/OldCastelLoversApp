package com.example.oldcastellovers.model;
import com.google.gson.annotations.SerializedName;

public class PlaceDetailsResponse {
    private String status;

    @SerializedName("result")
    private Castle result;

    public String getStatus() {
        return status;
    }

    public Castle getResult() {
        return result;
    }
}

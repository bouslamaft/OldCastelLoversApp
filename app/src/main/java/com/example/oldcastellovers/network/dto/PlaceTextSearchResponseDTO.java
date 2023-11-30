package com.example.oldcastellovers.network.dto;

import java.util.ArrayList;

public class PlaceTextSearchResponseDTO {
    private String status;
    private ArrayList<CastleDTO> results;

    public String getStatus() {
        return status;
    }

    public ArrayList<CastleDTO> getResults() {
        return results;
    }
}

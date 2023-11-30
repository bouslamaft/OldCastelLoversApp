package com.example.oldcastellovers.network.dto;
import com.example.oldcastellovers.network.dto.CastleDTO;
import com.google.gson.annotations.SerializedName;

public class PlaceDetailsResponseDTO {
    private String status;

    @SerializedName("result")
    private CastleDTO result;

    public String getStatus() {
        return status;
    }

    public CastleDTO getResult() {
        return result;
    }
}

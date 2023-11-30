package com.example.oldcastellovers.network.api;

import com.example.oldcastellovers.network.dto.PlaceAutocompleteResponseDTO;
import com.example.oldcastellovers.network.dto.PlaceDetailsResponseDTO;
import com.example.oldcastellovers.network.dto.PlaceTextSearchResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("maps/api/place/details/json")
    Call<PlaceDetailsResponseDTO> getPlaceDetails(
            @Query("fields") String fields,
            @Query("place_id") String placeId,
            @Query("key") String apiKey
    );

    @GET("maps/api/place/autocomplete/json")
    Call<PlaceAutocompleteResponseDTO> getPlacesByAutocomplete(
            @Query("input") String input,
            @Query("types") String types,
            @Query("key") String apiKey
    );
    @GET("maps/api/place/textsearch/json")
    Call<PlaceTextSearchResponseDTO> getPlacesByTextSearch(
            @Query("query") String query,
            @Query("key") String apiKey
    );
}

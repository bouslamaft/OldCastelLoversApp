package com.example.oldcastellovers;

import com.example.oldcastellovers.model.PlaceAutocompleteResponse;
import com.example.oldcastellovers.model.PlaceDetailsResponse;
import com.example.oldcastellovers.model.PlaceTextSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("maps/api/place/details/json")
    Call<PlaceDetailsResponse> getPlaceDetails(
            @Query("fields") String fields,
            @Query("place_id") String placeId,
            @Query("key") String apiKey
    );

    @GET("maps/api/place/autocomplete/json")
    Call<PlaceAutocompleteResponse> getPlacesByAutocomplete(
            @Query("input") String input,
            @Query("types") String types,
            @Query("key") String apiKey
    );
    @GET("maps/api/place/textsearch/json")
    Call<PlaceTextSearchResponse> getPlacesByTextSearch(
            @Query("query") String query,
            @Query("key") String apiKey
    );
}

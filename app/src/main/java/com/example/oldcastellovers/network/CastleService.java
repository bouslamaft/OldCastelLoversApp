package com.example.oldcastellovers.network;

import androidx.annotation.NonNull;

import com.example.oldcastellovers.network.dto.CastleDTO;
import com.example.oldcastellovers.network.api.ApiService;
import com.example.oldcastellovers.BuildConfig;
import com.example.oldcastellovers.network.dto.PlaceAutocompleteResponseDTO;
import com.example.oldcastellovers.network.dto.PlaceDetailsResponseDTO;
import com.example.oldcastellovers.network.dto.PlaceTextSearchResponseDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CastleService {

    public interface CastleDetailsCallback {
        void onCastleDetailsFetched(CastleDTO castleDTO);
        void onError(String errorMessage);
    }

    public interface CastlesCallback {
        void onCastlesFetched(ArrayList<CastleDTO> castleList);
        void onError(String errorMessage);
    }
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ArrayList<CastleDTO> castleList;
    private CastleDTO castleDTO;

    public CastleService(){}

    public  ArrayList<CastleDTO> getCastlesByTextSearch(String query,CastlesCallback callback){
        ApiService apiService = retrofit.create(ApiService.class);
        String apiKey = BuildConfig.MY_API_KEY;

        Call<PlaceTextSearchResponseDTO> call = apiService.getPlacesByTextSearch(query + " castle",apiKey);
        call.enqueue(new Callback<PlaceTextSearchResponseDTO>() {
            @Override
            public void onResponse(Call<PlaceTextSearchResponseDTO> call, Response<PlaceTextSearchResponseDTO> response) {
                if (response.isSuccessful()){
                    PlaceTextSearchResponseDTO placeTextSearchResponseDTO = response.body();
                    if (placeTextSearchResponseDTO != null){
                        ArrayList<CastleDTO> castleDTOS = placeTextSearchResponseDTO.getResults();
                        if (!castleDTOS.isEmpty()) {
                            castleList = castleDTOS;
                            callback.onCastlesFetched(castleList);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<PlaceTextSearchResponseDTO> call, Throwable t) {

            }
        });
        return castleList;
    }

    // the old method by place autocomplete

    public CastleDTO getCastleDetails(String placeId, CastleDetailsCallback callback) {
        ApiService apiService = retrofit.create(ApiService.class);
        String fields = "name,place_id,formatted_address,rating,url,photos,reviews,website,current_opening_hours," +
                "wheelchair_accessible_entrance,editorial_summary,user_ratings_total";
        String apiKey = BuildConfig.MY_API_KEY;

        Call<PlaceDetailsResponseDTO> call = apiService.getPlaceDetails(fields, placeId, apiKey);

        call.enqueue(new Callback<PlaceDetailsResponseDTO>() {
            @Override
            public void onResponse(@NonNull Call<PlaceDetailsResponseDTO> call, @NonNull Response<PlaceDetailsResponseDTO> response) {
                if (response.isSuccessful()) {
                    PlaceDetailsResponseDTO placeDetails = response.body();
                    if (placeDetails != null) {
                        castleDTO = placeDetails.getResult();
                        if (castleDTO != null) {
                            callback.onCastleDetailsFetched(castleDTO);
                        }
                    }
                } else {
                    // Handle the error
                }
            }
            @Override
            public void onFailure(Call<PlaceDetailsResponseDTO> call, Throwable t) {
                // Handle the network failure
            }
        });
        return castleDTO;
    }

    public void getPredictions(String query) {
        ApiService apiService = retrofit.create(ApiService.class);
        String types = "point_of_interest";
        String apiKey = BuildConfig.MY_API_KEY; // Replace with your API key

        Call<PlaceAutocompleteResponseDTO> call = apiService.getPlacesByAutocomplete(query, types, apiKey);

        call.enqueue(new Callback<PlaceAutocompleteResponseDTO>() {
            @Override
            public void onResponse(Call<PlaceAutocompleteResponseDTO> call, Response<PlaceAutocompleteResponseDTO> response) {
                if (response.isSuccessful()) {
                    PlaceAutocompleteResponseDTO placeAutocompleteResponseDTO = response.body();
                    if (placeAutocompleteResponseDTO != null) {
                        ArrayList<PlaceAutocompleteResponseDTO.PlacePredictions> predictions = placeAutocompleteResponseDTO.getPlacePredictions();
                        for (PlaceAutocompleteResponseDTO.PlacePredictions _predictions : predictions) {
                            //getCastlesByAutocomplete(_predictions.getPlaceId());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceAutocompleteResponseDTO> call, Throwable t) {
                // Handle the network failure
            }
        });
    }

}

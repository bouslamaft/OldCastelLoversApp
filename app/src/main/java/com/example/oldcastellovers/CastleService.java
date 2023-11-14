package com.example.oldcastellovers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.model.Castle;
import com.example.oldcastellovers.model.CastleModel;
import com.example.oldcastellovers.model.PlaceAutocompleteResponse;
import com.example.oldcastellovers.model.PlaceDetailsResponse;
import com.example.oldcastellovers.model.PlaceTextSearchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CastleService {

    public interface CastleDetailsCallback {
        void onCastleDetailsFetched(Castle castle);
        void onError(String errorMessage);
    }
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ArrayList<CastleModel> castleList;
    private Castle castle;
    private RecyclerView recyclerView;

    public CastleService(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
    public CastleService(){}

    public  ArrayList<CastleModel> getCastlesByTextSearch(String query){
        ApiService apiService = retrofit.create(ApiService.class);
        String apiKey = BuildConfig.MY_API_KEY;

        Call<PlaceTextSearchResponse> call = apiService.getPlacesByTextSearch(query + " castle",apiKey);
        call.enqueue(new Callback<PlaceTextSearchResponse>() {
            @Override
            public void onResponse(Call<PlaceTextSearchResponse> call, Response<PlaceTextSearchResponse> response) {
                if (response.isSuccessful()){
                    PlaceTextSearchResponse placeTextSearchResponse = response.body();
                    if (placeTextSearchResponse != null){
                        ArrayList<CastleModel> castleModel = placeTextSearchResponse.getResults();
                        if (!castleModel.isEmpty()) {
                            castleList = castleModel;
                            updateUIWithCastles(castleList);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<PlaceTextSearchResponse> call, Throwable t) {

            }
        });
        return castleList;
    }

    // the old method by place autocomplete

    public Castle getCastleDetails(String placeId,CastleDetailsCallback callback) {
        ApiService apiService = retrofit.create(ApiService.class);
        String fields = "name,place_id,formatted_address,rating,url,photos,reviews,website,current_opening_hours," +
                "wheelchair_accessible_entrance,editorial_summary,user_ratings_total";
        String apiKey = BuildConfig.MY_API_KEY;

        Call<PlaceDetailsResponse> call = apiService.getPlaceDetails(fields, placeId, apiKey);

        call.enqueue(new Callback<PlaceDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlaceDetailsResponse> call, @NonNull Response<PlaceDetailsResponse> response) {
                if (response.isSuccessful()) {
                    PlaceDetailsResponse placeDetails = response.body();
                    if (placeDetails != null) {
                        castle = placeDetails.getResult();
                        if (castle != null) {
                            callback.onCastleDetailsFetched(castle);
                        }
                    }
                } else {
                    // Handle the error
                }
            }
            @Override
            public void onFailure(Call<PlaceDetailsResponse> call, Throwable t) {
                // Handle the network failure
            }
        });
        return castle;
    }

    public void getPredictions(String query) {
        ApiService apiService = retrofit.create(ApiService.class);
        String types = "point_of_interest";
        String apiKey = BuildConfig.MY_API_KEY; // Replace with your API key

        Call<PlaceAutocompleteResponse> call = apiService.getPlacesByAutocomplete(query, types, apiKey);

        call.enqueue(new Callback<PlaceAutocompleteResponse>() {
            @Override
            public void onResponse(Call<PlaceAutocompleteResponse> call, Response<PlaceAutocompleteResponse> response) {
                if (response.isSuccessful()) {
                    PlaceAutocompleteResponse placeAutocompleteResponse = response.body();
                    if (placeAutocompleteResponse != null) {
                        ArrayList<PlaceAutocompleteResponse.PlacePredictions> predictions = placeAutocompleteResponse.getPlacePredictions();
                        for (PlaceAutocompleteResponse.PlacePredictions _predictions : predictions) {
                            //getCastlesByAutocomplete(_predictions.getPlaceId());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceAutocompleteResponse> call, Throwable t) {
                // Handle the network failure
            }
        });
    }

    private void updateUIWithCastles(ArrayList<CastleModel> castlesList) {
        CastleAdapter adapter = new CastleAdapter(castleList);
        recyclerView.setAdapter(adapter);
    }
}

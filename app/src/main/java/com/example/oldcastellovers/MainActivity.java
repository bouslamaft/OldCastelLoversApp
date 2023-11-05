package com.example.oldcastellovers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.example.oldcastellovers.model.CastleModel;
import com.example.oldcastellovers.model.PlaceAutocompleteResponse;
import com.example.oldcastellovers.model.PlaceDetailsResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText searchBarEditText;
    private RecyclerView recyclerView;
    private ArrayList<CastleModel> castleList = new ArrayList<>();
    private CastleService castleService;

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        searchBarEditText = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.castles_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        CastleAdapter adapter = new CastleAdapter(castleList);
        recyclerView.setAdapter(adapter);

        searchBarEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchBarEditText.getWindowToken(), 0);

                castleService = new CastleService(recyclerView);

                if (searchBarEditText.getText().toString().isEmpty()) {
                    searchBarEditText.setText("Please enter search query");
                    //TODO error handling empty query
                } else {
                    if (castleList != null) {
                        castleList.clear();
                    }
                    //TODO because of the nature of asynchronous request, castleList comes back null
                    castleList = castleService.getCastlesByTextSearch(searchBarEditText.getText().toString());

                    // Notify the adapter that the data set has changed
                    adapter.notifyDataSetChanged();

                    updateUIWithCastles(castleList);
                }
                return true;
            }
            return false;
        });

    }

    private void updateUIWithCastles(ArrayList<CastleModel> castleList) {
        CastleAdapter adapter = new CastleAdapter(castleList);
        recyclerView.setAdapter(adapter);
    }
}

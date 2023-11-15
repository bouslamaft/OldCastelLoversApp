package com.example.oldcastellovers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.example.oldcastellovers.model.Castle;
import com.example.oldcastellovers.model.CastleModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CastleService.CastlesCallback {
    private TextInputEditText searchBarEditText;
    private RecyclerView recyclerView;
    private ArrayList<CastleModel> castleList = new ArrayList<>();
    private CastleService castleService = new CastleService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        searchBarEditText = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.castles_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        searchBarEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchBarEditText.getWindowToken(), 0);

                if (searchBarEditText.getText().toString().isEmpty()) {
                    searchBarEditText.setText("Please enter search query");
                    //TODO error handling empty query
                } else {
                    if (castleList != null) {
                        castleList.clear();
                    }
                    castleList = castleService.getCastlesByTextSearch(searchBarEditText.getText().toString(),this);
                }
                return true;
            }
            return false;
        });

    }

    @Override
    public void onCastlesFetched(ArrayList<CastleModel> castleList) {
        updateUIWithCastles(castleList);
    }

    @Override
    public void onError(String errorMessage) {
        //TODO error handling
    }
    private void updateUIWithCastles(ArrayList<CastleModel> castleList) {
        CastleAdapter adapter = new CastleAdapter(castleList);
        recyclerView.setAdapter(adapter);
    }
}

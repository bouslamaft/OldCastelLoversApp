package com.example.oldcastellovers.UI.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.example.oldcastellovers.network.CastleService;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.adapters.CastleAdapter;
import com.example.oldcastellovers.network.dto.CastleDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements CastleService.CastlesCallback {
    private TextInputEditText searchBarEditText;
    private RecyclerView castleRecyclerView;
    private ArrayList<CastleDTO> castleList = new ArrayList<>();
    private CastleService castleService = new CastleService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


        // navigation menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    // Do nothing, stay in the same screen
                    return true;
                } else if (item.getItemId() == R.id.navigation_bookmark) {
                    Intent likedCastleIntent = new Intent(HomePageActivity.this, LikedCastleActivity.class);
                    startActivity(likedCastleIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_Gallery) {
                    Intent galleryIntent = new Intent(HomePageActivity.this, GalleryActivity.class);
                    startActivity(galleryIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_diary) {
                    Intent DiaryIntent = new Intent(HomePageActivity.this, DiaryEntryActivity.class);
                    startActivity(DiaryIntent);
                    return true;
                }
                return false;
            }
        });

        searchBarEditText = findViewById(R.id.search_bar);
        castleRecyclerView = findViewById(R.id.castles_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        castleRecyclerView.setLayoutManager(layoutManager);


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
    public void onCastlesFetched(ArrayList<CastleDTO> castleList) {
        updateUIWithCastles(castleList);
    }

    @Override
    public void onError(String errorMessage) {
        //TODO error handling
    }
    private void updateUIWithCastles(ArrayList<CastleDTO> castleList) {
        CastleAdapter adapter = new CastleAdapter(castleList);
        castleRecyclerView.setAdapter(adapter);
    }
}
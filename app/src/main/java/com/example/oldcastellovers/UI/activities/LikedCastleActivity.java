package com.example.oldcastellovers.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.UI.adapters.LikedCastleAdapter;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.database.models.LikedCastleModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class LikedCastleActivity extends AppCompatActivity implements LikedCastleAdapter.OnDeleteClickListener {

    private DataBaseHelper dataBaseHelper;
    RecyclerView likedCastleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liked_recycler_view);

        //navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    Intent HomePageIntent = new Intent(LikedCastleActivity.this, HomePageActivity.class);
                    startActivity(HomePageIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_bookmark) {
                    // do nothing
                    return true;
                } else if (item.getItemId() == R.id.navigation_Gallery) {
                    Intent galleryIntent = new Intent(LikedCastleActivity.this, GalleryActivity.class);
                    startActivity(galleryIntent);
                    return true;
                }
                return false;
            }
        });


        likedCastleRecyclerView = findViewById(R.id.likedCastleRecyclerView);

        dataBaseHelper = new DataBaseHelper(this);

        List<LikedCastleModel> likedCastleModels = dataBaseHelper.getAll();

        // Populate views with castle details
        if (likedCastleModels != null) {
            LikedCastleAdapter likedCastleAdapter = new LikedCastleAdapter(this, likedCastleModels);
            likedCastleAdapter.setOnDeleteClickListener(new LikedCastleAdapter.OnDeleteClickListener() {
                @Override
                public void onDeleteClick(LikedCastleModel likedCastleModels) {

                    boolean deleted = dataBaseHelper.deleteOne(likedCastleModels);
                    if (deleted) {
                        Toast.makeText(LikedCastleActivity.this, "Castle deleted", Toast.LENGTH_SHORT).show();
                        refreshRecyclerView();
                    } else {
                        Toast.makeText(LikedCastleActivity.this, "Failed to delete castle", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            likedCastleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            likedCastleRecyclerView.setAdapter(likedCastleAdapter);
        }
    }

    @Override
    public void onDeleteClick(LikedCastleModel likedCastleModel) {
        boolean deleted = dataBaseHelper.deleteOne(likedCastleModel);

        if (deleted) {
            Toast.makeText(this, "Castle deleted", Toast.LENGTH_SHORT).show();
            // Refresh the RecyclerView after deletion
            refreshRecyclerView();
        } else {
            Toast.makeText(this, "Failed to delete castle", Toast.LENGTH_SHORT).show();
        }
    }
    private void refreshRecyclerView() {
        List<LikedCastleModel> updatedCastleList = dataBaseHelper.getAll();

        LikedCastleAdapter updatedLikedCastleAdapter = new LikedCastleAdapter(this, updatedCastleList);
        updatedLikedCastleAdapter.setOnDeleteClickListener(this);
        likedCastleRecyclerView.setAdapter(updatedLikedCastleAdapter);
    }
}

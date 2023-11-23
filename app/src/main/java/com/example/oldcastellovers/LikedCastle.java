package com.example.oldcastellovers;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.model.CastleModel;

import java.util.List;

public class LikedCastle extends AppCompatActivity implements CastleAdapter.OnDeleteClickListener {

    private ImageView photoImageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private RatingBar ratingBar;
    private TextView ratingNumberTextView;

    private DataBaseHelper dataBaseHelper;
    RecyclerView recyclerView;
    ImageButton deleteIcon;
    CastleModel castleModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liked_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);


        // Initialize database helper
        dataBaseHelper = new DataBaseHelper(this);

        // Fetch castle details from the database (replace with your logic)
        //CastleModel castleModel = dataBaseHelper.getAll().get(0);
        List<CastleModel> castleList = dataBaseHelper.getAll();

        // Populate views with castle details
        if (castleList != null) {
            CastleAdapter castleAdapter = new CastleAdapter(this, castleList);
            castleAdapter.setOnDeleteClickListener(new CastleAdapter.OnDeleteClickListener() {
                @Override
                public void onDeleteClick(CastleModel castleModel) {
                    // Handle the delete action here
                    boolean deleted = dataBaseHelper.deleteOne(castleModel);

                    if (deleted) {
                        Toast.makeText(LikedCastle.this, "Castle deleted", Toast.LENGTH_SHORT).show();
                        // Refresh the RecyclerView after deletion
                        refreshRecyclerView();
                    } else {
                        Toast.makeText(LikedCastle.this, "Failed to delete castle", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(castleAdapter);

            // You may need to load the image using a library like Picasso or Glide
            // For example, if you have a photo reference in CastleModel
            // Picasso.get().load(castleModel.getPhotoReference()).into(photoImageView);
        }
    }

    @Override
    public void onDeleteClick(CastleModel castleModel) {
        boolean deleted = dataBaseHelper.deleteOne(castleModel);

        if (deleted) {
            Toast.makeText(this, "Castle deleted", Toast.LENGTH_SHORT).show();
            // Refresh the RecyclerView after deletion
            refreshRecyclerView();
        } else {
            Toast.makeText(this, "Failed to delete castle", Toast.LENGTH_SHORT).show();
        }
    }
    private void refreshRecyclerView() {
        // Fetch castle details from the database
        List<CastleModel> updatedCastleList = dataBaseHelper.getAll();
        // Update the RecyclerView with the new data
        CastleAdapter updatedCastleAdapter = new CastleAdapter(this, updatedCastleList);
        updatedCastleAdapter.setOnDeleteClickListener(this);
        recyclerView.setAdapter(updatedCastleAdapter);
    }
}

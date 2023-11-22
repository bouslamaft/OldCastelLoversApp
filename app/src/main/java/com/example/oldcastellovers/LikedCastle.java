package com.example.oldcastellovers;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.model.CastleModel;

import java.util.List;

public class LikedCastle extends AppCompatActivity {

    private ImageView photoImageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private RatingBar ratingBar;
    private TextView ratingNumberTextView;

    private DataBaseHelper dataBaseHelper;
    RecyclerView recyclerView;

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
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(castleAdapter);

            // You may need to load the image using a library like Picasso or Glide
            // For example, if you have a photo reference in CastleModel
            // Picasso.get().load(castleModel.getPhotoReference()).into(photoImageView);
        }
    }
}

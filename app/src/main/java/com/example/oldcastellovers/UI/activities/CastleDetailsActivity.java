package com.example.oldcastellovers.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.oldcastellovers.network.dto.CastleDTO;
import com.example.oldcastellovers.network.dto.PhotoDTO;
import com.example.oldcastellovers.network.CastleService;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.CastleViewModel;
import com.example.oldcastellovers.UI.adapters.PhotoPagerAdapter;
import com.example.oldcastellovers.UI.adapters.TabAdapter;
import com.example.oldcastellovers.UI.fragments.AboutFragment;
import com.example.oldcastellovers.UI.fragments.OverviewFragment;
import com.example.oldcastellovers.UI.fragments.ReviewsFragment;
import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.network.dto.ReviewDTO;
import com.example.oldcastellovers.database.models.LikedCastleModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CastleDetailsActivity extends AppCompatActivity implements CastleService.CastleDetailsCallback {

    private CastleService castleService = new CastleService();
    private CastleDTO castleDTO;
    ViewPager photoViewPager;
    TextView castleNameTextView;
    PhotoPagerAdapter photoPagerAdapter;
    RatingBar ratingBar;
    private TabLayout tabLayout;
    private ViewPager tabViewPager;
    private Button bookmarkButton;
    private Button createEntryButton;
    private TabLayout photoTablayout;
    private ReviewsFragment reviewsFragment;
    List<String> photoReferenceList = new ArrayList<>();
    private List<ReviewDTO> reviewDTOList = new ArrayList();
    private TabAdapter tabAdapter;
    private CastleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.castle_details);

        String placeId = getIntent().getStringExtra("placeId");

        photoViewPager = findViewById(R.id.photoViewPager);
        castleNameTextView = findViewById(R.id.castleNameTextView);
        ratingBar = findViewById(R.id.detailsRatingBar);
        tabLayout = findViewById(R.id.tabLayout);
        tabViewPager = findViewById(R.id.tabViewPager);
        bookmarkButton = findViewById(R.id.bookmarkButton);
        createEntryButton = findViewById(R.id.createEntryButton);
        photoTablayout = findViewById(R.id.photoTabLayout);

        photoPagerAdapter = new PhotoPagerAdapter(photoReferenceList);
        photoViewPager.setAdapter(photoPagerAdapter);
        photoTablayout.setupWithViewPager(photoViewPager);

        viewModel = new ViewModelProvider(this).get(CastleViewModel.class);

        castleDTO = castleService.getCastleDetails(placeId, this);

        setupViewPager();
    }

    @Override
    public void onCastleDetailsFetched(CastleDTO castleDTO) {
        castleNameTextView.setText(castleDTO.getName());
        ratingBar.setRating((float) castleDTO.getRating());

        //setting the castle object to the ViewModel in order to get the live object inside of the fragment class.
        viewModel.setCastle(castleDTO);

        if (castleDTO.getPhotos() != null && !castleDTO.getPhotos().isEmpty()) {
            for (PhotoDTO photoDTO : castleDTO.getPhotos()) {
                photoReferenceList.add(photoDTO.getReference());
            }
        }
        photoPagerAdapter.notifyDataSetChanged();
        tabAdapter.notifyDataSetChanged();

        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LikedCastleModel likedCastleModel = new LikedCastleModel(castleDTO.getPlaceId(), castleDTO.getName(), castleDTO.getFormattedAddress(),
                        castleDTO.getRating(), castleDTO.getPhotos().get(0).getReference());
                //Toast.makeText(CastleDetailsActivity.this, "Castle not bookmarked!!!! \n TRY AGAIN", Toast.LENGTH_SHORT).show();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(CastleDetailsActivity.this);

                boolean success = dataBaseHelper.addOne(likedCastleModel);
                if (success) {
                    Toast.makeText(CastleDetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(CastleDetailsActivity.this, LikedCastleActivity.class);
                startActivity(intent);

            }
        });

        createEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CastleDetailsActivity.this, EntryPageActivity.class);

                // Pass castle details to EntryPageActivity
                intent.putExtra("castleName", castleDTO.getName());
                intent.putExtra("castleAddress", castleDTO.getFormattedAddress());
                intent.putExtra("castleWebsite", castleDTO.getWebsite());

                // Start EntryPageActivity
                startActivity(intent);
            }
        });


    }

    @Override
    public void onError(String errorMessage) {
        //TODO error handling
    }

    private void setupViewPager() {
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        OverviewFragment overviewFragment = new OverviewFragment();
        ReviewsFragment reviewsFragment = new ReviewsFragment();
//        this.reviewsFragment = reviewsFragment; // Store the reference to the ReviewsFragment
        AboutFragment aboutFragment = new AboutFragment();

        tabAdapter.addFragment(overviewFragment, "Overview");
        tabAdapter.addFragment(reviewsFragment, "Reviews");
        tabAdapter.addFragment(aboutFragment, "About");

        tabViewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(tabViewPager);
    }
}

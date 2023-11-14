package com.example.oldcastellovers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.oldcastellovers.fragments.AboutFragment;
import com.example.oldcastellovers.fragments.OverviewFragment;
import com.example.oldcastellovers.fragments.ReviewsFragment;
import com.example.oldcastellovers.model.Castle;
import com.example.oldcastellovers.model.Photo;
import com.example.oldcastellovers.model.Review;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CastleDetailsActivity extends AppCompatActivity implements CastleService.CastleDetailsCallback {

    private CastleService castleService = new CastleService();
    private Castle castle;
    ViewPager photoViewPager;
    TextView castleNameTextView;
    PhotoPagerAdapter photoPagerAdapter;
    ReviewAdapter reviewAdapter;
    RatingBar ratingBar;
    private TabLayout tabLayout;
    private ViewPager tabViewPager;
    private Button bookmarkButton;
    private Button createEntryButton;
    private ReviewsFragment reviewsFragment;
    List<String> photoReferenceList = new ArrayList<>();
    private List<Review> reviewList = new ArrayList();
    private TabAdapter tabAdapter;

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

        photoPagerAdapter = new PhotoPagerAdapter(photoReferenceList);
        photoViewPager.setAdapter(photoPagerAdapter);

        castle = castleService.getCastleDetails(placeId, this);


        setupViewPager();
    }

    @Override
    public void onCastleDetailsFetched(Castle castle) {
        castleNameTextView.setText(castle.getName());
        ratingBar.setRating((float) castle.getRating());

        for (Photo photo : castle.getPhotos()) {
            photoReferenceList.add(photo.getReference());
        }

//        if (reviewsFragment != null) {
//            reviewList = castle.getReviews();
//            reviewAdapter.setReviewList(reviewList);
//            reviewAdapter.notifyDataSetChanged();
//        }

        photoPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errorMessage) {
        // Handle error
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

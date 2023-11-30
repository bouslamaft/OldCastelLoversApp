package com.example.oldcastellovers.UI.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.CastleViewModel;
import com.example.oldcastellovers.UI.adapters.ReviewAdapter;
import com.example.oldcastellovers.network.dto.CastleDTO;

public class ReviewsFragment extends Fragment {

    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;
    private CastleViewModel viewModel;
    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        reviewsRecyclerView = view.findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(CastleViewModel.class);

        // Initialize and set up the adapter
        reviewAdapter = new ReviewAdapter();
        reviewsRecyclerView.setAdapter(reviewAdapter);

        // Observe the review list in the ViewModel
        viewModel.getCastle().observe(getViewLifecycleOwner(), new Observer<CastleDTO>() {
            @Override
            public void onChanged(CastleDTO castleDTO) {
                updateUI(castleDTO);
            }
        });

        return view;
    }

    private void updateUI(CastleDTO castleDTO) {
        reviewAdapter.setReviewList(castleDTO.getReviews());
    }
}

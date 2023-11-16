package com.example.oldcastellovers.UI.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.CastleViewModel;
import com.example.oldcastellovers.model.Castle;

import android.widget.TextView;

import java.util.Objects;

public class OverviewFragment extends Fragment {

    private TextView addressTextView;
    private TextView websiteTextView;
    private CastleViewModel viewModel;
    private Castle _castle;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        addressTextView = view.findViewById(R.id.overviewAddress);
        websiteTextView = view.findViewById(R.id.overviewWebsite);

        viewModel = new ViewModelProvider(requireActivity()).get(CastleViewModel.class);
        if (viewModel.getCastle() != null ){
            _castle = viewModel.getCastle().getValue();
            if (_castle != null){
                addressTextView.setText(_castle.getFormattedAddress());
                websiteTextView.setText(_castle.getWebsite());
            }
              }



        return view;
    }
/*    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CastleViewModel.class);
        viewModel.getCastle().observe(getViewLifecycleOwner(), castle -> {
            // Update UI with castle data
            if (castle != null) {
                updateUI(castle);
            } else {
                // Handle the case when data is null or not available
                // You can show an error message or take appropriate action
            }
        });

    }
    private void updateUI(Castle castle) {
        // Update your UI components with the castle data
        addressTextView.setText(castle.getName());
        websiteTextView.setText(castle.getWebsite());
    }*/
}

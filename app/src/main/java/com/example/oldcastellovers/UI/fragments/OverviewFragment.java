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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.CastleViewModel;
import com.example.oldcastellovers.UI.adapters.OverviewAdapter;
import com.example.oldcastellovers.network.dto.CastleDTO;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class OverviewFragment extends Fragment {

    private TextView addressTextView;
    private TextView websiteTextView;
    private TextView openingHoursTextView;
    private LinearLayout openingHoursLayout;
    private LinearLayout websiteLayout;
    private CastleViewModel viewModel;
    private boolean openingHoursVisible = false;
    private CastleDTO _castleDTO;
    private RecyclerView recyclerView;
    private ImageView collapseIcon;

    public OverviewFragment() {
        // Required empty public constructor
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressTextView = view.findViewById(R.id.overviewAddress);
        websiteTextView = view.findViewById(R.id.overviewWebsite);
        openingHoursTextView = view.findViewById(R.id.overviewOpeningHours);
        recyclerView = view.findViewById(R.id.openingHoursRecyclerView);
        collapseIcon = view.findViewById(R.id.collapseIcon);
        openingHoursLayout = view.findViewById(R.id.overviewOpeningHoursLayout);
        websiteLayout = view.findViewById(R.id.websiteLayout);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(CastleViewModel.class);

        viewModel.getCastle().observe(getViewLifecycleOwner(), new Observer<CastleDTO>() {
            @Override
            public void onChanged(CastleDTO castleDTO) {
                if (castleDTO != null) {
                    _castleDTO = castleDTO;
                    updateUI(castleDTO);
                } else {
                    // Handle the case when data is null or not available
                }
            }
        });
    }

    private void updateUI(CastleDTO castleDTO) {
        addressTextView.setText(castleDTO.getFormattedAddress());
        websiteTextView.setText(castleDTO.getWebsite() != null ? castleDTO.getWebsite() : castleDTO.getUrl());


        if (castleDTO.getCurrentOpeningHours() != null){
            String openNow;
            if ((Boolean) castleDTO.getCurrentOpeningHours().get("open_now")){
                openNow = "Open Now";
            }else
                openNow = "Closed Now";

            openingHoursTextView.setText(openNow);


            OverviewAdapter adapter = new OverviewAdapter((List<String>) castleDTO.getCurrentOpeningHours().get("weekday_text"));
            recyclerView.setAdapter(adapter);
            openingHoursLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    collapseIcon.setImageResource(openingHoursVisible ? R.drawable.expand_more_icon : R.drawable.expand_less_icon);
                    toggleOpeningHours();
                }
            });
        }else
            openingHoursLayout.setVisibility(View.GONE);
    }
    public void toggleOpeningHours() {
        if (openingHoursVisible) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
        openingHoursVisible = !openingHoursVisible;
    }
}
package com.example.oldcastellovers.UI.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.CastleViewModel;
import com.example.oldcastellovers.model.Castle;

public class AboutFragment extends Fragment {

    private TextView summaryText;
    private ImageView accessibilityIcon;
    private CastleViewModel viewModel;
    private View grayLine;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        summaryText = view.findViewById(R.id.summaryText);
        accessibilityIcon = view.findViewById(R.id.accessibilityIcon);
        grayLine = view.findViewById(R.id.grayLine);

        viewModel = new ViewModelProvider(requireActivity()).get(CastleViewModel.class);

        viewModel.getCastle().observe(getViewLifecycleOwner(), new Observer<Castle>() {
            @Override
            public void onChanged(Castle castle) {
                accessibilityIcon.setImageResource(castle.isWheelchairAccessibleEntrance() ? R.drawable.done_icon: R.drawable.block_icon);
                updateUI(castle);
            }
        });

        return view;
    }

    private void updateUI(Castle castle) {
        if (castle.getEditorialSummary() != null) {
            summaryText.setText(castle.getEditorialSummary().get("overview"));
        }
        else {
            summaryText.setVisibility(View.GONE);
            grayLine.setVisibility(View.GONE);
        }
    }
}



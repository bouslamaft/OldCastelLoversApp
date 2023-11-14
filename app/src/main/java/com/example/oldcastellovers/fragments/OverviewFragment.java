package com.example.oldcastellovers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.oldcastellovers.R;
import android.widget.TextView;

public class OverviewFragment extends Fragment {

    private TextView addressTextView;
    private TextView websiteTextView;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        addressTextView = view.findViewById(R.id.overviewAddress);
        websiteTextView = view.findViewById(R.id.overviewWebsite);

        // Get the data (address and website) from your Castle object
        // Replace the following placeholders with actual data from your Castle object
        String address = "123 Castle St, Castle City";
        String website = "https://www.example.com/castle";

        // Set the text for address and website TextViews
        addressTextView.setText(address);
        websiteTextView.setText(website);

        return view;
    }
}

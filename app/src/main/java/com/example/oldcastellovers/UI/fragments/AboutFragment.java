package com.example.oldcastellovers.UI.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.oldcastellovers.R;

public class AboutFragment extends Fragment {

    private TextView nameTextView;
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        nameTextView = view.findViewById(R.id.aboutCastleName);

        nameTextView.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return view;
    }
}

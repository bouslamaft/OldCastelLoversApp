package com.example.oldcastellovers.UI.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.adapters.DiaryEntryAdapter;
import com.example.oldcastellovers.UI.adapters.DiaryEntryMediaAdapter;
import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.database.models.DiaryEntryModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntryDetailsPageActivity extends AppCompatActivity {

    Button datePickerButton;

    TextView textViewCastleContent, textViewlocationContent, textViewWebsiteContent, DisplayTexts;
    DataBaseHelper dataBaseHelper;
    RecyclerView diary_recycler_view;
    DiaryEntryMediaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_details_page);

        // Get the entry ID from the intent
        int entryId = getIntent().getIntExtra("entryid", -1);


        datePickerButton = findViewById(R.id.datePickerButton);
        textViewCastleContent = findViewById(R.id.textViewCastleContent);
        textViewlocationContent = findViewById(R.id.textViewlocationContent);
        textViewWebsiteContent = findViewById(R.id.textViewWebsiteContent);
        DisplayTexts = findViewById(R.id.DisplayTexts);
        diary_recycler_view = findViewById(R.id.diary_recycler_view);


        dataBaseHelper  = new DataBaseHelper(EntryDetailsPageActivity.this);

        // Fetch the DiaryEntryModel from the database based on entryId
        DiaryEntryModel entryDetails = dataBaseHelper.getDiaryEntryDetails(entryId);

        Log.d("media", "Media path " + entryDetails.getMediaPath());

        // Display the values in the UI components
        if (entryDetails != null) {
            datePickerButton.setText(entryDetails.getDate());
            textViewCastleContent.setText(entryDetails.getCastleName());
            textViewlocationContent.setText(entryDetails.getCastleLocation());
            textViewWebsiteContent.setText(entryDetails.getWebsite());
            DisplayTexts.setText(entryDetails.getNotes());


        }
        // Remove duplicates from the media paths
        ArrayList<String> uniqueMediaPaths = removeDuplicates(entryDetails.getMediaPath());


//        adapter = new DiaryEntryAdapter(this, diaryEntryModels);
//        entryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        entryRecyclerView.setAdapter(adapter);

        //DiaryEntryModel diaryEntryModels = dataBaseHelper.getDiaryEntryDetails(entryId);

        // Set up RecyclerView with a grid layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        diary_recycler_view.setLayoutManager(layoutManager);

        adapter = new DiaryEntryMediaAdapter( this, uniqueMediaPaths);
        diary_recycler_view.setAdapter(adapter);

    }

    // Helper method to remove duplicates from a list
    private ArrayList<String> removeDuplicates(ArrayList<String> list) {
        Set<String> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }
}
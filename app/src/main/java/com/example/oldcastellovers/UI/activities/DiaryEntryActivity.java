package com.example.oldcastellovers.UI.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.UI.adapters.DiaryEntryAdapter;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.database.models.DiaryEntryModel;

import java.util.List;

public class DiaryEntryActivity extends AppCompatActivity {

    RecyclerView entryRecyclerView;
    private DataBaseHelper dataBaseHelper;
    private DiaryEntryAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_entry_recycler_view);
        entryRecyclerView = findViewById(R.id.entryrecyclerView);

        dataBaseHelper = new DataBaseHelper(this);

        List<DiaryEntryModel> diaryEntryModels = dataBaseHelper.getCastleDetailsWithMediaPath();

        adapter = new DiaryEntryAdapter(this, diaryEntryModels);
        entryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        entryRecyclerView.setAdapter(adapter);
    }
}

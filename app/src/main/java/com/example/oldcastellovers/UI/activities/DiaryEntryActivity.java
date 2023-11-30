package com.example.oldcastellovers.UI.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.UI.adapters.DiaryEntryAdapter;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.database.models.DiaryEntryModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        sortDiaryEntryModels(diaryEntryModels);

        adapter = new DiaryEntryAdapter(this, diaryEntryModels);
        entryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        entryRecyclerView.setAdapter(adapter);
    }

    // sorting by entry date and castle name
    private void sortDiaryEntryModels(List<DiaryEntryModel> diaryEntryModels) {
        Collections.sort(diaryEntryModels, new Comparator<DiaryEntryModel>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.getDefault());

            @Override
            public int compare(DiaryEntryModel model1, DiaryEntryModel model2) {
                try {
                    Date date1 = dateFormat.parse(model1.getDate());
                    Date date2 = dateFormat.parse(model2.getDate());

                    // Compare dates first (descending order - most recent first)
                    int dateComparison = date2.compareTo(date1);
                    if (dateComparison != 0) {
                        return dateComparison;
                    }

                    // If dates are the same, compare castle names alphabetically
                    return model1.getCastleName().compareToIgnoreCase(model2.getCastleName());
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0; // Handle the exception according to your needs
                }
            }
        });
    }
}

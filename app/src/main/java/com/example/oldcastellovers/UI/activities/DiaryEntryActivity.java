package com.example.oldcastellovers.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.UI.adapters.DiaryEntryAdapter;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.database.models.DiaryEntryModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        // navigation:

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    Intent HomePageIntent = new Intent(DiaryEntryActivity.this, HomePageActivity.class);
                    startActivity(HomePageIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_bookmark) {
                    Intent likedCastleIntent = new Intent(DiaryEntryActivity.this, LikedCastleActivity.class);
                    startActivity(likedCastleIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_Gallery) {
                    Intent GalleryIntent = new Intent(DiaryEntryActivity.this, GalleryActivity.class);
                    startActivity(GalleryIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_diary) {
                    // nothing to do.
                    return true;
                }
                return false;
            }
        });

        //adding the button to the menu :
        ImageView filterIcon = findViewById(R.id.filter_icon);
        entryRecyclerView = findViewById(R.id.entryrecyclerView);

        dataBaseHelper = new DataBaseHelper(this);

        List<DiaryEntryModel> diaryEntryModels = dataBaseHelper.getCastleDetailsWithMediaPath();

        // menu
        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(DiaryEntryActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.filter_menu_layout, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menu_date_filter) {
                            sortByDate(diaryEntryModels);
                            adapter.notifyDataSetChanged();
                            return true;
                        } else if (item.getItemId() == R.id.menu_alphabet_order) {
                            sortByAlphabet(diaryEntryModels);
                            adapter.notifyDataSetChanged();
                            return true;  }
                        else if (item.getItemId() == R.id.filter_by_DateANDAlpahabetic) {
                            sortDiaryEntryModels(diaryEntryModels);
                            adapter.notifyDataSetChanged();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });
        adapter = new DiaryEntryAdapter(this, diaryEntryModels);
        entryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        entryRecyclerView.setAdapter(adapter);
    }

    // sorting by entry date and castle name
    public void sortDiaryEntryModels(List<DiaryEntryModel> diaryEntryModels) {
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
    // sort by date:
    public void sortByDate(List<DiaryEntryModel> diaryEntryModels) {
        Collections.sort(diaryEntryModels, new Comparator<DiaryEntryModel>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.getDefault());

            @Override
            public int compare(DiaryEntryModel model1, DiaryEntryModel model2) {
                try {
                    Date date1 = dateFormat.parse(model1.getDate());
                    Date date2 = dateFormat.parse(model2.getDate());

                    // Compare dates (descending order - most recent first)
                    return date2.compareTo(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0; // Handle the exception according to your needs
                }
            }
        });
    }


    // sort by alphabetical order
    public void sortByAlphabet(List<DiaryEntryModel> diaryEntryModels) {
        Collections.sort(diaryEntryModels, new Comparator<DiaryEntryModel>() {
            @Override
            public int compare(DiaryEntryModel model1, DiaryEntryModel model2) {
                // Compare castle names alphabetically
                return model1.getCastleName().compareToIgnoreCase(model2.getCastleName());
            }
        });
    }


}
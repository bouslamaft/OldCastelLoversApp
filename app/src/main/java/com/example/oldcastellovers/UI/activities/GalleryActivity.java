package com.example.oldcastellovers.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.UI.adapters.GalleryAdapter;
import com.example.oldcastellovers.UI.GalleryItem;
import com.example.oldcastellovers.UI.MediaGalleryItem;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.VideoGalleryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;
    private List<MediaGalleryItem> mediaItems; // Declare it here

    private static final int MEDIA_VIEW_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Gallery);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    Intent HomePageIntent = new Intent(GalleryActivity.this, HomePageActivity.class);
                    startActivity(HomePageIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_bookmark) {
                    Intent likedCastleIntent = new Intent(GalleryActivity.this, LikedCastleActivity.class);
                    startActivity(likedCastleIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_Gallery) {
                    // do nothing.
                    return true;
                } else if (item.getItemId() == R.id.navigation_diary) {
                    Intent DiaryIntent = new Intent(GalleryActivity.this, DiaryEntryActivity.class);
                    startActivity(DiaryIntent);
                    return true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mediaItems = loadMediaFromFolders(); // Initialize mediaItems

        galleryAdapter = new GalleryAdapter(this, mediaItems);
        recyclerView.setAdapter(galleryAdapter);

        galleryAdapter.setOnItemClickListener(position -> {
            MediaGalleryItem clickedItem = mediaItems.get(position);
            String mediaPath = clickedItem.getMediaPath();

            Intent intent = new Intent(GalleryActivity.this, MediaViewActivity.class);
            intent.putExtra("mediaPath", mediaPath);
            startActivityForResult(intent, MEDIA_VIEW_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MEDIA_VIEW_REQUEST_CODE && resultCode == RESULT_OK) {
            // A media item has been deleted, refresh the gallery
            mediaItems = loadMediaFromFolders();
            galleryAdapter.updateMediaList(mediaItems);
        }
    }

    private List<MediaGalleryItem> loadMediaFromFolders() {
        List<MediaGalleryItem> mediaItems = new ArrayList<>();

        File picturesFolder = new File(getExternalFilesDir(null), "Pictures");
        File videosFolder = new File(getExternalFilesDir(null), "Video");

        if (picturesFolder.exists() && picturesFolder.isDirectory()) {
            File[] pictureFiles = picturesFolder.listFiles();
            if (pictureFiles != null) {
                for (File file : pictureFiles) {
                    mediaItems.add(new GalleryItem(file.getAbsolutePath()));
                }
            }
        }

        if (videosFolder.exists() && videosFolder.isDirectory()) {
            File[] videoFiles = videosFolder.listFiles();
            if (videoFiles != null) {
                for (File file : videoFiles) {
                    mediaItems.add(new VideoGalleryItem(file.getAbsolutePath()));
                }
            }
        }

        return mediaItems;
    }
}

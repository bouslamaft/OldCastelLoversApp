package com.example.oldcastellovers.UI.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.oldcastellovers.R;

public class EntryDetailsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_details_page);
    }

    private void addMediaItemToLayout(Uri mediaUri) {
        // Create a new LinearLayout to hold the media item and remove button
        LinearLayout mediaItemLayout = new LinearLayout(this);
        mediaItemLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Create an ImageView for the media
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                300, 300  // Set a fixed size for the media item (adjust as needed)
        );
        imageView.setLayoutParams(imageParams);

        // Check if the media is a video and set the thumbnail accordingly
        if (isVideoFile(mediaUri.getPath())) {
            loadVideoThumbnail(imageView, mediaUri);
        } else {
            imageView.setImageURI(mediaUri);
        }

        // Add the ImageView and Remove button to the mediaItemLayout
        mediaItemLayout.addView(imageView);


        // Add the mediaItemLayout to the mediaLayout
        LinearLayout mediaLayout = findViewById(R.id.mediaLayout);
        mediaLayout.addView(mediaItemLayout);
    }



    private void loadVideoThumbnail(ImageView imageView, Uri videoUri) {
        Glide.with(this)
                .load(videoUri)
                .into(imageView);
    }

    protected boolean isVideoFile(String path) {
        // Implement this method to check if the file at the given path is a video file
        // You can use the file extension or other methods to determine the file type
        // For example, you can check if the path ends with ".mp4"
        return path.toLowerCase().endsWith(".mp4");
    }





}


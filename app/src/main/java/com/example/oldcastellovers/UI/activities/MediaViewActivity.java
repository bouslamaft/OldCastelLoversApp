package com.example.oldcastellovers.UI.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.oldcastellovers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class MediaViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_view);

        String mediaPath = getIntent().getStringExtra("mediaPath");

        ImageView imageView = findViewById(R.id.mediaImageView);
        VideoView videoView = findViewById(R.id.mediaVideoView);
        FloatingActionButton deleteButton = findViewById(R.id.deleteButton);

        if (mediaPath.endsWith(".mp4") || mediaPath.endsWith(".3gp") || mediaPath.endsWith(".avi")) {
            // It's a video
            imageView.setVisibility(ImageView.GONE);
            videoView.setVisibility(VideoView.VISIBLE);
            videoView.setVideoURI(Uri.parse(mediaPath));
            videoView.start();
        } else {
            // It's an image
            imageView.setVisibility(ImageView.VISIBLE);
            videoView.setVisibility(VideoView.GONE);
            Glide.with(this)
                    .load(mediaPath)
                    .apply(new RequestOptions().fitCenter())
                    .into(imageView);
        }

        // Set up the delete button click listener
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(mediaPath);
            }
        });
    }

    private void deleteMedia(String mediaPath) {
        File mediaFile = new File(mediaPath);
        if (mediaFile.exists()) {
            mediaFile.delete(); // Delete the file from storage

            // Set the result to indicate that a media item has been deleted
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);

            // Finish the activity
            finish();
        }
    }


    private void showDeleteConfirmationDialog(final String mediaPath) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this media?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle "Yes" button click
                        deleteMedia(mediaPath);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle "No" button click
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}

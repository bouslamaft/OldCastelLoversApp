package com.example.oldcastellovers;


import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoViewActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView = findViewById(R.id.video_view);

        // Get the video path from the intent
        String videoPath = getIntent().getStringExtra("videoPath");

        // Set the video path for the VideoView
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        // Start playing the video
        videoView.start();
    }
}

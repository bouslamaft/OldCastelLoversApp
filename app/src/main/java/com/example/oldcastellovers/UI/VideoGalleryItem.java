package com.example.oldcastellovers.UI;

public class VideoGalleryItem extends MediaGalleryItem {
    private String videoPath;

    public VideoGalleryItem(String videoPath) {
        super(videoPath); // Pass the videoPath to the super constructor
        this.videoPath = videoPath; // Set the videoPath for the VideoGalleryItem
    }

    public String getVideoPath() {
        return videoPath;
    }
}


package com.example.oldcastellovers;

public class GalleryItem extends MediaGalleryItem {
    private String imagePath;

    public GalleryItem(String mediaPath) {
        super(mediaPath);
        this.imagePath = mediaPath; // Use the constructor parameter
    }

    public String getImagePath() {
        return imagePath;
    }
}


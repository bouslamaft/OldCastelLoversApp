package com.example.oldcastellovers;

public class MediaItem {
    private String filePath;
    private MediaType mediaType;

    public MediaItem(String filePath, MediaType mediaType) {
        this.filePath = filePath;
        this.mediaType = mediaType;
    }

    public String getFilePath() {
        return filePath;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public enum MediaType {
        PICTURE,
        VIDEO,
        AUDIO
    }
}


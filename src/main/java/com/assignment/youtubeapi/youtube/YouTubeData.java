package com.assignment.youtubeapi.youtube;

public class YouTubeData {
    Integer id;
    String title;
    String description;
    String thumbnailUrl;
    String videoId;

    public String getVideoId() { return videoId; }

    public void setVideoId(String videoId) { this.videoId = videoId; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

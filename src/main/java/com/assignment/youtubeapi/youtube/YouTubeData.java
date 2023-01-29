package com.assignment.youtubeapi.youtube;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class YouTubeData {
    String title;
    String description;
    String thumbnailUrl;
    LocalDateTime dateTime;
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        String[] parts=dateTime.split("Z");
        this.dateTime=LocalDateTime.parse(parts[0]);
    }
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

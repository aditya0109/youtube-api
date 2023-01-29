package com.assignment.youtubeapi.youtube;

import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseServices {
    private static final String SQL_GET_ALL="select * from db order by date desc limit ? offset ?"; //pageSize,pageNumber*pageSize
    private static final String SQL_INSERT= "insert into youtubeapidb (title, description, datetime, url) "+
                                            "values (?, ?, ?, ?)";
    JdbcTemplate jdbcTemplate;

    public List<YouTubeData> getAllData(Integer pageNumber, Integer pageSize) {
        return jdbcTemplate.queryForList(SQL_GET_ALL, YouTubeData.class, pageSize, pageSize*pageNumber);
    }

    public void loadData(JSONObject res) {
        YouTubeData yt=new YouTubeData();
        for(int i=0;i<res.getJSONArray("items").length();i++) {
            yt.setTitle(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("snippet").getString("title"));
            yt.setDescription(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("snippet").getString("description"));
            yt.setDateTime(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("snippet").getString("publishedAt"));
            yt.setThumbnailUrl(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("snippet").getJSONObject("thumbnails")
                    .getJSONObject("medium").getString("url"));
            saveData(yt);
        }
    }

    private void saveData(YouTubeData yt) {
        jdbcTemplate.update(SQL_INSERT, yt.getTitle(), yt.getDescription(), yt.getDateTime(), yt.getThumbnailUrl());
    }
}

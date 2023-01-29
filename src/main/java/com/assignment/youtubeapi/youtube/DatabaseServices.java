package com.assignment.youtubeapi.youtube;

import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class DatabaseServices {
    private static final String SQL_GET_ALL="select * from youtube limit ? offset ?"; //pageSize,pageNumber*pageSize
    private static final String SQL_INSERT= "insert into youtube (title, description, datetime, url) "+
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
        jdbcTemplate.update(SQL_INSERT, yt.getTitle(), yt.getDescription(), Timestamp.valueOf(yt.getDateTime()), yt.getThumbnailUrl());
    }

    public void test(JSONObject res){
        String t=res.getJSONArray("items").getJSONObject(0)
                .getJSONObject("snippet").getString("title");
        String d=res.getJSONArray("items").getJSONObject(0)
                .getJSONObject("snippet").getString("description");
        String th=res.getJSONArray("items").getJSONObject(0)
                .getJSONObject("snippet").getJSONObject("thumbnails")
                .getJSONObject("medium").getString("url");
        String date = res.getJSONArray("items").getJSONObject(0)
                .getJSONObject("snippet").getString("publishedAt");
        String[] parts=date.split("Z");
        LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
        Timestamp timestamp=Timestamp.valueOf(dateTime);
        jdbcTemplate.update("insert into youtube (title, description, datetime, url) values (?, ?, ?, ?)",
                t,d,timestamp,th);
    }
}

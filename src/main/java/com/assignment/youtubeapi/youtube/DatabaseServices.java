package com.assignment.youtubeapi.youtube;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DatabaseServices {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseServices.class);
    private static final String SQL_GET_ALL="select * from youtube limit ? offset ?"; //pageSize,pageNumber*pageSize
    private static final String SQL_INSERT= "insert into youtube (title, description, url, video_id) "+
                                            "values (?, ?, ?, ?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<YouTubeData> getAllData(Integer pageNumber, Integer pageSize) {
        return jdbcTemplate.query(SQL_GET_ALL, new BeanPropertyRowMapper<YouTubeData>(YouTubeData.class),
                pageSize, pageSize*(pageNumber-1));
    }

    public void loadData(JSONObject res) {
        YouTubeData yt=new YouTubeData();
        for(int i=0;i<res.getJSONArray("items").length();i++) {
            yt.setTitle(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("snippet").getString("title"));
            yt.setDescription(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("snippet").getString("description"));
            yt.setThumbnailUrl(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("snippet").getJSONObject("thumbnails")
                    .getJSONObject("medium").getString("url"));
            yt.setVideoId(res.getJSONArray("items").getJSONObject(i)
                    .getJSONObject("id").getString("videoId"));
            saveData(yt);
        }
    }

    private void saveData(YouTubeData yt) {
        try{
            jdbcTemplate.update(SQL_INSERT, yt.getTitle(), yt.getDescription(), yt.getThumbnailUrl(), yt.getVideoId());
        } catch (Exception e) {
            logger.error("DB error ", e);
        }
    }

    public List<YouTubeData> getAllDataMatching(String keywords, Integer pageNumber, Integer pageSize) {
        String sql = "select * from youtube where title like ? or description like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<YouTubeData>(YouTubeData.class),
                "%"+keywords+"%", "%"+keywords+"%");
    }

}

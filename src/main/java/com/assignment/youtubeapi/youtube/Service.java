package com.assignment.youtubeapi.youtube;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    DatabaseServices db;
    public List<YouTubeData> getAllData(Integer pageNumber, Integer pageSize) {
        return db.getAllData(pageNumber, pageSize);
    }

    public List<YouTubeData> getAllDataMatching(String keywords, Integer pageNumber, Integer pageSize) {
        return db.getAllDataMatching(keywords, pageNumber, pageSize);
    }

    public void loadData(JSONObject res) {
        db.loadData(res);
    }
}

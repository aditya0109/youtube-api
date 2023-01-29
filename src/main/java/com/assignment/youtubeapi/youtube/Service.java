package com.assignment.youtubeapi.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    DatabaseServices db;
    public List<YouTubeData> getAllData(Integer pageNumber, Integer pageSize) {
        return db.getAllData(pageNumber, pageSize);
    }


}

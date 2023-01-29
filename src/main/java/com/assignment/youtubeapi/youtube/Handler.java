package com.assignment.youtubeapi.youtube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/youtube")
public class Handler {

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);
    @Autowired
    Service service;
    @GetMapping("/all")
    public ResponseEntity<List<YouTubeData>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ){
        try{
            List<YouTubeData> data=service.getAllData(pageNumber,pageSize);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e){
            logger.error("Error occurred while getting all data", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<YouTubeData>> getResult(
            @PathVariable("keywords") String keywords,
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ){
        try{
            List<YouTubeData> data=service.getAllDataMatching(keywords,pageNumber,pageSize);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e){
            logger.error("Error occurred while getting matching data", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

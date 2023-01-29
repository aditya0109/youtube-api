package com.assignment.youtubeapi.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/youtube")
public class Handler {
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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

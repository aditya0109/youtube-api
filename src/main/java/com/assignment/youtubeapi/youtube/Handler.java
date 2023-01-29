package com.assignment.youtubeapi.youtube;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/youtube")
public class Handler {

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
    public ResponseEntity<List<YouTubeData>> getResult(@PathVariable("keywords") String keywords){
        return null;
    }

    @GetMapping("/test")
    public String get(){
        String uri="https://www.googleapis.com/youtube/v3/search?part=snippet&q=cricket&key=AIzaSyBPMQAo1X3ZCZLtpXAl1yQlMG5zOHPiPm0&order=date&type=video";
        RestTemplate restTemplate=new RestTemplate();
        String data=restTemplate.getForObject(uri, String.class);
        return data;
    }
}

package com.assignment.youtubeapi;

import com.assignment.youtubeapi.youtube.Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class YoutubeApiApplication {
	private static final Logger logger = LoggerFactory.getLogger(YoutubeApiApplication.class);
	@Autowired
	Service service;

	public static void main(String[] args) {
		SpringApplication.run(YoutubeApiApplication.class, args);
	}
	@Scheduled(fixedRate = 10000L)
	public void schedule(){
		logger.info("API hit");
		String uri="https://www.googleapis.com/youtube/v3/search?part=snippet&q=cricket&key=AIzaSyBPMQAo1X3ZCZLtpXAl1yQlMG5zOHPiPm0&order=date&type=video&publishedAfter=2005-01-01T00:00:00Z";
		RestTemplate restTemplate=new RestTemplate();
		String data=restTemplate.getForObject(uri, String.class);
		JSONObject res=new JSONObject(data);
		service.loadData(res);
	}
}

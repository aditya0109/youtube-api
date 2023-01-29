package com.assignment.youtubeapi;

import com.assignment.youtubeapi.youtube.DatabaseServices;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class YoutubeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeApiApplication.class, args);
		ScheduledExecutorService executorService = Executors
				.newSingleThreadScheduledExecutor();
		DatabaseServices db=new DatabaseServices();
		Runnable task = new Runnable() {
			@Async
			public void run() {
				String uri="https://www.googleapis.com/youtube/v3/search?part=snippet&q=cricket&key=AIzaSyBPMQAo1X3ZCZLtpXAl1yQlMG5zOHPiPm0&order=date&type=video";
				RestTemplate restTemplate=new RestTemplate();
				String data=restTemplate.getForObject(uri, String.class);
				JSONObject res=new JSONObject(data);
				db.loadData(res);
			}
		};
		//executorService.schedule(task, 5, TimeUnit.SECONDS);
		executorService.scheduleAtFixedRate(task,1,10,TimeUnit.SECONDS);
	}
}

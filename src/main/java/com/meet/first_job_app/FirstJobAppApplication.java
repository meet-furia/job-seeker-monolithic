package com.meet.first_job_app;

import com.meet.first_job_app.job.service.JobService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FirstJobAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(FirstJobAppApplication.class, args);
		JobService jobService = context.getBean(JobService.class);
	}

}

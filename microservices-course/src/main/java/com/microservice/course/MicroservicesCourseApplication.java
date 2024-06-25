package com.microservice.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCourseApplication.class, args);
	}

}

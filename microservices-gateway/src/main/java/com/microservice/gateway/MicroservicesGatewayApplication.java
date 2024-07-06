package com.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;
import java.util.Set;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesGatewayApplication.class, args);
	}
}

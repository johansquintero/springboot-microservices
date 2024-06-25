package com.microservice.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//no es obligatoria, pero es recomendable usarla
@SpringBootApplication
public class MicroservicesStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesStudentApplication.class, args);
	}

}

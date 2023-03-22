package com.mycompany.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RatingSpringApp {

	public static void main(String[] args) {
		SpringApplication.run(RatingSpringApp.class,args);

	}

}


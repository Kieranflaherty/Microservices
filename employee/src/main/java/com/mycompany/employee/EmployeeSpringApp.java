package com.mycompany.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@EnableEurekaClient
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class EmployeeSpringApp {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSpringApp.class,args);

	}

}


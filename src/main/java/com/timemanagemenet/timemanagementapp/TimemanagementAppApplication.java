package com.timemanagemenet.timemanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TimemanagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimemanagementAppApplication.class, args);
	}

}

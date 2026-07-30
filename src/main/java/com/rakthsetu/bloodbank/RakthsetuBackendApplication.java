package com.rakthsetu.bloodbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RakthsetuBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RakthsetuBackendApplication.class, args);
	}

}

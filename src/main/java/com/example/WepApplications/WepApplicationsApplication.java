package com.example.WepApplications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WepApplicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WepApplicationsApplication.class, args);
	}

}

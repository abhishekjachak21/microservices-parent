package com.project.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MiddlerwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddlerwareApplication.class, args);
	}

}

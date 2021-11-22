package com.FileClaimService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FileClaimServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileClaimServiceApplication.class, args);
	}

}

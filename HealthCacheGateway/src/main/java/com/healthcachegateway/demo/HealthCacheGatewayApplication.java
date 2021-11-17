package com.healthcachegateway.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class HealthCacheGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCacheGatewayApplication.class, args);
	}
	
	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("This is the fallback");
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/api/user")
	            .uri("http://localhost:8081"))
	        .route(p -> p
	            .path("/api/profile")
	            .uri("http://localhost:8081"))
	        .route(p -> p
	        	.path("/claim")
	        	.uri("http://localhost:8089"))
	        .route(p -> p
	        	.path("/message")
	        	.uri("http://localhost:2727"))
	        .route(p -> p
	        	.path("/subject")
	        	.uri("http://localhost:2727"))
	        .route(p -> p
	        	.path("/usernames/**")
	        	.uri("http://localhost:2727"))
	        .build();
	}
		
	}


package com.GatewayService.driver;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.reactive.config.WebFluxConfigurer;


@Configuration
public class CorsConfigurationSource2 implements WebFluxConfigurer{

	public UrlBasedCorsConfigurationSource corsConfigurationSource2() { 
		CorsConfiguration configuration = new CorsConfiguration(); 
		configuration.setAllowedOrigins(Arrays.asList("*")); 
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS")); 
		configuration.setExposedHeaders(Arrays.asList("*")); 
		configuration.setAllowedHeaders(Arrays.asList("*")); 
		configuration.setAllowCredentials(true); 
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		source.registerCorsConfiguration("/**", configuration); return source; 
	}

}
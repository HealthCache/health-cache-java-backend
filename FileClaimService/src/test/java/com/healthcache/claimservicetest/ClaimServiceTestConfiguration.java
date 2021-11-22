package com.healthcache.claimservicetest;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.FileClaimService.service.ClaimService;
@Profile("test")
@Configuration
public class ClaimServiceTestConfiguration {
	
	@Bean
	@Primary
	public ClaimService claimServ() {
		return Mockito.mock(ClaimService.class);
	}

}

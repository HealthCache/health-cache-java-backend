package com.healthCache.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.healthCache.model.User;
import com.healthCache.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController()
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService uServ;

	@Bean
	@LoadBalanced
	private RestTemplate RestTemplet() {

		return new RestTemplate();
	}

	//@Autowired
	//private RestTemplate rest;
	
	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody LinkedHashMap<String, String> user)
	{
		User u =new User();
		if(uServ.registerUser(u))
		{
			return new ResponseEntity<String>("User was registered",HttpStatus.CREATED);
			
		}
		else
		{
			return new ResponseEntity<String>("Username or email was already taken",HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody LinkedHashMap<String, String> user){
		User u = uServ.loginUser(user.get("username"), user.get("password"));
		
		if(u == null) {
			return new ResponseEntity<User>(u, HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}
	
	
	
}

package com.healthCache.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthCache.model.User;
import com.healthCache.service.UserService;

import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController()
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService uServ;

	
	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody User user)
	{
		User u =new User(0,user.getFirstName(),user.getLastName(),user.getGender(),user.getUsername(),user.getEmail(),user.getPassword(),user.getDob(),user.getRole(),user.getAddressLineOne(),user.getAddressLineTwo(),user.getZipcode(),user.getCity(),user.getPhoneNo(),user.getRelationshipStatus(),user.getProfilePic());
		if(uServ.registerUser(u))
		{
			return new ResponseEntity<String>("User was registered",HttpStatus.CREATED);
			
		}
		else
		{
			return new ResponseEntity<String>("Username or email was already taken",HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/test")
	public String test() {
		return "This controller is being reached.";
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user){
		User u = uServ.loginUser(user.getUsername(), user.getPassword());
		
		if(u == null) {
			return new ResponseEntity<User>(u, HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}
	
	
	
}

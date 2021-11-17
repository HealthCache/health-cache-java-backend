package com.backend.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Username;
import com.backend.service.UsernamesService;

@RestController
@RequestMapping("/usernames")
public class UsernamesController {
	
	@Autowired
	private UsernamesService us;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Username>> getAll() {
		return new ResponseEntity<List<Username>>(us.getAllUsernames(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Username> createUsernames(@RequestBody LinkedHashMap<String, Object> usernames) {
		Username u = new Username();
		return new ResponseEntity<Username>(u, HttpStatus.CREATED);
	}
}

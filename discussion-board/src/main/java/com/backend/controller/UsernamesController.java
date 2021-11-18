package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Username;
import com.backend.service.UsernamesService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usernames")
public class UsernamesController {
	
	@Autowired
	private UsernamesService us;
	
	@GetMapping("/getbyid")
	public ResponseEntity<Username> getById(int id) {
		Username u = us.getUsernameById(id);
		return new ResponseEntity<Username>(u, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Username>> getAll() {
		return new ResponseEntity<List<Username>>(us.getAllUsernames(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Username> create(@RequestBody Username username) {
		Username u = us.createUsername(username);
		return new ResponseEntity<Username>(u, HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Username> update(@RequestBody Username username) {
		Username u = us.updateUsername(username);
		return new ResponseEntity<Username>(u, HttpStatus.CREATED);
	}
}

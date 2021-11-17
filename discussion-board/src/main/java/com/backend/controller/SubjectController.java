package com.backend.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Subject;
import com.backend.service.SubjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	private SubjectService ss;
	
	@GetMapping("/getone")
	public ResponseEntity<Subject> getOne() {
		return new ResponseEntity<Subject>(new Subject(), HttpStatus.OK);		
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Subject>> getAll() {
		return new ResponseEntity<List<Subject>>(ss.getAllSubjects(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Subject> createMessage(@RequestBody Subject subject) throws JsonMappingException, JsonProcessingException {		
		System.out.println(subject);
		return new ResponseEntity<Subject>(subject, HttpStatus.CREATED);
	}
}

package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	private SubjectService ss;
	
	@GetMapping("/getbyid")
	public ResponseEntity<Subject> getOne(@RequestParam int id) {
		Subject s = ss.getSubjectById(id);
		return new ResponseEntity<Subject>(s, HttpStatus.OK);		
	}
	
	@GetMapping("/getbyuser")
	public ResponseEntity<List<Subject>> getByUser(@RequestBody Username u) {
		List<Subject> list = ss.getSubjectsByUser(u);
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Subject>> getAll() {
		List<Subject> list = ss.getAllSubjects();
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
		Subject s = ss.createSubject(subject);
		return new ResponseEntity<Subject>(s, HttpStatus.CREATED);
	}
	
	@PostMapping("/delete") 
	public ResponseEntity<String> deleteSubject(@RequestBody Subject subject) {
		ss.deleteSubject(subject);
		return new ResponseEntity<String>("Subject deleted", HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) {
		Subject s = ss.getSubjectById(subject.getId());
		return new ResponseEntity<Subject>(s, HttpStatus.ACCEPTED);
	}
}

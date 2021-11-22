package com.discussionboard.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.discussionboard.model.Subject;
import com.discussionboard.model.Username;
import com.discussionboard.service.SubjectService;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*")
public class SubjectController {
	
	private SubjectService ss;
	
	@Autowired
	public SubjectController(SubjectService ss) {
		this.ss = ss;
	}
	/**
	 * 
	 * @return an empty Subject object for testing purposes.
	 */
	@GetMapping("/getone")
	public ResponseEntity<Subject> getOne() {
		Subject s = new Subject();
		return new ResponseEntity<Subject>(s, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id the subject id to be found by.
	 * @return a single subject object based on the id provided.
	 */
	@GetMapping("/getbyid")
	public ResponseEntity<Subject> getOne(@RequestParam int id) {
		Subject s = ss.getSubjectById(id);
		return new ResponseEntity<Subject>(s, HttpStatus.OK);		
	}
	
	/**
	 * 
	 * @return a list of all subjects.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<Subject>> getAll() {
		List<Subject> list = ss.getAllSubjects();
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}
	
	/**
	 * 	
	 * @return a list of the ten latest subjects.
	 */
	@GetMapping("/getlatestten")
	public ResponseEntity<List<Subject>> getLatestTen() {
		List<Subject> list = ss.getLastTenOrderById();
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param u is an integer data coming from the client-side
	 * @return a list of user's subjects
	 */
	@PostMapping("/getbyuserid")
	public ResponseEntity<List<Subject>> getByUserId(@RequestParam int id) {
		List<Subject> list = ss.getSubjectsByUserId(id);
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param u is an Username object coming from the client-side
	 * @return a list of user's subjects
	 */
	@PostMapping("/getbyuser")
	public ResponseEntity<List<Subject>> getByUser(@RequestBody Username u) {
		List<Subject> list = ss.getSubjectsByUser(u);
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param vote is a 
	 * @return
	 */
	@PostMapping("/vote")
	public ResponseEntity<Subject> voteSubject(@RequestBody LinkedHashMap<String, Integer> vote) {
		int userid = vote.get("userId");
		int subjectid = vote.get("subjectId");
		Subject s = ss.voteSubject(userid, subjectid);
		return new ResponseEntity<Subject>(s, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param subject
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
		Subject s = ss.createSubject(subject);
		return new ResponseEntity<Subject>(s, HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param subject
	 * @return
	 */
	@PostMapping("/delete") 
	public ResponseEntity<String> deleteSubject(@RequestBody Subject subject) {
		ss.deleteSubject(subject);
		return new ResponseEntity<String>("Subject deleted", HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param subject
	 * @return
	 */
	@PostMapping("/update")
	public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) {
		Subject s = ss.getSubjectById(subject.getId());
		return new ResponseEntity<Subject>(s, HttpStatus.ACCEPTED);
	}
}

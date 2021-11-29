package com.backend.controller;

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

import com.backend.model.Message;
import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.service.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "*")
public class MessageController { 
	
	private MessageService ms;

	@Autowired
	public MessageController(MessageService ms) {
		this.ms = ms;
	}
	
	@GetMapping("/getone")
	public ResponseEntity<Message> getOne() {
		Message m = new Message();
		return new ResponseEntity<Message>(m, HttpStatus.OK);
	}
	
	@GetMapping("/getbyid")
	public ResponseEntity<Message> getById(@RequestParam int id) {
		Message m = ms.getMessageById(id);
		return new ResponseEntity<Message>(m, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Message>> getAll() {
		List<Message> list = ms.getAllMessages();
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/getlatestten")
	public ResponseEntity<List<Message>> getLatestTen() {
		List<Message> list = ms.getLastTenOrderById();
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/getbyuserid")
	public ResponseEntity<List<Message>> getByUser(@RequestParam int id) {
		List<Message> list = ms.getMessagesByUserId(id);
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/getbyuser")
	public ResponseEntity<List<Message>> getByUser(@RequestBody Username u) {
		List<Message> list = ms.getMessagesByUser(u);
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/getbysubjectid")
	public ResponseEntity<List<Message>> getBySubject(@RequestParam int id) {
		List<Message> list = ms.getMessagesBySubjectId(id);
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/getbysubject")
	public ResponseEntity<List<Message>> getBySubject(@RequestBody Subject s) {
		List<Message> list = ms.getMessagesBySubject(s);
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Message> createMessage(@RequestBody LinkedHashMap<String, String> message) {
		Message m = ms.createMessage(message);
		return new ResponseEntity<Message>(m, HttpStatus.CREATED);		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteMessage(@RequestBody Message message) {		
		return new ResponseEntity<String>("Message deleted", HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Message> updateSubject(@RequestBody Message message) {
		Message s = ms.getMessageById(message.getId());
		return new ResponseEntity<Message>(s, HttpStatus.ACCEPTED);
	}
}

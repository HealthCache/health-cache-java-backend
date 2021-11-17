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

import com.backend.model.Message;
import com.backend.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService ms;
	
	@GetMapping("/getone")
	public ResponseEntity<Message> getOne() {
		return new ResponseEntity<Message>(new Message(), HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Message>> getAll() {
		return new ResponseEntity<List<Message>>(ms.getAllMessages(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Message> createMessage(@RequestBody LinkedHashMap<String, String> message) {
		
		Message m = new Message();
		return new ResponseEntity<Message>(m, HttpStatus.CREATED);
		
	}
}
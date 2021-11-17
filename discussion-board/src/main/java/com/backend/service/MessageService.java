package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.model.Message;
import com.backend.repository.MessageRepo;

@Service
public class MessageService {

	private MessageRepo mr;
	
	public MessageService(MessageRepo mr) {
		this.mr = mr;
	}
	
	public List<Message> getAllMessages() {
		return mr.findAll();
	}
}

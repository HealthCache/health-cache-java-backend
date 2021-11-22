package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Message;
import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.repository.MessageRepo;

@Service
public class MessageService {

	private MessageRepo mr;
	
	@Autowired
	public MessageService(MessageRepo mr) {
		this.mr = mr; 
	}
	
	public Message getMessageById(int id) {
		return mr.findById(id).orElse(new Message());
	}
	
	public List<Message> getAllMessages() {
		return mr.findAll(); 
	}
	
	public List<Message> getMessagesByUser(Username u) {
		return mr.findByUsernameId(u.getId());
	}
	
	public List<Message> getMessagesBySubject(Subject s) {
		return mr.findBySubjectId(s.getId());
	}
	
	public List<Message> getLatestTenById() {
		return mr.findLast10ByOrderById();
	}
	
	public Message createMessage(Message message) { 
		return mr.save(message);
	}
	
	public boolean deleteMessage(Message message) {
		boolean flag = false;
		if(mr.findById(message.getId()).isPresent()) {
			mr.delete(message);
			flag = true;
		}
		return flag;
	}
	
	public Message updateMessage(Message message) {
		Message m = null;
		if(mr.findById(message.getId()).isPresent()) {
			m = mr.save(message);
		}
		return m;
	}
}

package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.model.Message;
import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.repository.MessageRepo;

@Service
public class MessageService {

	private MessageRepo mr;
	
	public MessageService(MessageRepo mr) {
		this.mr = mr;
	}
	
	public Message getMessageById(int id) {
		return mr.getById(id);
	}
	
	public List<Message> getAllMessages() {
		return mr.findAll();
	}
	
	public List<Message> getMessagesByUser(Username u) {
		return mr.findByUserId(u.getUsernameId());
	}
	
	public List<Message> getMessagesBySubject(Subject s) {
		return mr.findBySubjectId(s.getSubjectId());
	}
	
	public Message createMessage(Message message) {
		return mr.save(message);
	}
	
	public boolean deleteMessage(Message message) {
		boolean flag = false;
		try {
			mr.delete(message);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Message updateMessage(Message message) {
		Message m = null;
		if(mr.findById(message.getMessageId()).isPresent()) {
			m = mr.save(message);
		}
		return m;
	}
}

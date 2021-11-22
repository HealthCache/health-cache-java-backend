package com.discussionboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discussionboard.model.Message;
import com.discussionboard.model.Subject;
import com.discussionboard.model.Username;
import com.discussionboard.repository.MessageRepo;

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
	
	public List<Message> getMessagesByUserId(int id) {
		return mr.findByUsernameId(id);
	}
	
	public List<Message> getMessagesByUser(Username u) {
		return mr.findByUsernameId(u.getId());
	}
	
	public List<Message> getMessagesBySubjectId(int id) {
		return mr.findBySubjectId(id);
	}
	
	public List<Message> getMessagesBySubject(Subject s) {
		return mr.findBySubjectId(s.getId());
	}
	
	public List<Message> getLastTenOrderById() {
		return mr.findLast10ByOrderByIdDesc();
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

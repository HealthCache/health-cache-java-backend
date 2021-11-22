package com.discussionboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.discussionboard.model.Message;
import com.discussionboard.model.Subject;
import com.discussionboard.model.Username;
import com.discussionboard.repository.MessageRepo;

@Service
public class MessageService {

	private MessageRepo mr;
	
	public MessageService(MessageRepo mr) {
		this.mr = mr; 
	}
	
	public Message getMessageById(int id) {
		return mr.findById(id);
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
		if(mr.findById(message.getId())!=null) {
			mr.delete(message);
			flag = true;
		}
		return flag;
	}
	
	public Message updateMessage(Message message) {
		Message m = null;
		if(mr.findById(message.getId())!=null) {
			m = mr.save(message);
		}
		return m;
	}
}

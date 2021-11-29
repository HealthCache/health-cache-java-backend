package com.backend.service;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Message;
import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.repository.MessageRepo;
import com.backend.repository.SubjectRepo;
import com.backend.repository.UserNamesRepo;

@Service
public class MessageService {

	private MessageRepo mr;
	private UserNamesRepo ur;
	private SubjectRepo sr;
	
	@Autowired
	public MessageService(MessageRepo mr, UserNamesRepo ur, SubjectRepo sr) {
		this.mr = mr;
		this.ur = ur;
		this.sr = sr;
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
	
	public Message createMessage(LinkedHashMap<String, String> message) {
		int username_id = Integer.parseInt(message.get("user_id"));
		int subject_id = Integer.parseInt(message.get("subject_id"));
		String username = message.get("username");
		Username user = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Subject s = sr.findById(subject_id).get();
		Message m = null;
		
		if(username_id!=0&&username!=null) {
			if(user==null)
				user=ur.save(new Username(username_id,username,null,null,null));
		}
		try {
			if(username==null)
				throw new RuntimeException("Problem with username");
			m = mr.save(new Message(0,s,user,message.get("content"),formatter.parse(message.get("timestamp"))));
		}catch (Exception e) {
			m=null;
		}
		System.out.print(m);
		return m;
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

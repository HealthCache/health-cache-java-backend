package com.backend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.repository.SubjectRepo;
import com.backend.repository.UserNamesRepo;


@Service
public class SubjectService {
	
	private SubjectRepo sr;
	private UserNamesRepo ur;
	
	@Autowired	
	public SubjectService(SubjectRepo sr, UserNamesRepo ur) {
		this.sr = sr;
		this.ur = ur;
	}
	
	public Subject getSubjectById(int id) {
		return sr.findById(id).get();
	}
	
	public List<Subject> getLastTenOrderById() {
		return sr.findLast10ByOrderByIdDesc();
	}
	
	public List<Subject> getAllSubjects() {
		return sr.findAll();
	}
	
	public List<Subject> getSubjectsByUserId(int id) {
		return sr.findByUsernameId(id);
	}
	
	public List<Subject> getSubjectsByUser(Username username) {
		return sr.findByUsernameId(username.getId());
	}
	
	public Subject voteSubject(int userid, int subjectid) {
		Username u = ur.findById(userid).get();
		Subject s = sr.findById(subjectid).get();
		Set<Username> votes = s.getVotes();
		votes.add(u);
		s.setVotes(votes);
		
		Set<Subject> votedSubjects = u.getVoteSubjects();
		votedSubjects.add(s);
		
		ur.save(u);
		return sr.save(s);
	}
	
	public Subject createSubject(LinkedHashMap<String, String> subject) {
		int username_id = Integer.parseInt(subject.get("user_id"));
		String username = subject.get("username");
		Username user = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Subject s = null;
		
		if(username_id!=0&&username!=null) {
			if(user==null)
				user=ur.save(new Username(username_id,username,null,null,null));
		}
		try {
			if(username==null)
				throw new RuntimeException("Problem with username");
			s = sr.save(new Subject(0,subject.get("content"),formatter.parse(subject.get("timestamp")),user,null,null));
		}catch (Exception e) {
			s=null;
		}
		System.out.print(s);
		return s;
	}
	
	public boolean deleteSubject(Subject subject) {
		boolean flag = false;
		if(sr.findById(subject.getId()).isPresent()) {
			sr.delete(subject);
			flag = true;
		}
		return flag;
	}
	
	public Subject updateSubject(Subject subject) {
		Subject s = null;
		if(sr.findById(subject.getId()).isPresent()) {
			s = sr.save(subject);
		}
		return s;
	}
}

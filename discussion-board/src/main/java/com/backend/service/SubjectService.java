package com.backend.service;

import java.util.List;
import java.util.Set;

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
	
	public Subject createSubject(Subject subject) {
		Subject s = sr.save(subject);
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

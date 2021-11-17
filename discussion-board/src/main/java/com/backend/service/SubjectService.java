package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.repository.SubjectRepo;

@Service
public class SubjectService {
	
	private SubjectRepo sr;
	
	public SubjectService(SubjectRepo sr) {
		this.sr = sr;
	}
	
	public Subject getSubjectById(int id) {
		return sr.getById(id);
	}
	
	public List<Subject> getAllSubjects() {
		return sr.findAll();
	}
	
	public List<Subject> getSubjectsByUser(Username username) {
		
		return sr.findByUserId(username.getUsernameId());
	}
	
	public Subject createSubject(Subject subject) {
		return sr.save(subject);
	}
	
	public boolean deleteSubject(Subject subject) {
		boolean flag = false;
		try {
			sr.delete(subject);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Subject updateSubject(Subject subject) {
		Subject s = null;
		if(sr.findById(subject.getSubjectId()).isPresent()) {
			s = sr.save(subject);
		}
		return s;
	}
}

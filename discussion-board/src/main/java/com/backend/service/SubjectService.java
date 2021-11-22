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
		return sr.findById(id);
	}
	
	public List<Subject> getLastTenById() {
		return sr.findLast10ByOrderByIdDesc();
	}
	
	public List<Subject> getAllSubjects() {
		return sr.findAll();
	}
	
	public List<Subject> getSubjectsByUser(Username username) {
		
		return sr.findByUsernameId(username.getId());
	}
	
	public Subject createSubject(Subject subject) {
		return sr.save(subject);
	}
	
	public boolean deleteSubject(Subject subject) {
		boolean flag = false;
		if(sr.findById(subject.getId())!=null ) {
			sr.delete(subject);
			flag = true;
		}
		return flag;
	}
	
	public Subject updateSubject(Subject subject) {
		Subject s = null;
		if(sr.findById(subject.getId())!=null) {
			s = sr.save(subject);
		}
		return s;
	}
}

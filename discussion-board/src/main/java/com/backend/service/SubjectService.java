package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.model.Subject;
import com.backend.repository.SubjectRepo;

@Service
public class SubjectService {
	
	private SubjectRepo sr;
	
	public SubjectService(SubjectRepo sr) {
		this.sr = sr;
	}
	
	public List<Subject> getAllSubjects() {
		return sr.findAll();
	}
}

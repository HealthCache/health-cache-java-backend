package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer> {
	public Subject findById(int id);
	public List<Subject> findByUsernameId(int id);
	public List<Subject> findLast10ByOrderByIdDesc();
}

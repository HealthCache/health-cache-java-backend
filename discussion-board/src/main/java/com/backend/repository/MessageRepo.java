package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Message;

public interface MessageRepo extends JpaRepository<Message, Integer> {
	
	public List<Message> findByUsernameId(int id);
	public List<Message> findBySubjectId(int id);
	public List<Message> findLast10ByOrderByIdDesc();
	
}

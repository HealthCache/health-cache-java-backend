package com.healthCache.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthCache.model.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	public List<User> findAll();
	public User findByUsername(String username);
	
}

package com.UserServices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserServices.model.User;

public interface UserRepo extends JpaRepository<User,Long> {

	public List<User> findAll();
	public User findByUsername(String username);
//	Optional<User> findByUsername(String username);
	User findByEmail(String email);
	
}

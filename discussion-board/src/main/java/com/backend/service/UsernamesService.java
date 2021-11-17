package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.model.Username;
import com.backend.repository.UserNamesRepo;

@Service
public class UsernamesService {
	
	private UserNamesRepo ur;
	
	public UsernamesService(UserNamesRepo ur) {
		this.ur = ur;
	}
	
	public List<Username> getAllUsernames() {
		return ur.findAll();
	}
}

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
	
	public Username getUsernameById(int id) {
		return ur.getById(id);
	}
	
	public List<Username> getAllUsernames() {
		return ur.findAll();
	}
	
	public Username createUsername(Username username) {
		return ur.save(username);
	}
	
	public Username updateUsername(Username username) {
		Username u = null;
		if(ur.findById(username.getUsernameId()).isPresent() == false) {
			u = ur.save(username);
		}
		return u;
	}
}

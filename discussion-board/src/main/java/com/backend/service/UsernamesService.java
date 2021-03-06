package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Username;
import com.backend.repository.UserNamesRepo;

@Service
public class UsernamesService {
	
	private UserNamesRepo ur;
	
	@Autowired
	public UsernamesService(UserNamesRepo ur) {
		this.ur = ur;
	}
	
	public Username getUsernameById(int id) {
		return ur.findById(id).get();
	}
	
	public List<Username> getAllUsernames() {
		return ur.findAll();
	}
	
	public List<Username> getFirst10() {
		return ur.findLast10ByOrderByIdDesc();
	}
	
	public Username createUsername(Username username) {
		return ur.save(username);
	}
	
	public Username updateUsername(Username username) {
		Username u = null;
		if(ur.findById(username.getId()).isPresent()) {
			u = ur.save(username);
		}
		return u; 
	}
	
	public boolean deleteUsername(Username username) {
		boolean flag = false;		 
		if(ur.findById(username.getId()).isPresent()) {
			ur.delete(username);
			flag = true;
		}
		return flag;
	}
}

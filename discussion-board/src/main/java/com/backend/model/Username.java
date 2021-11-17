package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usernames")
public class Username {
	
	@Id
	@Column(name="username_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int username_id;
	
	@Column(name="username")
	private String username;
	
	@OneToMany(mappedBy="username", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Subject> subjects = new ArrayList<>();
	
	@OneToMany(mappedBy="username", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Message> messages = new ArrayList<>();

	public int getUsername_id() {
		return username_id;
	}

	public void setUsername_id(int username_id) {
		this.username_id = username_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}

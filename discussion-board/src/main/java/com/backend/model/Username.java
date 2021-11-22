package com.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="usernames")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Username {
	
	@Id
	@Column(name="username_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@OneToMany(mappedBy="username", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Subject> subjects = new ArrayList<>();
	
	@OneToMany(mappedBy="username", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Message> messages = new ArrayList<>();	
	
	@ManyToMany(mappedBy="votes")
	@JsonIgnore
	private Set<Subject> voteSubjects = new HashSet<Subject>();
	
	
}

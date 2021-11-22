package com.discussionboard.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Subject")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Subject {
	
	@Id
	@Column(name="subject_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="timestamp")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date timestamp;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="username_id")
	private Username username;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
		name="subject_votes_junction",
		joinColumns= {@JoinColumn(name="subject_id")},
		inverseJoinColumns = {@JoinColumn(name="user_id")}
	)
	Set<Username> votes = new HashSet<Username>();
	
	@OneToMany(mappedBy = "subject",cascade=CascadeType.ALL)
	private List<Message> messages;

	
}

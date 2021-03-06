package com.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="message")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Message {

	@Id
	@Column(name="message_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="subject_id")
	@JsonIgnore
	private Subject subject;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private Username username;
	
	@Column(name="content", nullable = false)
	private String content;
	
	@Column(name="timestamp", nullable = false)
	private Date timestamp;
	
	
	
}

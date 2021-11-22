package com.FileClaimService.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="claims")
public class Claim {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="user_id", nullable=false)
	private int userId;
	
	@Column(name="claim_type", nullable=false)
	private String claimType;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="status")
	private String status;
	
}

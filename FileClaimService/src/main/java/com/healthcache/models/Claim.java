package com.healthcache.models;

import java.util.Arrays;
import java.util.List;
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
	
	private static final String APPROVED = "APPROVED";
	private static final String DENIED = "DENIED";

	private static final List<String> CLAIM_TYPES = Arrays.asList("SURGERY", "MEDICATION", "ELECTIVE", 
			"EMERGENCY", "OTHER");

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private int user_id;
	
	@Column(name="claim_type")
	private String claimType;
	
	@Column(name="description")
	private String description;
	
	@Column(name="Status")
	private String status;
	
	public List<String> getClaimTypes(){ return CLAIM_TYPES;}
	
}

package com.healthCache.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data
@Table(name="users")
public class User {
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;

	@Column(name="first_name", nullable=false)
	private String firstName;

	@Column(name="last_name", nullable=false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name="gender", nullable=false)
	private Gender gender;

	@Column(name="username", nullable=false, unique=true)
	private String username;

	@Column(name="email", nullable=false, unique=true)
	private String email;

	@Column(name="password", nullable=false)
	private String password;

	@Column(name="dob", nullable=false)
	private LocalDate dob;

	@Enumerated(EnumType.STRING)
	@Column(name="role", nullable=false)
	private UserRole role;

	@Column(name="address_line_one")
	private String addressLineOne;

	@Column(name="address_line_two")
	private String addressLineTwo;

	@Column(name="zipcode", nullable=false)
	private int zipcode;

	@Column(name="city", nullable=false)
	private String city;

	@Column(name="phone_no", nullable=false, unique=true)
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	@Column(name="relationship_status", nullable=false)
	private RelationshipStatus relationshipStatus;

	@Column(name="profile_pic")
	private String profilePic;
	
}

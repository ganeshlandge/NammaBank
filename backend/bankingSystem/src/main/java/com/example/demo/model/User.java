package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
	@Email(message = "Invalid email address")
	private String email;
	@Column(unique = true, nullable = false)
//	@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
	private long mobile_num;
	@Column(unique = true, nullable = false)
	private long adhar_id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String first_name;
	private String middle_name;
	private String father_name;
	@Column(nullable = false)
	private String last_name;
	@Column(nullable = false)
	private Date dob;
	@Column(nullable = false)
//	@Min(10)
	private int age;
	private String gender;
	@Column(nullable = false)
	private int perm_add_id;
	private int temp_add_id;
	@Column(nullable = false)
	private int is_admin;
	@Column(nullable = false)
	private String occupation_type;
	@Column(nullable = false)
	private String income_source;
	@Column(nullable = false)
	private double annual_income;
}

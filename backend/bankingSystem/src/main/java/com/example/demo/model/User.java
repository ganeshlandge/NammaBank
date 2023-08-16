package com.example.demo.model;

import javax.persistence.CascadeType;

//import java.sql.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
//	@Email(message = "Invalid email address")
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
	private String dob;
	@Column(nullable = false)
//	@Min(10)
	private int age;
	private String gender;
	@Column(nullable = false)
	private String perm_add_id;
	private String temp_add_id;
	@Column(nullable = false)
	private int is_admin;
	@Column(nullable = false)
	private String occupation_type;
	@Column(nullable = false)
	private String income_source;
	@Column(nullable = false)
	private double annual_income;
	
//	fetch=FetchType.EAGER,
	@OneToOne(mappedBy = "user",  cascade=CascadeType.ALL)
//	@OneToOne(mappedBy = "user")
	
	private Account account;

	public User() {
		super();
	}

	public User(Long id, String email, long mobile_num, long adhar_id, String title, String first_name,
			String middle_name, String father_name, String last_name, String dob, int age, String gender,
			String perm_add_id, String temp_add_id, int is_admin, String occupation_type, String income_source,
			double annual_income, Account account) {
		super();
		this.id = id;
		this.email = email;
		this.mobile_num = mobile_num;
		this.adhar_id = adhar_id;
		this.title = title;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.father_name = father_name;
		this.last_name = last_name;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.perm_add_id = perm_add_id;
		this.temp_add_id = temp_add_id;
		this.is_admin = is_admin;
		this.occupation_type = occupation_type;
		this.income_source = income_source;
		this.annual_income = annual_income;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile_num() {
		return mobile_num;
	}

	public void setMobile_num(long mobile_num) {
		this.mobile_num = mobile_num;
	}

	public long getAdhar_id() {
		return adhar_id;
	}

	public void setAdhar_id(long adhar_id) {
		this.adhar_id = adhar_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPerm_add_id() {
		return perm_add_id;
	}

	public void setPerm_add_id(String perm_add_id) {
		this.perm_add_id = perm_add_id;
	}

	public String getTemp_add_id() {
		return temp_add_id;
	}

	public void setTemp_add_id(String temp_add_id) {
		this.temp_add_id = temp_add_id;
	}

	public int getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}

	public String getOccupation_type() {
		return occupation_type;
	}

	public void setOccupation_type(String occupation_type) {
		this.occupation_type = occupation_type;
	}

	public String getIncome_source() {
		return income_source;
	}

	public void setIncome_source(String income_source) {
		this.income_source = income_source;
	}

	public double getAnnual_income() {
		return annual_income;
	}

	public void setAnnual_income(double annual_income) {
		this.annual_income = annual_income;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
//	public User() {
//		super();
//	}
//
//	public User(Long id, String email, long mobile_num, long adhar_id, String title, String first_name,
//			String middle_name, String father_name, String last_name, String dob, int age, String gender,
//			String perm_add_id, String temp_add_id, int is_admin, String occupation_type, String income_source,
//			double annual_income) {
//		super();
//		this.id = id;
//		this.email = email;
//		this.mobile_num = mobile_num;
//		this.adhar_id = adhar_id;
//		this.title = title;
//		this.first_name = first_name;
//		this.middle_name = middle_name;
//		this.father_name = father_name;
//		this.last_name = last_name;
//		this.dob = dob;
//		this.age = age;
//		this.gender = gender;
//		this.perm_add_id = perm_add_id;
//		this.temp_add_id = temp_add_id;
//		this.is_admin = is_admin;
//		this.occupation_type = occupation_type;
//		this.income_source = income_source;
//		this.annual_income = annual_income;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public long getMobile_num() {
//		return mobile_num;
//	}
//
//	public void setMobile_num(long mobile_num) {
//		this.mobile_num = mobile_num;
//	}
//
//	public long getAdhar_id() {
//		return adhar_id;
//	}
//
//	public void setAdhar_id(long adhar_id) {
//		this.adhar_id = adhar_id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getFirst_name() {
//		return first_name;
//	}
//
//	public void setFirst_name(String first_name) {
//		this.first_name = first_name;
//	}
//
//	public String getMiddle_name() {
//		return middle_name;
//	}
//
//	public void setMiddle_name(String middle_name) {
//		this.middle_name = middle_name;
//	}
//
//	public String getFather_name() {
//		return father_name;
//	}
//
//	public void setFather_name(String father_name) {
//		this.father_name = father_name;
//	}
//
//	public String getLast_name() {
//		return last_name;
//	}
//
//	public void setLast_name(String last_name) {
//		this.last_name = last_name;
//	}
//
//	public String getDob() {
//		return dob;
//	}
//
//	public void setDob(String dob) {
//		this.dob = dob;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getPerm_add_id() {
//		return perm_add_id;
//	}
//
//	public void setPerm_add_id(String perm_add_id) {
//		this.perm_add_id = perm_add_id;
//	}
//
//	public String getTemp_add_id() {
//		return temp_add_id;
//	}
//
//	public void setTemp_add_id(String temp_add_id) {
//		this.temp_add_id = temp_add_id;
//	}
//
//	public int getIs_admin() {
//		return is_admin;
//	}
//
//	public void setIs_admin(int is_admin) {
//		this.is_admin = is_admin;
//	}
//
//	public String getOccupation_type() {
//		return occupation_type;
//	}
//
//	public void setOccupation_type(String occupation_type) {
//		this.occupation_type = occupation_type;
//	}
//
//	public String getIncome_source() {
//		return income_source;
//	}
//
//	public void setIncome_source(String income_source) {
//		this.income_source = income_source;
//	}
//
//	public double getAnnual_income() {
//		return annual_income;
//	}
//
//	public void setAnnual_income(double annual_income) {
//		this.annual_income = annual_income;
//	}
//	
	
}

package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Account {
	@Id
	@GeneratedValue
	private long account_num;
	@Column(nullable = false)
//	private long user_id;
	private String username;
	private String login_passwd;
	private String transcation_passwd;
	private double balance;
	private String open_date;
	private String account_type;
	private int is_debit_card;
	private int is_credit_card;
	private int is_net_banking;
	private long timestamp;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	
	public Account() {
		super();
	}


	public Account(long account_num, String username, String login_passwd, String transcation_passwd, double balance,
			String open_date, String account_type, int is_debit_card, int is_credit_card, int is_net_banking,
			long timestamp, User user) {
		super();
		this.account_num = account_num;
		this.username = username;
		this.login_passwd = login_passwd;
		this.transcation_passwd = transcation_passwd;
		this.balance = balance;
		this.open_date = open_date;
		this.account_type = account_type;
		this.is_debit_card = is_debit_card;
		this.is_credit_card = is_credit_card;
		this.is_net_banking = is_net_banking;
		this.timestamp = timestamp;
		this.user = user;
	}


	public long getAccount_num() {
		return account_num;
	}


	public void setAccount_num(long account_num) {
		this.account_num = account_num;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getLogin_passwd() {
		return login_passwd;
	}


	public void setLogin_passwd(String login_passwd) {
		this.login_passwd = login_passwd;
	}


	public String getTranscation_passwd() {
		return transcation_passwd;
	}


	public void setTranscation_passwd(String transcation_passwd) {
		this.transcation_passwd = transcation_passwd;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public String getOpen_date() {
		return open_date;
	}


	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}


	public String getAccount_type() {
		return account_type;
	}


	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}


	public int getIs_debit_card() {
		return is_debit_card;
	}


	public void setIs_debit_card(int is_debit_card) {
		this.is_debit_card = is_debit_card;
	}


	public int getIs_credit_card() {
		return is_credit_card;
	}


	public void setIs_credit_card(int is_credit_card) {
		this.is_credit_card = is_credit_card;
	}


	public int getIs_net_banking() {
		return is_net_banking;
	}


	public void setIs_net_banking(int is_net_banking) {
		this.is_net_banking = is_net_banking;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
}

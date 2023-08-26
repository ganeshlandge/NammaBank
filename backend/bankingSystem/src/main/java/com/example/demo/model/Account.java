package com.example.demo.model;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
//	@GeneratedValue
	private long accountNum;
//	Random random = new Random();
//    accountNum = random.nextInt(9000) + 1000;
	@Column(unique = true)
	private String username;
	private String loginPasswd;
	private String transcationPasswd;
	private double balance;
	private String openDate;
	private String accountType;
	private int isDebitCard;
	private int isCreditCard;
	private int isNetBanking;
	private long timestamp;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Transcation> transcations;


	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Payee> payee;
	

	public Account() {
		super();
		this.accountNum = generateRandomAccountNumber();
	}
	// Helper method to generate a random 6-digit account number
    private long generateRandomAccountNumber() {
        Random random = new Random();
        return random.nextInt(900000) + 100000; 
    }

	public Account(long accountNum, String username, String loginPasswd, String transcationPasswd, double balance,
			String openDate, String accountType, int isDebitCard, int isCreditCard, int isNetBanking, long timestamp,
			User user, List<Transcation> transcations, List<Payee> payee) {

		super();
		this.accountNum = accountNum;
		this.username = username;
		this.loginPasswd = loginPasswd;
		this.transcationPasswd = transcationPasswd;
		this.balance = balance;
		this.openDate = openDate;
		this.accountType = accountType;
		this.isDebitCard = isDebitCard;
		this.isCreditCard = isCreditCard;
		this.isNetBanking = isNetBanking;
		this.timestamp = timestamp;
		this.user = user;
		this.transcations = transcations;
		this.payee = payee;
		
	}


	public long getAccountNum() {
		return accountNum;
	}


	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getLoginPasswd() {
		return loginPasswd;
	}


	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}


	public String getTranscationPasswd() {
		return transcationPasswd;
	}


	public void setTranscationPasswd(String transcationPasswd) {
		this.transcationPasswd = transcationPasswd;
	}


	public double getBalance() {
//		System.out.println("getBalance: "+ balance);
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public String getOpenDate() {
		return openDate;
	}


	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public int getIsDebitCard() {
		return isDebitCard;
	}


	public void setIsDebitCard(int isDebitCard) {
		this.isDebitCard = isDebitCard;
	}


	public int getIsCreditCard() {
		return isCreditCard;
	}


	public void setIsCreditCard(int isCreditCard) {
		this.isCreditCard = isCreditCard;
	}


	public int getIsNetBanking() {
		return isNetBanking;
	}


	public void setIsNetBanking(int isNetBanking) {
		this.isNetBanking = isNetBanking;
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


	public List<Transcation> getTranscations() {
		return transcations;
	}


	public void setTranscations(List<Transcation> transcations) {
		this.transcations = transcations;
	}


	public List<Payee> getPayee() {
		return payee;
	}


	public void setPayee(List<Payee> payee) {
		this.payee = payee;
	}

}

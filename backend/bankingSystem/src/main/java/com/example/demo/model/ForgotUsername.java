package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ForgotUsername {
	@Id
	@Column(nullable = false)
	private long accountNum;
	@Column(nullable = true)
	private String username;
	@Column(nullable = true)
	private int otp;
	@Column(nullable = true)
	private Timestamp timestamp;
	
	public ForgotUsername() {
		super();
	}

	public ForgotUsername(long accountNum, String username, int otp, Timestamp timestamp) {
		super();
		this.accountNum = accountNum;
		this.username = username;
		this.otp = otp;
		this.timestamp = timestamp;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}

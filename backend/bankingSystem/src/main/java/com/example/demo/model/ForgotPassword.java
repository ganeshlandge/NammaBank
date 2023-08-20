package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ForgotPassword {
	@Id
	@Column(nullable = false)
	private String username;
	@Column(nullable = true)
	private int otp;
	@Column(nullable = true)
	private Timestamp timestamp;
	
	public ForgotPassword() {
		super();
	}

	public ForgotPassword(String username, int otp, Timestamp timestamp) {
		super();
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

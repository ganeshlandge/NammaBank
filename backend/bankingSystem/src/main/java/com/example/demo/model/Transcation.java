package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Transcation {
	@Id
	@GeneratedValue
	private long transcactionId;
	private String transcationType;
	private double amountTransfer;
	private long creditAccNum;
//	private long deb_acc_num;
	private long timestamp;
	private String remark;
	
	@ManyToOne
	@JoinColumn(name = "debitAccNum", referencedColumnName = "accountNum", nullable = false)
	@JsonBackReference
	private Account account;

	public Transcation() {
		super();
	}

	public Transcation(long transcactionId, String transcationType, double amountTransfer, long creditAccNum,
			long timestamp, String remark, Account account) {
		super();
		this.transcactionId = transcactionId;
		this.transcationType = transcationType;
		this.amountTransfer = amountTransfer;
		this.creditAccNum = creditAccNum;
		this.timestamp = timestamp;
		this.remark = remark;
		this.account = account;
	}

	public long getTranscactionId() {
		return transcactionId;
	}

	public void setTranscactionId(long transcactionId) {
		this.transcactionId = transcactionId;
	}

	public String getTranscationType() {
		return transcationType;
	}

	public void setTranscationType(String transcationType) {
		this.transcationType = transcationType;
	}

	public double getAmountTransfer() {
		return amountTransfer;
	}

	public void setAmountTransfer(double amountTransfer) {
		this.amountTransfer = amountTransfer;
	}

	public long getCreditAccNum() {
		return creditAccNum;
	}

	public void setCreditAccNum(long creditAccNum) {
		this.creditAccNum = creditAccNum;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

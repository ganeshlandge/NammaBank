package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Transcation {
	@Id
	@GeneratedValue
	private long transcaction_id;
	private String transcation_type;
	private double amount_transfer;
	private long credit_acc_num;
//	private long deb_acc_num;
	private long timestamp;
	private String remark;
	
	@ManyToOne
	@JoinColumn(name = "deb_acc_num", referencedColumnName = "account_num", nullable = false)
	@JsonBackReference
	private Account account;
	
	

	public Transcation() {
		super();
	}



	public Transcation(long transcaction_id, String transcation_type, double amount_transfer, long credit_acc_num,
			long timestamp, String remark, Account account) {
		super();
		this.transcaction_id = transcaction_id;
		this.transcation_type = transcation_type;
		this.amount_transfer = amount_transfer;
		this.credit_acc_num = credit_acc_num;
		this.timestamp = timestamp;
		this.remark = remark;
		this.account = account;
	}



	public long getTranscaction_id() {
		return transcaction_id;
	}



	public void setTranscaction_id(long transcaction_id) {
		this.transcaction_id = transcaction_id;
	}



	public String getTranscation_type() {
		return transcation_type;
	}



	public void setTranscation_type(String transcation_type) {
		this.transcation_type = transcation_type;
	}



	public double getAmount_tansfer() {
		return amount_transfer;
	}



	public void setAmount_tansfer(double amount_tansfer) {
		this.amount_transfer = amount_tansfer;
	}



	public long getCredit_acc_num() {
		return credit_acc_num;
	}



	public void setCredit_acc_num(long credit_acc_num) {
		this.credit_acc_num = credit_acc_num;
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

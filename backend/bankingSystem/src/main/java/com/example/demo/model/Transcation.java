package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Transcation {
	@Id
	@GeneratedValue
	private long transcaction_id;
	private String transcation_type;
	private double amount_tansfer;
	private long credit_acc_num;
	private long deb_acc_num;
	private long timestamp;
	private String remark;
}

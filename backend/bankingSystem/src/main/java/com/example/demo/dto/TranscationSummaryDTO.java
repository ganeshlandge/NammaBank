package com.example.demo.dto;

public class TranscationSummaryDTO {
	private long transcactionId;
	private String transcationType;
	private double amountTransfer;
	private long creditAccNum;
	private long debitAccNum;
	private long timestamp;
	private String remark;
	
	public TranscationSummaryDTO() {
		super();
	}

	public TranscationSummaryDTO(long transcactionId, String transcationType, double amountTransfer, long creditAccNum,
			long debitAccNum, long timestamp, String remark) {
		super();
		this.transcactionId = transcactionId;
		this.transcationType = transcationType;
		this.amountTransfer = amountTransfer;
		this.creditAccNum = creditAccNum;
		this.debitAccNum = debitAccNum;
		this.timestamp = timestamp;
		this.remark = remark;
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

	public long getDebitAccNum() {
		return debitAccNum;
	}

	public void setDebitAccNum(long debitAccNum) {
		this.debitAccNum = debitAccNum;
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
	
}

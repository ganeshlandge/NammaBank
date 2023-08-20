package com.example.demo.dto;

public class SuccessResponse<T> {
	private String status;
	private String message;
	private T data;
	
	public SuccessResponse() {
		super();
	}
	
	public SuccessResponse(String message) {
		super();
		this.status = "success";
		this.message = message;
	}

	public SuccessResponse(String message, T data) {
		super();
		this.status = "success";
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}

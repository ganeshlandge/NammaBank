package com.example.demo.dto;

import lombok.Builder;

@Builder
public class JwtResponse {
	private String jwtToken;
	private String username;
	private String email;
	private long accountNum;
	public JwtResponse() {
		super();
	}
	public JwtResponse(String jwtToken, String username, String email, long accountNum) {
		super();
		this.jwtToken = jwtToken;
		this.username = username;
		this.email = email;
		this.accountNum = accountNum;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}
	// Private constructor to prevent direct instantiation
    private JwtResponse(Builder builder) {
        this.jwtToken = builder.jwtToken;
        this.username = builder.username;
        this.email = builder.email;
        this.accountNum = builder.accountNum;
    }
	 // Builder class
    public static class Builder {
        private String jwtToken;
        private String username;
        private String email;
        private long accountNum;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder accountNum(long accountNum) {
            this.accountNum = accountNum;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }
}

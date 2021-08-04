package com.fdm.qualifier.httpResponse;

import org.springframework.http.HttpStatus;

public class LoginResponse {
	private HttpStatus statusCode;
    private String msg;
    private String username;
    private int userId;
    private String accountType;
    private String jwtToken;

    public LoginResponse(){}
    
    public LoginResponse(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public LoginResponse(HttpStatus statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
    
    public LoginResponse(HttpStatus statusCode, String username, String accountType, String jwtToken) {
        this.statusCode = statusCode;
        this.accountType = accountType;
        this.jwtToken = jwtToken;
    }
    
    public LoginResponse(HttpStatus statusCode, String username, int userId, String accountType, String jwtToken) {
		super();
		this.statusCode = statusCode;
		this.username = username;
		this.userId = userId;
		this.accountType = accountType;
		this.jwtToken = jwtToken;
	}

	public LoginResponse(HttpStatus statusCode, String msg, int userId, String username, String accountType, String jwtToken) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.userId = userId;
        this.username = username;
        this.accountType = accountType;
        this.jwtToken = jwtToken;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getJwtToken() {
        return jwtToken;
    }
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
    public String toString() {
        return "LoginResponse [accountType=" + accountType + ", jwtToken=" + jwtToken + ", username=" + username + "]";
    }
}

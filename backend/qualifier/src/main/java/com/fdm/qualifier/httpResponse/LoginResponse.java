package com.fdm.qualifier.httpResponse;

import org.springframework.http.HttpStatus;

/**
 * Serialise for to-json conversion
 * 
 */
public class LoginResponse 
{
    private HttpStatus statusCode;
    private String msg;
    //private String username;
    //private String accountType;
    private String jwtToken;


    public LoginResponse(){}
    public LoginResponse(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public LoginResponse(HttpStatus statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public LoginResponse(HttpStatus statusCode, String msg, String jwtToken) {
        this.statusCode = statusCode;
        this.msg = msg;
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
    public String getJwtToken() {
        return jwtToken;
    }
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    


















    // public HttpStatus getStatusCode() {
    //     return statusCode;
    // }
    // public void setStatusCode(HttpStatus statusCode) {
    //     this.statusCode = statusCode;
    // }
    
    // public String getMsg() {
    //     return msg;
    // }

    // public void setMsg(String msg) {
    //     this.msg = msg;
    // }

    // public String getUsername() {
    //     return username;
    // }
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    // public String getAccountType() {
    //     return accountType;
    // }
    // public void setAccountType(String accountType) {
    //     this.accountType = accountType;
    // }
    // public String getJwtToken() {
    //     return jwtToken;
    // }
    // public void setJwtToken(String jwtToken) {
    //     this.jwtToken = jwtToken;
    // }
    // @Override
    // public String toString() {
    //     return "LoginResponse [accountType=" + accountType + ", jwtToken=" + jwtToken + ", username=" + username + "]";
    // }

}

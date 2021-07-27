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


    //==================================================
    // getters and setters
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
}

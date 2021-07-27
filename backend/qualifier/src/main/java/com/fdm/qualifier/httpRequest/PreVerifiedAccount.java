package com.fdm.qualifier.httpRequest;

public class PreVerifiedAccount 
{
    private String username;
    private String password;

    public PreVerifiedAccount(){}
    public PreVerifiedAccount(String username, String password) 
    {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PreVerifiedAccount [password=" + password + ", username=" + username + "]";
    }
}

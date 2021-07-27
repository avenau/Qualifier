package com.fdm.qualifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uid;

    private String username;
    private String password;
    private boolean isActive; //needed for spring security OR just return true always
    private String roles;     //can do clever things later

    public User(){super();}
    public User(String username, String password) 
    {
        this.username = username;
        this.password = password;
        this.isActive = true;
        this.roles = "admin";
    }

    public User(String username, String password, boolean isActive)
    {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.roles = "admin";
    }

    public User(String username, String password, boolean isActive, String roles)
    {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
    }

    //!!! PLACEHOLDER ONLY !!!
    //!!! PLACEHOLDER ONLY !!!
    //!!! PLACEHOLDER ONLY !!!
    public String getAccountType(){
        return "admin";
    }


    //========================================
    // getters and setters
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
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
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "User [isActive=" + isActive + ", password=" + password + ", roles=" + roles + ", uid=" + uid
                + ", username=" + username + "]";
    }
}

package com.fdm.qualifier.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//import com.fdm.q.model.Account;
import com.fdm.qualifier.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This is for Spring Security
 * 
 */
public class AccountDetails implements UserDetails
{
    private String username;
    private String password;
    private boolean isActive;
    private String accountType;
    private List<GrantedAuthority> authorities;

    public AccountDetails(){}
    public AccountDetails(String username)
    {
        this.username = username;
    }
    public AccountDetails(User user)
    {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isActive = user.isActive();
        this.accountType = user.getAccountType();
        this.authorities = Arrays.stream(user.getAccountType().split(","))
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList());
    }

    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return isActive;
    }
    @Override
    public String toString() {
        return "AccountDetails [authorities=" + authorities + ", isActive=" + isActive + ", password=" + password
                + ", username=" + username + "]";
    }
}

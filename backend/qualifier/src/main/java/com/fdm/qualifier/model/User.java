package com.fdm.qualifier.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;

	private String username;
	private String password;
	private String email;
	private String address;
	private int phoneNumber;
	private String city;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private boolean isActive; // needed for spring security OR just return true always
	private String roles; // can do clever things later

	@OneToMany(mappedBy = "user")
	private List<UserNotification> userNotification;

	public User() {
		super();
	}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.isActive = true;
		this.roles = role;
	}

	public User(String username, String password, boolean isActive) {
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.roles = "admin";
	}

	public User(String username, String password, boolean isActive, String roles) {
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.roles = roles;
	}

	public User(String username, String password, String email, String address, int phoneNumber, String city,
			String firstName, String lastName, LocalDate dob, List<UserNotification> userNotification, boolean isActive,
			String roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.userNotification = userNotification;
		this.isActive = isActive;
		this.roles = roles;
	}

	// !!! PLACEHOLDER ONLY !!!
	// !!! PLACEHOLDER ONLY !!!
	// !!! PLACEHOLDER ONLY !!!
	public String getAccountType() {
		return this.roles;
	}

	// ========================================
	// getters and setters

	public int getUserId() {
		return uid;
	}

	public void setUserId(int userId) {
		this.uid = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public List<UserNotification> getUserNotification() {
		return userNotification;
	}

	public void setUserNotification(List<UserNotification> userNotification) {
		this.userNotification = userNotification;
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
		return "User [userId=" + uid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", city=" + city + ", firstName="
				+ firstName + ", lastName=" + lastName + ", dob=" + dob + ", isActive=" + isActive + ", userType="
				+ roles + "]";
	}
}

package com.fdm.qualifier.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "trainer")
public class Trainer extends User {
	@ManyToMany
	@JoinTable(name="trainer_skills")
	private List<Skill> skills;

	public Trainer() {
		super();
	}

	public Trainer(String username, String password, boolean isActive, String roles) {
		super(username, password, isActive, roles);
	}

	public Trainer(String username, String password, boolean isActive) {
		super(username, password, isActive);
	}

	public Trainer(String username, String password, String email, String address, int phoneNumber, String city,
			String firstName, String lastName, LocalDate dob, List<UserNotification> userNotification, boolean isActive,
			String roles) {
		super(username, password, email, address, phoneNumber, city, firstName, lastName, dob, userNotification,
				isActive, roles);
	}

	public Trainer(String username, String password) {
		super(username, password, "trainer");
	}

	public Trainer(String username, String password, String email, String address, int phoneNumber, String city,
			String firstName, String lastName, LocalDate dob, List<UserNotification> userNotification, boolean isActive,
			String roles, List<Skill> skills) {
		super(username, password, email, address, phoneNumber, city, firstName, lastName, dob, userNotification,
				isActive, roles);
		this.skills = skills;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Trainer [skills=" + skills + ", toString()=" + super.toString() + "]";
	}

}

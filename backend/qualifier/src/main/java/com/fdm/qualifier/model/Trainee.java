package com.fdm.qualifier.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "trainee")
public class Trainee extends User {
	private LocalDate completionDate;

	@ManyToOne
	@JoinColumn(name = "FK_STREAM")
	private Stream stream;

	@ManyToOne
	private List<Placement> placements;

	@ManyToOne
	private List<Results> results;

	@ManyToMany
	@JoinColumn(name = "FK_SKILL_LEVEL")
	private List<SkillLevel> skills;

	public Trainee() {
		super();
	}

	public Trainee(String username, String password, LocalDate date, Stream stream) {
		super(username, password);
		this.completionDate = date;
		this.stream = stream;
	}

	public Trainee(String username, String password, boolean isActive, LocalDate date, Stream stream) {
		super(username, password, isActive);
		this.completionDate = date;
		this.stream = stream;
	}

	public Trainee(String username, String password, boolean isActive, String userType, LocalDate date, Stream stream) {
		super(username, password, isActive, userType);
		this.completionDate = date;
		this.stream = stream;
	}

	public Trainee(String username, String password, String email, String address, int phoneNumber, String city,
			String firstName, String lastName, LocalDate dob, List<UserNotification> userNotification, boolean isActive,
			String userType, LocalDate date, Stream stream) {
		super(username, password, email, address, phoneNumber, city, firstName, lastName, dob, userNotification,
				isActive, userType);
		this.completionDate = date;
		this.stream = stream;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public Stream getStream() {
		return stream;
	}

	public void setStream(Stream stream) {
		this.stream = stream;
	}

	public List<Placement> getPlacements() {
		return placements;
	}

	public void setPlacements(List<Placement> placements) {
		this.placements = placements;
	}

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public List<SkillLevel> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillLevel> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Trainee [date=" + completionDate + ", stream=" + stream + ", toString()=" + super.toString() + "]";
	}
}

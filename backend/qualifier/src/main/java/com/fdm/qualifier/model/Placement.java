package com.fdm.qualifier.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Placement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int placementId;
	private String name;
	private LocalDate startDate;
	private LocalDate completionDate;
	private String description;
	private String location;
	
	@ManyToOne
	private Client client;
	
	@OneToOne
	private Trainee trainee;
	
	@ManyToMany
	private List<Trainee> appliedTrainees;
	
	@ManyToMany
	private List<SkillLevel> skillsNeeded;
	
	public Placement() {
		super();
	}

	public Placement(String name, LocalDate startDate, LocalDate completionDate, String description,
			String location, Client client, List<SkillLevel> skillsNeeded) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.description = description;
		this.location = location;
		this.client = client;
		this.skillsNeeded = skillsNeeded;
	}


	public Placement(String name, LocalDate startDate, LocalDate completionDate, String description, String location, Client client,
			Trainee trainee, List<Trainee> appliedTrainees, List<SkillLevel> skillsNeeded) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.description = description;
		this.location = location;
		this.client = client;
		this.trainee = trainee;
		this.appliedTrainees = appliedTrainees;
		this.skillsNeeded = skillsNeeded;
	}
	
	public void addAppliedTrainee(Trainee trainee) {
		appliedTrainees.add(trainee);
	}

	public int getPlacementId() {
		return placementId;
	}

	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public List<Trainee> getAppliedTrainees() {
		return appliedTrainees;
	}

	public void setAppliedTrainees(List<Trainee> appliedTrainees) {
		this.appliedTrainees = appliedTrainees;
	}

	public List<SkillLevel> getSkillsNeeded() {
		return skillsNeeded;
	}

	public void setSkillsNeeded(List<SkillLevel> skillsNeeded) {
		this.skillsNeeded = skillsNeeded;
	}

	@Override
	public String toString() {
		return "Placement [placementId=" + placementId + ", " + (name != null ? "name=" + name + ", " : "")
				+ (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (completionDate != null ? "completionDate=" + completionDate + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (location != null ? "location=" + location + ", " : "")
				+ (client != null ? "client=" + client + ", " : "")  + "]";
	}
	
	

}

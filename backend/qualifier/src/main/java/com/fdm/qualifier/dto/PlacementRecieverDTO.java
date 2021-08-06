package com.fdm.qualifier.dto;

import java.time.LocalDate;
import java.util.List;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Trainee;

public class PlacementRecieverDTO {
	private String name;
	private LocalDate startDate;
	private LocalDate completionDate;
	private String description;
	private String location;
	private ClientDTO client;
	private List<Integer> skillsNeeded;

	public PlacementRecieverDTO() {
		super();
	}

	public PlacementRecieverDTO(String name, LocalDate startDate, LocalDate completionDate, String description,
			String location, ClientDTO client, List<Integer> skillsNeeded) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.description = description;
		this.location = location;
		this.client = client;
		this.skillsNeeded = skillsNeeded;
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

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public List<Integer> getSkillsNeeded() {
		return skillsNeeded;
	}

	public void setSkillsNeeded(List<Integer> skillsNeeded) {
		this.skillsNeeded = skillsNeeded;
	}

	@Override
	public String toString() {
		return "PlacementRecieverDTO [name=" + name + ", startDate=" + startDate + ", completionDate=" + completionDate
				+ ", description=" + description + ", location=" + location + ", client=" + client + ", skillsNeeded="
				+ skillsNeeded + "]";
	}

}

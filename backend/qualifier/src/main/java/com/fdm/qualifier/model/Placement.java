package com.fdm.qualifier.model;

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
	private String startDate;
	private String description;
	private String location;
	@ManyToOne
	private Client client;
	private boolean status;
	@OneToOne
	private Quiz quiz;
	@ManyToMany
	private Trainee trainee;
	
	public Placement() {
		super();
	}

	public Placement(String startDate, String description, String location, Client client,
			boolean status, Quiz quiz) {
		super();
		this.startDate = startDate;
		this.description = description;
		this.location = location;
		this.client = client;
		this.status = status;
		this.quiz = quiz;
		this.trainee = null;
	}
	
	public Placement(String startDate, String description, String location, Client client,
			boolean status, Quiz quiz, Trainee trainee) {
		super();
		this.startDate = startDate;
		this.description = description;
		this.location = location;
		this.client = client;
		this.status = status;
		this.quiz = quiz;
		this.trainee = trainee;
	}

	public int getPlacementId() {
		return placementId;
	}

	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	@Override
	public String toString() {
		return "Placement [placementId=" + placementId + ", startDate=" + startDate + ", description=" + description
				+ ", location=" + location + ", status=" + status + "]";
	}
	
	
	
	
	
	

}

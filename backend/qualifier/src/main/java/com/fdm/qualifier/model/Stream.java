package com.fdm.qualifier.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Stream {
	@Id
	@GeneratedValue
	private int streamId;
	private String name;

	@OneToMany
	private List<Trainee> trainees;

	public Stream() {
		super();
	}

	public Stream(String name, List<Trainee> trainees) {
		super();
		this.name = name;
		this.trainees = trainees;
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<Trainee> trainees) {
		this.trainees = trainees;
	}

	@Override
	public String toString() {
		return "Stream [streamId=" + streamId + ", name=" + name + "]";
	}

}

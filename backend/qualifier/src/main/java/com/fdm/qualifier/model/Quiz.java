package com.fdm.qualifier.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int quizId;
	private String name;
	private String description;
	private double duration;
	private int questionCount;
	private double passingMark;

	@OneToMany(mappedBy = "quiz")
	private List<Question> questions;

	public Quiz() {
		super();
	}
	
	public Quiz(String name, String description, double duration, double passingMark) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.passingMark = passingMark;
	}

	public Quiz(String name, String description, double duration, int questionCount, double passingMark,
			List<Question> questions) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.questionCount = questionCount;
		this.passingMark = passingMark;
		this.questions = questions;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public double getPassingMark() {
		return passingMark;
	}

	public void setPassingMark(double passingMark) {
		this.passingMark = passingMark;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", name=" + name + ", description=" + description + ", duration=" + duration
				+ ", questionCount=" + questionCount + ", passingMark=" + passingMark + "]";
	}

}

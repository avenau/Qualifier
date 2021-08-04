package com.fdm.qualifier.httpRequest;

import java.util.List;

import com.fdm.qualifier.model.Question;

public class UpdateQuizRequest {
	private int quizId;
	private String name;
	private String description;
	private double duration;
	private int questionCount;
	private double passingMark;
	private List<Question> questions;
	public UpdateQuizRequest(int quizId, String name, String description, double duration, int questionCount,
			double passingMark, List<Question> questions) {
		super();
		this.quizId = quizId;
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
}

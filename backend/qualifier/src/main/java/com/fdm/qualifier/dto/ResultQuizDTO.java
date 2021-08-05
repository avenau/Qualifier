package com.fdm.qualifier.dto;

import java.util.List;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;

public class ResultQuizDTO {
	private int quizId;
	private String name;
	private String description;
	private double duration;
	private int questionCount;
	private double passingMark;
	private TraineeSkillLevelDTO skillLevel;
	private List<Question> questions;

	public ResultQuizDTO() {
		super();
	}

	public ResultQuizDTO(int quizId, String name, String description, double duration, int questionCount,
			double passingMark, TraineeSkillLevelDTO skillLevel, List<Question> questions) {
		super();
		this.quizId = quizId;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.questionCount = questionCount;
		this.passingMark = passingMark;
		this.skillLevel = skillLevel;
		this.questions = questions;
	}

	public ResultQuizDTO(Quiz quiz) {
		super();
		this.quizId = quiz.getQuizId();
		this.name = quiz.getName();
		this.description = quiz.getDescription();
		this.duration = quiz.getDuration();
		this.questionCount = quiz.getQuestionCount();
		this.passingMark = quiz.getPassingMark();
		this.skillLevel = new TraineeSkillLevelDTO(quiz.getSkillLevel());
		this.questions = quiz.getQuestions();
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

	public TraineeSkillLevelDTO getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(TraineeSkillLevelDTO skillLevel) {
		this.skillLevel = skillLevel;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "ResultQuizDTO [quizId=" + quizId + ", name=" + name + ", description=" + description + ", duration="
				+ duration + ", questionCount=" + questionCount + ", passingMark=" + passingMark + ", skillLevel="
				+ skillLevel + ", questions=" + questions + "]";
	}

}

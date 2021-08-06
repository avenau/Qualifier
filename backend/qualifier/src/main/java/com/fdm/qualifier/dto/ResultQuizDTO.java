package com.fdm.qualifier.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	private List<ResultQuestionDTO> questions;

	public ResultQuizDTO() {
		super();
	}

	@JsonCreator
	public ResultQuizDTO(@JsonProperty("quizId") int quizId, 
			@JsonProperty("name") String name, 
			@JsonProperty("description") String description, 
			@JsonProperty("duration") double duration, 
			@JsonProperty("questionCount") int questionCount,
			@JsonProperty("passingMark") double passingMark, 
			@JsonProperty("skillLevel") TraineeSkillLevelDTO skillLevel, 
			@JsonProperty("questions") List<ResultQuestionDTO> questions) {
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
		List<ResultQuestionDTO> questions = new ArrayList<>();
		for(Question question : quiz.getQuestions()) {
			questions.add(new ResultQuestionDTO(question));
		}
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

	public TraineeSkillLevelDTO getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(TraineeSkillLevelDTO skillLevel) {
		this.skillLevel = skillLevel;
	}

	public List<ResultQuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ResultQuestionDTO> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "ResultQuizDTO [quizId=" + quizId + ", name=" + name + ", description=" + description + ", duration="
				+ duration + ", questionCount=" + questionCount + ", passingMark=" + passingMark + ", skillLevel="
				+ skillLevel + ", questions=" + questions + "]";
	}

}

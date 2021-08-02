package com.fdm.qualifier.dto;

import java.util.List;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;

public class QuizDTO {
	
	private int quizId;
	private String quizName;
	private String quizDescription;
	private double quizDuration;
	private int questionCount;
	private double passingMark;
	private List<Question> questions;
	
	public QuizDTO(Quiz quiz) {
		super();
		this.quizId = quiz.getQuizId();
		this.quizName = quiz.getName();
		this.quizDescription = quiz.getDescription();
		this.quizDuration = quiz.getDuration();
		this.questionCount = quiz.getQuestionCount();
		this.passingMark = quiz.getPassingMark();
		this.questions = quiz.getQuestions();
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getQuizDescription() {
		return quizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}

	public double getQuizDuration() {
		return quizDuration;
	}

	public void setQuizDuration(double quizDuration) {
		this.quizDuration = quizDuration;
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

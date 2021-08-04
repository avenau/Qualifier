package com.fdm.qualifier.dto;

import java.util.ArrayList;
import java.util.List;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;

public class QuizDTO {
	
	private int quizId;
	private String name;
	private String description;
	private double duration;
	private int questionCount;
	private double passingMark;
	private List<QuestionDTO> questions;
	
	public QuizDTO(Quiz quiz) {
		super();
		this.quizId = quiz.getQuizId();
		this.name = quiz.getName();
		this.description = quiz.getDescription();
		this.duration = quiz.getDuration();
		this.questionCount = quiz.getQuestionCount();
		this.passingMark = quiz.getPassingMark();
		
		this.questions = new ArrayList<QuestionDTO>();
		if (quiz.getQuestions() != null) {
			for (Question question : quiz.getQuestions()) {
				questions.add(new QuestionDTO(question));
			}
		}
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

	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}


}

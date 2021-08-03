package com.fdm.qualifier.request;

import java.util.List;

import com.fdm.qualifier.model.Question;

public class UpdateQuizRequest {
	private String quizId;
	private String quizName;
	private List<Question> questions;
	
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	
	
}

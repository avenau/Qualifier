package com.fdm.qualifier.dto;

import com.fdm.qualifier.model.Answer;

public class AnswerDTO2 {
	private int answerId;
	private String content;
	private boolean correct;
	
	
	public AnswerDTO2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerDTO2(Answer answer) {
		super();
		this.answerId = answer.getAnswerId();
		this.content = answer.getContent();
		this.correct = answer.isCorrect();
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	

	
}

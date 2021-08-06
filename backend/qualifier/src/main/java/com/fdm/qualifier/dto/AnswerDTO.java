package com.fdm.qualifier.dto;

import com.fdm.qualifier.model.Answer;

/**
 * Answer DTO
 * @author William
 *
 */
public class AnswerDTO {
	private int answerId;
	private String answerContent;
	
	public AnswerDTO(Answer answer) {
		super();
		this.answerId = answer.getAnswerId();
		this.answerContent = answer.getContent();
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	
}

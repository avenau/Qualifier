package com.fdm.qualifier.dto;

import java.util.List;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;

public class QuestionDTO {
	private int questionId;
	private String questionContent;
	private String questionType;
	private int questionPoints;
	private byte[] questionImage;
	private List<Answer> answers;
	public QuestionDTO() {
		super();
	}

	public QuestionDTO(Question question) {
		super();
		this.questionId = question.getQuestionId();
		this.questionContent = question.getContent();
		this.questionType = question.getType().toString();
		this.questionPoints = question.getPoints();
		this.questionImage = question.getImage();
		this.answers = question.getAnswers();
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public int getQuestionPoints() {
		return questionPoints;
	}

	public void setQuestionPoints(int questionPoints) {
		this.questionPoints = questionPoints;
	}

	public byte[] getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(byte[] questionImage) {
		this.questionImage = questionImage;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
}

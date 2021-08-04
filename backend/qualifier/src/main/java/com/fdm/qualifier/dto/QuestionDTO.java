package com.fdm.qualifier.dto;

import java.util.List;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.model.SubmittedAnswer;

public class QuestionDTO {
	private int questionId;
	private String questionContent;
	private QuestionType questionType;
	private int questionPoints;
	private byte[] questionImage;
	private List<Answer> answers;
	private List<SubmittedAnswer> submittedAnswers;
	
	public QuestionDTO(Question question) {
		super();
		this.questionId = question.getQuestionId();
		this.questionContent = question.getContent();
		this.questionType = question.getType();
		this.questionPoints = question.getPoints();
		this.questionImage = question.getImage();
		this.answers = question.getAnswers();
//		this.submittedAnswers = question.getSubmittedAnswers();
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

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
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

	public List<SubmittedAnswer> getSubmittedAnswers() {
		return submittedAnswers;
	}

	public void setSubmittedAnswers(List<SubmittedAnswer> submittedAnswers) {
		this.submittedAnswers = submittedAnswers;
	}
	
	
}

package com.fdm.qualifier.dto;

import java.util.ArrayList;
import java.util.List;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;

public class QuestionDTO2 {
	private int questionId;
	private String questionContent;
	private String questionType;
	private int questionPoints;
	private byte[] questionImage;
	private List<AnswerDTO2> answers;

//	private List<SubmittedAnswer> submittedAnswers;
	public QuestionDTO2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionDTO2(Question question) {
		super();
		this.questionId = question.getQuestionId();
		this.questionContent = question.getContent();
		this.questionType = question.getType().toString();
		this.questionPoints = question.getPoints();
		this.questionImage = question.getImage();
		this.answers = answerListToDTOList(question.getAnswers());
//		this.submittedAnswers = question.getSubmittedAnswers();
	}

	public int getQuestionId() {
		return questionId;
	}
	
	public List<AnswerDTO2> answerListToDTOList(List<Answer> list) {
		List<AnswerDTO2> dtoList = new ArrayList<AnswerDTO2>();
		for (Answer answer : list) {
			AnswerDTO2 dto = new AnswerDTO2(answer);
			dtoList.add(dto);
		}
		return dtoList;
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

	public List<AnswerDTO2> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDTO2> answers) {
		this.answers = answers;
	}
	

//	public List<SubmittedAnswer> getSubmittedAnswers() {
//		return submittedAnswers;
//	}
//
//	public void setSubmittedAnswers(List<SubmittedAnswer> submittedAnswers) {
//		this.submittedAnswers = submittedAnswers;
//	}
	
	
}

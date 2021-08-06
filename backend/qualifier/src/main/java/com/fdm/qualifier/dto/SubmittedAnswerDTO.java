package com.fdm.qualifier.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.SubmittedAnswer;

public class SubmittedAnswerDTO {
	private int submittedAnswer_id;
	private Question question;
	private int resultId;
	private Answer answer;
	private String answerContent;

	public SubmittedAnswerDTO() {
		super();
	}

	@JsonCreator
	public SubmittedAnswerDTO(@JsonProperty("submittedAnswer_id") int submittedAnswer_id, 
			@JsonProperty("question") Question question, 
			@JsonProperty("resultId") int resultId, 
			@JsonProperty("answer") Answer answer,
			@JsonProperty("answerContent") String answerContent) {
		super();
		this.submittedAnswer_id = submittedAnswer_id;
		this.question = question;
		this.resultId = resultId;
		this.answer = answer;
		this.answerContent = answerContent;
	}

	public SubmittedAnswerDTO(SubmittedAnswer submittedAnswer) {
		super();
		this.submittedAnswer_id = submittedAnswer.getSubmittedAnswer_id();
		this.question = submittedAnswer.getQuestion();
		this.resultId = submittedAnswer.getResult().getResultId();
		this.answer = submittedAnswer.getAnswer();
		this.answerContent = submittedAnswer.getAnswerContent();
	}

	public int getSubmittedAnswer_id() {
		return submittedAnswer_id;
	}

	public void setSubmittedAnswer_id(int submittedAnswer_id) {
		this.submittedAnswer_id = submittedAnswer_id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	@Override
	public String toString() {
		return "SubmittedAnswerDTO [submittedAnswer_id=" + submittedAnswer_id + ", question=" + question + ", resultId="
				+ resultId + ", answer=" + answer + ", answerContent=" + answerContent + "]";
	}

}

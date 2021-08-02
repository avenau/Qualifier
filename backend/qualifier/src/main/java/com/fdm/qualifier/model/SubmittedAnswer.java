package com.fdm.qualifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SubmittedAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int submittedAnswer_id;
	@ManyToOne
	private Question question;
	@ManyToOne
	private Result result;
	@ManyToOne
	private Answer answer;
	private String answerContent;
	
	public SubmittedAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubmittedAnswer(Question question, Result result, Answer answer, String answerContent) {
		super();
		this.question = question;
		this.result = result;
		this.answer = answer;
		this.answerContent = answerContent;
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

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
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
		return "SubmittedAnswer [submittedAnswer_id=" + submittedAnswer_id + ", question=" + question + ", result="
				+ result + ", answer=" + answer + ", answerContent=" + answerContent + "]";
	}
	
	

}

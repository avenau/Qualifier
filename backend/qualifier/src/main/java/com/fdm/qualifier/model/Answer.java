package com.fdm.qualifier.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int answerId;
	private String content;
	@ManyToOne
	@JsonBackReference
	private Question question;
	private boolean correct;
	
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(String content, Question question, boolean correct) {
		super();
		this.content = content;
		this.question = question;
		this.correct = correct;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", content=" + content + ", correct=" + correct + "]";
	}
	
	

}

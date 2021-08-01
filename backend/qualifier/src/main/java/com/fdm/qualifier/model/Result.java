package com.fdm.qualifier.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resultId;
	private double mark;
	private boolean passed;

	@ManyToOne
	private Trainee trainee;
	
	@ManyToOne//#TODO add mappedBy
	private Quiz quiz;
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(double mark, Trainee trainee, Quiz quiz, boolean passed) {
		super();
		this.mark = mark;
		this.trainee = trainee;
		this.quiz = quiz;
		this.passed = passed;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	

	@Override
	public String toString() {
		return "Results [resultId=" + resultId + ", mark=" + mark + ", trainee=" + trainee + ", quiz=" + quiz
				+ ", passed=" + passed + "]";
	}
	
	
}

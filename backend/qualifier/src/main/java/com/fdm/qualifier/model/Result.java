package com.fdm.qualifier.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resultId;
	private double mark;
	private boolean passed;
	private boolean marked;

	@ManyToOne
	@JsonBackReference(value = "trainee-result")
	private Trainee trainee;

	@OneToOne(cascade = CascadeType.MERGE)
//	@JsonManagedReference(value = "quiz-result")
	private Quiz quiz;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "result")
	@JsonManagedReference(value = "submittedAnswer-result")
	private List<SubmittedAnswer> submittedAnswers;

	public Result() {
		super();
	}

	public Result(double mark, Trainee trainee, Quiz quiz, boolean passed) {
		super();
		this.mark = mark;
		this.passed = passed;
		this.trainee = trainee;
		this.quiz = quiz;
	}

	public Result(double mark, boolean passed, boolean marked, Quiz quiz, List<SubmittedAnswer> submittedAnswers) {
		super();
		this.mark = mark;
		this.passed = passed;
		this.marked = marked;
		this.quiz = quiz;
		this.submittedAnswers = submittedAnswers;
	}

	public Result(double mark, boolean passed, boolean marked, Trainee trainee, Quiz quiz,
			List<SubmittedAnswer> submittedAnswers) {
		super();
		this.mark = mark;
		this.passed = passed;
		this.marked = marked;
		this.trainee = trainee;
		this.quiz = quiz;
		this.submittedAnswers = submittedAnswers;
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

	public List<SubmittedAnswer> getSubmittedAnswers() {
		return submittedAnswers;
	}

	public void setSubmittedAnswers(List<SubmittedAnswer> submittedAnswers) {
		this.submittedAnswers = submittedAnswers;
	}

	public void addSubmittedAnswer(SubmittedAnswer answer) {
		this.submittedAnswers.add(answer);
	}

	public void removeSubmittedAnswer(SubmittedAnswer answer) {
		this.submittedAnswers.remove(answer);
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	@Override
	public String toString() {
		return "Result [resultId=" + resultId + ", mark=" + mark + ", passed=" + passed + ", marked=" + marked
				+ ", trainee=" + trainee + ", quiz=" + quiz + "]";
	}

}

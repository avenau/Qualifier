package com.fdm.qualifier.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fdm.qualifier.dto.QuestionDTO;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int quizId;
	private String name;
	private String description;
	private double duration;
	private int questionCount;
	private double passingMark;
	
	@OneToOne
	@JsonManagedReference(value = "skillLevel")
	private SkillLevel skillLevel;

	@OneToMany(mappedBy = "quiz")
	@JsonManagedReference(value = "quiz")
	private List<Question> questions;

	public Quiz() {
		super();
	}
	
	public Quiz(String name, String description, double duration, int questionCount,double passingMark) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.questionCount = questionCount;
		this.passingMark = passingMark;
		this.questions = new ArrayList<>();
	}
	
	public Quiz(String name, String description, double duration, int questionCount, double passingMark,
			SkillLevel skillLevel) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.questionCount = questionCount;
		this.passingMark = passingMark;
		this.skillLevel = skillLevel;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public double getPassingMark() {
		return passingMark;
	}

	public void setPassingMark(double passingMark) {
		this.passingMark = passingMark;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void setQuestionsByDTO(List<QuestionDTO> questionDTOs) {
		for (QuestionDTO questionDTO : questionDTOs) {
			Question question = new Question(questionDTO);
			this.questions.add(question);
		}
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}
	
	public double getFullMark() {
		double mark = 0.0;
		for (Question question : questions) {
			mark += question.getPoints();
		}
		return mark;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", name=" + name + ", description=" + description + ", duration=" + duration
				+ ", questionCount=" + questionCount + ", passingMark=" + passingMark + "]";
	}

	

}
